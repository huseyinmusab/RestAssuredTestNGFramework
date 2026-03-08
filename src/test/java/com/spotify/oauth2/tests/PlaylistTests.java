package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
 public class PlaylistTests extends BaseTest {

//    @Story("Create a playlist story")
//    @Link("https://example.org")
//    @Link(name = "allure", type = "mylink")
//    @TmsLink("12345")
//    @Issue("1234567")
//    @Description("this is the description")
//    @Test(description = "should be able to create a playlist")



    //0-1
    //RequestSpecification requestSpecification;
    //ResponseSpecification responseSpecification;
    //String accessToken = "asdasdasdasdasdadadsdfsdfasfasdfadfasfa";
    //=====>Removing to spec builder

    //0-2
    //@BeforeClass
    //public void beforeClass(){
    //    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
    //            setBaseUri("https://api.spotify.com").
    //            setBasePath("/v1").
    //            addHeader("Authorization", "Bearer "+ accessToken).
    //           setContentType(ContentType.JSON).
    //            log(LogDetail.ALL);
    //    requestSpecification = requestSpecBuilder.build();
    //    ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
    //            //expectStatusCode(200).//for the put method it turns 201
    //            //expectContentType(ContentType.JSON). //put method is different
    //            log(LogDetail.ALL);
    //    RestAssured.responseSpecification = responseSpecBuilder.build();
    //}

    //=====>Removing to spec builder under api folder




    //COMMON METHODS FOR SHORTCUT, (can be actually used in another class if they are not related with this class,e.g Error)
    //1-4
    // this is for a building a playList request method, short cut
    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public){

        return Playlist.builder().  //it is used with @Builder in Playlist Class under the pojo,ERROR with @Getter @Setter
                name(name).
                description(description).
                _public(_public).
                build();
    }
    //2-5
    //this is a common method for only assertion
    @Step //steps are method level for a good debugging purpose in allure report
    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist){
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
    }
    //2-6
    @Step //steps are method level for a good debugging purpose in allure report
    public void assertStatusCode(int actualStatusCode, StatusCode statusCode){
        assertThat(actualStatusCode, equalTo(statusCode.code));
    }
    //for error common method
    @Step //steps are method level for a good debugging purpose in allure report
    public void assertError(Error responseErr, StatusCode statusCode){
        assertThat(responseErr.getError().getStatus(), equalTo(statusCode.code));
        assertThat(responseErr.getError().getMessage(), equalTo(statusCode.msg));
    }






    //allure serve target/allure-results ==>generate allure reports by this command
    @Description("this is a description")//for allure test report
    @Issue("120")//generates a link to navigate when error happens
    @Test(description = "should be able to create a playlist description for allure reports")
    public void ShouldBeAbleToCreateAPlaylist(){
        //REQUEST PART
        //1-2
        //Playlist requestPlaylist = new Playlist();
        //requestPlaylist.setName("New Playlist");
        //requestPlaylist.setDescription("New Playlist description");
        //requestPlaylist.setPublic(false);

        //1-3
        //to avoid the repeatable requestPlayList object over and over the pojo class,
        //remove void as return type
        //and instead use the Same class , like PlayList
        //public void setDescription(String description) {
        //    this.description = description;
        //}
        //public PlayList setDescription(String description) {
        //    this.description = description;
        //    return this;//=> with this, this method returns the OBJECT of the same class,
        //in this framework there is lombok
        //when you create your pojo class you can consider this way,
        //}
        //Playlist requestPlaylist = new Playlist().
        //        setName("New Playlist").
        //        setDescription("New Playlist description").
         //       setPublic(false);

        //1-4 - Using playListBuilder at the top
        //Playlist requestPlaylist = playlistBuilder("New Playlist","New Playlist description",false);

        //1-5 - Using playListBuilder at the top with JavaFaker
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);



        //RESPONSE PART
        //2-1
        //removing this with 2nd step , we set all we need with pojos instead of this hard code String
        //String payload = "{\n" +
        //        "   \"workspace\": {\n" +
        //        "                   \"name\": \"New Playlist\",\n" +
        //        "                   \"description\": \"New Playlist description\",\n" +
        //        "                   \"public\": false\n" +
        //        "    }" +
        //        "}";

        //2-1
        //given(requestSpecification).
        //        body(payload).
        //        when().
        //        post("/users/{userid it is like asdasdasdada}/playlist").
        //        then().
        //        spec(responseSpecification).
        //        assertThat().
        //        statusCode(201).
        //        body("name", equalTo("New Playlist"),
        //                "description", equalTo("New Playlist description"),
        //                "public", equalTo(false));;

        //2-2 -updating with 2nd step, no need to use payload, instead we can use requestPlayList and deserialization
        //Playlist responsePlayList = given(requestSpecification).
        //        body(requestPlaylist). //==>requestPlaylist object 2nd step at the top instead of payload
        //        when().
        //        post("/users/{userid it is like asdasdasdada}/playlist").
        //        then().
        //        spec(responseSpecification).
        //        assertThat().
        //        statusCode(201).
        //        extract().
        //        response().
        //        as(Playlist.class);
        //        assertThat(requestPlaylist.getName(),equalTo(requestPlaylist.getName()));
        //        assertThat(requestPlaylist.getDescription(),equalTo(requestPlaylist.getDescription()));
        //        assertThat(requestPlaylist.get_public(),equalTo(requestPlaylist.get_public()));

        //2-3
        //after creating spec builder with getRequestSpec() and getResponseSpec()
        //Playlist responsePlayList = given(getRequestSpec()).
        //        body(requestPlaylist).
        //        when().
        //        post("/users/{userid it is like asdasdasdada}/playlist").
        //        then().
        //        spec(getResponseSpec()).
        //        assertThat().
        //        statusCode(201).
        //       extract().
        //        response().
        //        as(Playlist.class);
        //assertThat(responsePlayList.getName(),equalTo(requestPlaylist.getName()));
        //assertThat(responsePlayList.getDescription(),equalTo(requestPlaylist.getDescription()));
        //assertThat(responsePlayList.get_public(),equalTo(requestPlaylist.get_public()));



        //2-4 - By calling generic (reusable) methods , they are created in PlayListApi class to be used in tests
        //now we need to pass requestPlaylist body for post method in PlaylistApi
        //Response response = PlaylistApi.post(requestPlaylist);
        //assertThat(response.statusCode(),equalTo(201));

        //Playlist responsePlayList = response.as(Playlist.class);//de-serialization here
        //assertThat(responsePlayList.getName(),equalTo(requestPlaylist.getName()));
        //assertThat(responsePlayList.getDescription(),equalTo(requestPlaylist.getDescription()));
        //assertThat(responsePlayList.get_public(),equalTo(requestPlaylist.get_public()));

        //2-5 - After creating shortcut assertion method
        //Response response = PlaylistApi.post(requestPlaylist);
        //assertThat(response.statusCode(),equalTo(201));
        //assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);

        //2-6 - After creating shortcut assertion method for status
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);

    }

    @Test
    public void ShouldBeAbleToGetAPlaylist(){

        //REQUEST PART
        //1-2
        // getting values form pojos
        //Playlist requestPlaylist = new Playlist();
        //requestPlaylist.setName("New Playlist");
        //requestPlaylist.setDescription("New Playlist description");
        //requestPlaylist.setPublic(false);

        //1-3
        //to avoid the repeatable requestPlayList object over and over the pojo class, remove void as return type
        //and instead use the Same class , like PlayList
        //public void setDescription(String description) {
        //    this.description = description;
        //}
        //public PlayList setDescription(String description) {
        //    this.description = description;
        //    return this;//=> with this, this method returns the OBJECT of the same class,
        //in this framework there is lombok
        //when you create your pojo class you can consider this way,
        //}
        //Playlist requestPlaylist = new Playlist().
        //        setName("New Playlist").
        //        setDescription("New Playlist description").
        //        setPublic(false);



        //1-4 - Using playListBuilder at the top
        Playlist requestPlaylist = playlistBuilder("New Playlist","New Playlist description",false);


        //2-1
        //given(requestSpecification).
        //        when().
        //        get("/playlist/{createdPlayListId}").
        //        then().
        //        spec(responseSpecification).
        //        assertThat().
        //        statusCode(200).
        //        body("name", equalTo("New Playlist"),
        //                "description", equalTo("New Playlist description"),
        //                "public", equalTo(false));;


        //2-2
        //updating with 2nd step, no need to use payload, instead we can use requestPlayList and deserialization
        //Playlist responsePlayList = given(requestSpecification).
        //        when().
        //        get("/playlist/{createdPlayListId}").
        //        then().
        //        spec(responseSpecification).
        //        assertThat().
        //        statusCode(200).
        //        extract().
        //        response().
        //        as(Playlist.class);
        //assertThat(requestPlaylist.getName(),equalTo(requestPlaylist.getName()));
        //assertThat(requestPlaylist.getDescription(),equalTo(requestPlaylist.getDescription()));
        //assertThat(requestPlaylist.get_public(),equalTo(requestPlaylist.get_public()));

        //2-3
        //after creating spec builder with getRequestSpec() and getResponseSpec()
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
        //assertThat(responsePlayList.getName(),equalTo(requestPlaylist.getName()));
        //assertThat(responsePlayList.getDescription(),equalTo(requestPlaylist.getDescription()));
        //assertThat(responsePlayList.get_public(),equalTo(requestPlaylist.get_public()));

        //2-4 -By calling generic (reusable) methods , they are created in PlayListApi class to be used in tests
        //now we need to pass playListId for get method in PlaylistApi
        //Response response = PlaylistApi.get("playListIdlike46radfg89");
        //assertThat(response.statusCode(),equalTo(200));

        //Playlist responsePlayList = response.as(Playlist.class);// de-serialization
        //assertThat(responsePlayList.getName(),equalTo(requestPlaylist.getName()));
        //assertThat(responsePlayList.getDescription(),equalTo(requestPlaylist.getDescription()));
        //assertThat(responsePlayList.get_public(),equalTo(requestPlaylist.get_public()));

        //2-5 - by calling playListId from "data.properties" under the resources file and DataLoader Class
        //now we need to pass playListId for get method in PlaylistApi
        //Response response = PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
        //assertThat(response.statusCode(),equalTo(200));
        //Playlist responsePlayList = response.as(Playlist.class);// de-serialization
        //assertThat(responsePlayList.getName(),equalTo(requestPlaylist.getName()));
        //assertThat(responsePlayList.getDescription(),equalTo(requestPlaylist.getDescription()));
        //assertThat(responsePlayList.get_public(),equalTo(requestPlaylist.get_public()));

        //2-5 -2-6  After creating shortcut assertion method and assertion method for status
        Response response = PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);

    }






    @Test
    public void ShouldBeAbleToUpdateAPlaylist(){

        //1-2 -getting values form pojos
        //Playlist requestPlaylist = new Playlist();
        //requestPlaylist.setName("New Playlist");
        //requestPlaylist.setDescription("New Playlist description");
        //requestPlaylist.setPublic(false);

        //1-3
        //to avoid the repeatable requestPlayList object over and over the pojo class, remove void as return type
        //and instead use the Same class , like PlayList
        //public void setDescription(String description) {
        //    this.description = description;
        //}
        //public PlayList setDescription(String description) {
        //    this.description = description;
        //    return this;//=> with this, this method returns the OBJECT of the same class,
        //in this framework there is lombok
        //when you create your pojo class you can consider this way,
        //}
        //Playlist requestPlaylist = new Playlist().
        //        setName("New Playlist").
        //        setDescription("New Playlist description").
        //        setPublic(false);

        //1-4 - Using playListBuilder at the top
        //Playlist requestPlaylist = playlistBuilder("New Playlist","New Playlist description",false);

        //1-5 - Using playListBuilder at the top with JavaFaker
        Playlist requestPlaylist = playlistBuilder(generateName(),generateDescription(),false);





        //2-1 - removing this with 2nd step , we set all we need with pojos
        //String payload = "{\n" +
        //        "   \"workspace\": {\n" +1
        //        "                   \"name\": \"New Playlist\",\n" +
        //        "                   \"description\": \"New Updated Playlist description\",\n" +
        //        "                   \"public\": false\n" +
        //        "    }" +
        //        "}";


        //2-1
        //given(requestSpecification).
        //        body(payload).
        //        when().
        //        put("/playlist/asdasdasdascce").
        //        then().
        //        spec(responseSpecification).
        //        assertThat().
        //        statusCode(200);

        //2-2 - updating with 2nd step, no need to use payload, instead we can use requestPlayList and deserialization
        //given(requestSpecification).
        //        body(requestPlaylist).
        //        when().
        //        put("/playlist/asdasdasdascce").
        //        then().
        //        spec(responseSpecification).
        //        assertThat().
        //        statusCode(200);

        //2-3
        //after creating spec builder with getRequestSpec() and getResponseSpec()
        //given(getRequestSpec()).
        //        body(requestPlaylist).
        //        when().
        //        put("/playlist/asdasdasdascce").
        //        then().
        //        spec(getResponseSpec()).
        //        assertThat().
        //        statusCode(200);
        //2-3
        //we do not need body assertion here, because put method has no body, only status code check is enough

        //2-4 -By calling generic (reusable) methods , they are created in PlayListApi class to be used in tests
        //now we need to pass as parameters playListId and requestPlayList for to be updated body
        //Response response = PlaylistApi.update("playListIdlike4689",requestPlaylist);
        //assertThat(response.statusCode(),equalTo(200));
        //Response responsePlayList = response.as(Response.class);//de-serialization which is we do not need , becasue we dont have body

        //2-5 - by calling playListId from "data.properties" under the resources file and DataLoader Class
        //now we need to pass playListId for get method in PlaylistApi
        //Response response = PlaylistApi.update(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        //assertThat(response.statusCode(),equalTo(200));

        //2-5 2-6 with assertion method
        Response response = PlaylistApi.update(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);



    }

    @Story("Create a playlist story")
    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithName(){
        Playlist requestPlaylist = playlistBuilder("", generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_400);
        assertError(response.as(Error.class), StatusCode.CODE_400);
    }

    @Story("Create a playlist story")
    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken(){
        String invalid_token = "12345";
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.post(invalid_token, requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_401);
        assertError(response.as(Error.class), StatusCode.CODE_401);
    }


}
