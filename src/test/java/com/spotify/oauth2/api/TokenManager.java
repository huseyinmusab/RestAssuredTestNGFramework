package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {
    /*
    synchronized ==>Java’da synchronized anahtar kelimesi çoklu thread (multithreading) ortamında aynı metoda veya
    koda aynı anda birden fazla thread’in girmesini engellemek için kullanılır.Bu sayede race condition ve veri tutarsızlığı önlenir.
    Aynı anda sadece 1 thread bu metodu çalıştırabilir.Başka bir thread bu metoda girmek isterse:mevcut thread işi bitirene kadar bekler
     */
    private static String access_token;
    private static Instant expiry_time;

    public synchronized static String getToken(){
        //only if the accesToken is null or token is expired,we create a token by this method
        try{
            if(access_token == null || Instant.now().isAfter(expiry_time)){
                System.out.println("Renewing token ...");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
            } else{
                System.out.println("Token is good to use");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("ABORT!!! Failed to get token");
        }
        return access_token;

    }
    //after creating this method which contains renewToken(),
    //we will use this method inside PlayListApi class,
    //no need to use renewToken() in PlayListApi class anymore,
    //so we can change accessmodidfier of the renewToken() from public to private to stay only in this class






    /*
        //1-1
    public static String renewToken() {
        //1
        HashMap<String, String> formParams = new HashMap<String, String>(); //key-value
        formParams.put("client_id", "sdasdasdas");
        formParams.put("client_secret", "sdasdasdas");
        formParams.put("refresh_token", "sdasdasdas");
        formParams.put("grant_type", "refresh_token");
        //RequestSpec can not, but ResponseSpec can be used here from SpecBuilder Class
        //because url is different "https://accounts.spotify.com/api/token"
        Response response = given().
                baseUri("https://accounts.spotify.com").
                contentType(ContentType.URLENC). //x-www-form-urlencoded
                formParams(formParams).
                when().post("/api/token").
                then().spec(getResponseSpec()). //from SpecBuilder
                extract().
                response();
        if (response.statusCode() != 200) {
            throw new RuntimeException("ABORT!!! Renew Token failed");
        }
        return response.path("access_token");

    }
     */
    /*
        public static Response renewToken() {
        //1-2  changing return type from String to repsonse to be used in getToken() method in this class
        HashMap<String, String> formParams = new HashMap<String, String>(); //key-value
        formParams.put("client_id", "sdasdasdas");
        formParams.put("client_secret", "sdasdasdas");
        formParams.put("refresh_token", "sdasdasdas");
        formParams.put("grant_type", "refresh_token");

        //RequestSpec can not, but ResponseSpec can be used here from SpecBuilder Class
        //because url is different "https://accounts.spotify.com/api/token"
        Response response = given().
                baseUri("https://accounts.spotify.com").
                contentType(ContentType.URLENC). //x-www-form-urlencoded
                        formParams(formParams).
                when().post("/api/token").
                then().spec(getResponseSpec()). //from SpecBuilder
                        extract().
                response();
        if (response.statusCode() != 200) {
            throw new RuntimeException("ABORT!!! Renew Token failed");
        }
        return response;

    }
     */
    /*
        private static Response renewToken() {
        //1-3  changing return type from String to repsonse to be used in getToken() method in this class
        HashMap<String, String> formParams = new HashMap<String, String>(); //key-value
        formParams.put("client_id", "sdasdasdas");
        formParams.put("client_secret", "sdasdasdas");
        formParams.put("refresh_token", "sdasdasdas");
        formParams.put("grant_type", "refresh_token");

        //RequestSpec can not, but ResponseSpec can be used here from SpecBuilder Class
        //because url is different "https://accounts.spotify.com/api/token"
        Response response = given().
                baseUri("https://accounts.spotify.com").
                contentType(ContentType.URLENC). //x-www-form-urlencoded
                        formParams(formParams).
                log().all().
                when().post("/api/token").
                then().spec(getResponseSpec()). //from SpecBuilder
                        extract().
                response();
        if (response.statusCode() != 200) {
            throw new RuntimeException("ABORT!!! Renew Token failed");
        }
        return response;
    }
     */
    //2-1
    private static Response renewToken() {
        HashMap<String, String> formParams = new HashMap<String, String>(); //key-value
        formParams.put("client_id", ConfigLoader.getInstance().getClientId());
        formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
        formParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
        formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());

        Response response = RestResource.postAccount(formParams);

       if(response.statusCode() != 200){
            throw new RuntimeException("ABORT!!! Renew Token failed");
        }
        return response;
    }

}
