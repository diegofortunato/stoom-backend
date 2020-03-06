FROM java:8-jdk-alpine

COPY ./target/stoom-backend.jar /build/

WORKDIR /build/

RUN sh -c 'touch stoom-backend.jar'

ENTRYPOINT ["java","-jar","stoom-backend.jar"]