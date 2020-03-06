package com.stoom.backend.util;

import com.stoom.backend.entity.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class URLUtil {

    private static final Logger log = LoggerFactory.getLogger(URLUtil.class);

    public static Optional<String> getUrlRequest(Address address, String url, String key){
        String urlAPI = url
                .concat(address.getStreetName())
                .concat("+")
                .concat(address.getNumber().toString())
                .concat("+")
                .concat(address.getCity())
                .concat("+")
                .concat(address.getState())
                .concat("&key=")
                .concat(key);
        urlAPI = urlAPI.replaceAll(" ", "+");
        return Optional.of(urlAPI);
    }
}
