package com.spotify.oauth2.api;

import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    //as parameter Playlist requestPlaylist ---> Object requestPlaylist, we need to create reusable methods that can be used across all apis
    public static Response post(String path, String token, Object requestPlaylist){

        //1 - it was in PlaylistApi like this, but we want to make the method morre generic to be used with different apis
        //return  given(getRequestSpec()).
        //        body(requestPlaylist).
        //        header("Authorization", "Bearer "+ accessToken).
        //        when().
        //        post("/users/{userid it is like asdasdasdada}/playlist").
        //        then().
        //        spec(getResponseSpec()).
        //        extract().
        //        response();//just need response here, additional assertions will be in test case

        //2
        //return given(getRequestSpec()).
        //        body(requestPlaylist).
        //        header("Authorization", "Bearer "+ token).
        //        when().post(path).
        //       then().spec(getResponseSpec()).
        //        extract().
        //        response();

        //3 -auth() instead of header()
        return given(getRequestSpec()).
                body(requestPlaylist).
                auth().oauth2(token).
        when().post(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }






    //for TokenManager Class
    public static Response postAccount(HashMap<String, String> formParams){
        return given(SpecBuilder.getAccountRequestSpec()).
                formParams(formParams).
        when().post(API + TOKEN). //constants from Route Class
        then().spec(getResponseSpec()).
                extract().
                response();
    }






    public static Response get(String path, String token){
        //1
        //return given(getRequestSpec()).
        //        header("Authorization", "Bearer "+ accessToken).
        //        when().
        //        get("/playlists/" + alreadyCreatedPlayListId).
        //        then().
        //        spec(getResponseSpec()).
        //        extract().
        //        response();//just need response here, additional assertions will be in test case

        //2
        //return given(getRequestSpec()).
        //        header("Authorization", "Bearer "+ token).
        //        when().get(path).
        //        then().spec(getResponseSpec()).
        //        extract().
        //        response();

        //3 -auth() instead of header()
        return given(getRequestSpec()).
                auth().oauth2(token).
        when().get(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }






    //as parameter Playlist requestPlaylist ---> Object requestPlaylist, we need to create reusable methods that can be used across all apis
    public static Response update(String path, String token, Object requestPlaylist){

        //1
        //return  given(getRequestSpec()).
        //        header("Authorization", "Bearer "+ accessToken).
        //        body(requestPlaylist).
        //       when().
        //        put("/playlists/" + playListId).
        //        then().
        //        spec(getResponseSpec())
        //        .extract().
        //        response();//just need response here, additional assertions will be in test case

        //2
        //return given(getRequestSpec()).
        //        header("Authorization", "Bearer "+ token).
        //        body(requestPlaylist).
        //        when().put(path).
        //        then().spec(getResponseSpec()).
        //        extract().
        //        response();

        //3 -auth() instead of header()
        return given(getRequestSpec()).
                auth().oauth2(token).
                body(requestPlaylist).
        when().put(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }
}
