package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.params.CoreConnectionPNames;

import static com.spotify.oauth2.api.Route.BASE_PATH;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com").
                setBasePath(BASE_PATH).
//                setBasePath(ConfigManager.getInstance().getString("base_path")).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();
    }

    public static RequestSpecification getAccountRequestSpec() {
        return new RequestSpecBuilder().
                setBaseUri("https://accounts.spotify.com").
                setContentType(ContentType.URLENC).
                log(LogDetail.ALL).
                build();
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}