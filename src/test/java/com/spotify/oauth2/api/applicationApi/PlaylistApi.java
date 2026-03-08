package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static com.spotify.oauth2.api.TokenManager.getToken;
//import static com.spotify.oauth2.api.TokenManager.renewToken;
import static io.restassured.RestAssured.given;

public class PlaylistApi {

    //this is for reusable methods - GENERIC METHODS - to be used in tests


    //5
    //static String accessToken = "asdasdasdasdasdadadsdfsdfasfasdfadfasfa";

    public static Response post(Playlist requestPlaylist){
        //1
        //it was in this structure in test case
        //Playlist responsePlayList = given(getRequestSpec()).
        //        body(requestPlaylist).
        //        when().
        //        post("/users/useridc8u86gj65fg9gw3/playlist").
        //        then().
        //        spec(getResponseSpec()).
        //        assertThat().
        //        statusCode(201).
        //        extract().
        //        response().
        //        as(Playlist.class);

        //2
        //return  given(getRequestSpec()).
        //        body(requestPlaylist).
        //        when().
        //        post("/users/useridc8u86gj65fg9gw3/playlist").
        //        then().
        //        spec(getResponseSpec()).
        //        extract().
        //        response();//just need response here,
        //        additional assertions will be in test case

        //3
        //after removing header (accessToken) from the RequestSpecification in SpecBuilder Class,
        //we add here
        //return  given(getRequestSpec()).
        //        body(requestPlaylist).
        //        header("Authorization", "Bearer "+ accessToken).
        //        when().
        //        post("/users/useridc8u86gj65fg9gw3}/playlist").
        //        then().
        //        spec(getResponseSpec()).
        //        extract().
        //       response();//just need response here, additional assertions will be in test case

        //4 ==> look at the RestSources Class under the applicationApi folder,
        // now we did method more generic and dynamic
        //return RestResource.post("/users/useridc8u86gj65fg9gw3/playlist",accessToken,requestPlaylist);

        //5 After creating renewToken() method in TokenManager Class, we can update step 4 by deleting
        //static String accessToken = "asdasdasdasdasdadadsdfsdfasfasdfadfasfa"; top of this class
        //return RestResource.post("/users/useridc8u86gj65fg9gw3/playlist", TokenManager.renewToken(),requestPlaylist);
        //or
        //return RestResource.post("/users/useridc8u86gj65fg9gw3/playlist",renewToken(),requestPlaylist);

        //6 After creating getToken() in TokenManager Class, should be used getToken()
        //return RestResource.post("/users/useridc8u86gj65fg9gw3/playlist",getToken(),requestPlaylist);

        //7 After creating constant values in Route Class to not get the duplications of the url
        //return RestResource.post(USERS + "/useridc8u86gj65fg9gw3"+PLAYLISTS, getToken(),requestPlaylist);

        //8 After creating ConfigLoader Class and config.properties file
        return RestResource.post(USERS + ConfigLoader.getInstance().getUser()+PLAYLISTS, getToken(),requestPlaylist);
    }


//    @Step
//    public static Response post(Playlist requestPlaylist){
//        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUser()
//                + PLAYLISTS, getToken(), requestPlaylist);
//    }


    //overloaded post() method for the error test case
    //we dont need to add here accessToken header becasue we are already passing the header as an argument for this method
    public static Response post(String token, Playlist requestPlaylist){
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUser() + PLAYLISTS, token, requestPlaylist);
    }





    public static Response get(String alreadyCreatedPlayListId){
        //1
        //Playlist responsePlayList = given(getRequestSpec()).
        //        when().
        //        get("/playlist/{createdPlayListId}").
        //        then().
        //        spec(getResponseSpec()).
        //        assertThat().
        //        statusCode(200).
        //        extract().
        //        response().
        //        as(Playlist.class);

        //2
        //return given(getRequestSpec()).
        //        when().
        //        get("/playlists/" + alreadyCreatedPlayListId).
        //        then().
        //        spec(getResponseSpec()).
        //        extract().
        //        response();//just need response here, additional assertions will be in test case

        //3
        //after removing header (accessToken) from the RequestSpecification in SpecBuilder Class,(expalined there) we add here
        //return given(getRequestSpec()).
        //        header("Authorization", "Bearer "+ accessToken).
        //        when().
        //        get("/playlists/" + alreadyCreatedPlayListId).
        //        then().
        //        spec(getResponseSpec()).
        //        extract().
        //        response();//just need response here, additional assertions will be in test case

        //4 ==> look at the RestSources Class under the applicationApi folder, now we did method more generic and dynamic
        //return RestResource.get("/playlists/" + alreadyCreatedPlayListId,accessToken);

        //5 After creating renewToken() method in TokenManager Class, we can update step 4 by deleting
        //static String accessToken = "asdasdasdasdasdadadsdfsdfasfasdfadfasfa"; top of this class
        //return RestResource.get("/playlists/" + alreadyCreatedPlayListId,renewToken());

        //6 After creating getToken() in TokenManager Class, should be used getToken()
        //return RestResource.get("/playlists/" + alreadyCreatedPlayListId,getToken());

        //7 After creating constant values in Route Class to not get the duplications of the url
        return RestResource.get(PLAYLISTS+ "/" + alreadyCreatedPlayListId, getToken());

    }






    public static Response update(String playListId, Playlist requestPlaylist){
        //1
        //return  given(getRequestSpec()).
        //        body(requestPlaylist).
        //        when().
        //        put("/playlists/" + playListId).
        //        then().
        //        spec(getResponseSpec())
        //        .extract().
        //        response();//just need response here, additional assertions will be in test case

        //2-3
        //after removing header (accessToken) from the RequestSpecification in SpecBuilder Class,(expalined there) we add here
        //return  given(getRequestSpec()).
        //        header("Authorization", "Bearer "+ accessToken).
        //        body(requestPlaylist).
        //        when().
        //        put("/playlists/" + playListId).
        //        then().
        //        spec(getResponseSpec())
        //        .extract().
        //        response();//just need response here, additional assertions will be in test case

        //4 ==> look at the RestSources Class under the applicationApi folder,  now we did method more generic and dynamic
        //return RestResource.update("/playlists/" + playListId,accessToken,requestPlaylist);

        //5 After creating renewToken() method in TokenManager Class, we can update step 4 by deleting
        //static String accessToken = "asdasdasdasdasdadadsdfsdfasfasdfadfasfa"; top of this class
        //return RestResource.update("/playlists/" + playListId,renewToken(),requestPlaylist);

        //6 After creating getToken() in TokenManager Class, should be used getToken()
        //return RestResource.update("/playlists/" + playListId,getToken(),requestPlaylist);

        //7 After creating constant values in Route Class to not get the duplications of the url
        return RestResource.update(PLAYLISTS+ "/" + playListId,  getToken(),requestPlaylist);

    }
}
