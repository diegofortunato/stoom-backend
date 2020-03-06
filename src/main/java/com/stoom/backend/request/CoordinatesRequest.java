package com.stoom.backend.request;

import com.stoom.backend.entity.Address;
import com.stoom.backend.exception.ApplicationException;
import com.stoom.backend.util.URLUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class CoordinatesRequest {


    @Value("${google.api.url}")
    private String url;

    @Value("${google.api.key}")
    private String key;

    public Address getCoordinatesAddress(Address address) throws ApplicationException {
        RestTemplate restTemplate = new RestTemplate();
        Optional<String> urlAPI = URLUtil.getUrlRequest(address, url, key);
        if (urlAPI.isPresent()){
            ResponseEntity<String> response = restTemplate.getForEntity(urlAPI.get(), String.class);
            JSONArray jsonObjectArray = new JSONObject(response.getBody()).getJSONArray("results");
            JSONObject locationObject = new JSONObject();
            locationObject = getJsonLocationObject(jsonObjectArray, locationObject);
            Double lat = locationObject.getDouble("lat");
            Double lng = locationObject.getDouble("lng");
            address.setLatitude(lat.toString());
            address.setLongitude(lng.toString());
            return address;
        }
        throw new ApplicationException("Coordinate integration error");
    }

    private JSONObject getJsonLocationObject(JSONArray jsonObjectArray, JSONObject locationObject) {
        JSONObject jsonObject = new JSONObject();
        JSONObject geometryObject = new JSONObject();
        for (int i = 0; i < jsonObjectArray.length(); i++) {
            jsonObject = jsonObjectArray.getJSONObject(i);
            geometryObject = jsonObject.getJSONObject("geometry");
            locationObject = geometryObject.getJSONObject("location");
        }
        return locationObject;
    }

}
