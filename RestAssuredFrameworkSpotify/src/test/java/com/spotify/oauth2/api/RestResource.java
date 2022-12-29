package com.spotify.oauth2.api;

import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {
    public static Response post(String path, String token, Object requestPlaylist){
        return given(getRequestSpec()).
                body(requestPlaylist).
                header("Authorization", "Bearer " + token).
                when().post(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response postAccount(HashMap<String, String> formParams){
        return given().spec(getAccountRequestSpec()).
                formParams(formParams).
                log().all().
                when().post(API + TOKEN).
                then().spec(getResponseSpec()).
                extract().response();
    }

    public static Response get(String path, String id, String token) {
        return given(getRequestSpec()).
//                header("Authorization", "Bearer " + token).
                auth().oauth2(token).
                when().get(path + id).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response update(String path, String token, Object requestPlaylist, String id) {
        return given(getRequestSpec()).
                body(requestPlaylist).
                header("Authorization", "Bearer " + token).
                pathParam("id", id).
                when().put(path).
                then().spec(getResponseSpec())
                .extract().response();
    }
}
