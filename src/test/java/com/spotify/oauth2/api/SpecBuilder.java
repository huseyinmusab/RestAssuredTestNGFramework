package com.spotify.oauth2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.oauth2.api.Route.BASE_PATH;

public class SpecBuilder {

    //2
    //use static keyword for access token
    //static String accessToken = "asdasdasdasdasdadadsdfsdfasfasdfadfasfa";
    //3 - > we remove accesstoken from here and put it in PlaylistApi Class

    /*
    why static?
    Ana sebep: Shared / global state
    Access token tüm testler için aynı
    Test class’ları arasında paylaşılan bir değer
    Her test için yeni bir instance’a bağlı olmasını istemiyoruz.
    Eğer static olmasaydı:

    RequestSpecification spec = new RequestSpec();
    spec.getRequestSpec();

    → Her yerde obje üretmen gerekirdi
    → Token state’i instance’a bağlı olurdu
    → Gereksiz karmaşa
     */


    public static RequestSpecification getRequestSpec() {
        /*
        why this method is static?
        Çünkü bu method:
        Stateless (içinde instance’a ait bir şey yok), Sadece:
        sabit config okuyor
        static accessToken kullanıyor
        Utility / factory method gibi davranıyor
        Bu tarz method’lar için klasik Java yaklaşımı:
        Utility method → static
        Yani bu method:
        Bir objeyi temsil etmiyor
        Bir davranış sağlıyor (RequestSpecification üretmek)
        Bu yüzden:
        RequestSpec.getRequestSpec();

        Stateless = Nesnenin kendi içinde durum (state) tutmaması Yani:
        Instance field’ı yok ya da Olan field’lar davranışı etkilemiyor
        Method çağrıları arasında bir şey hatırlanmıyor
        Her çağrıldığında sıfırdan RequestSpecification oluşturuyor
        Önceki çağrıyla hiçbir bağlantısı yok
        “En son ne yaptım?” diye bir şey hatırlamıyor
         */

        //1-
        // In @Before method was like this in test case
        //         RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
        //         setBaseUri("https://api.spotify.com").
        //         setBasePath("/v1").
        //         addHeader("Authorization", "Bearer "+ accessToken).
        //         setContentType(ContentType.JSON).
        //         log(LogDetail.ALL);
        // requestSpecification = requestSpecBuilder.build();


        //2
        //return as a request specification object direct
        //return new RequestSpecBuilder().
        //        setBaseUri("https://api.spotify.com").
        //        setBasePath("/v1").
        //       addHeader("Authorization", "Bearer "+ accessToken).
        //        setContentType(ContentType.JSON).
        //        log(LogDetail.ALL).build();

        //3- for the error test case with invalid token,
        // we can not set the accessToken here directly
        // because it will also set a second (invalid) token in test case,
        // so there will be 2 headers, it will fail
        // so , we need to remove the accessToken header  here directly
        // return as a request specification object direct
        //return new RequestSpecBuilder().
        //        setBaseUri("https://api.spotify.com").
        //        setBasePath("/v1").
        //        setContentType(ContentType.JSON).
        //        log(LogDetail.ALL).build();

        //4 After using constants from Route Class
        //return new RequestSpecBuilder().
        //        setBaseUri("https://api.spotify.com").
        //        setBasePath(BASE_PATH).
        //        setContentType(ContentType.JSON).
        //        log(LogDetail.ALL).build();

        //5 After adding filter() method from allure-rest-assured dependency
        return new RequestSpecBuilder().
                setBaseUri(System.getProperty("BASE_URI")).
        //        setBaseUri("https://api.spotify.com").
                setBasePath(BASE_PATH).
                setContentType(ContentType.JSON).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).
                build();
    }

    //  ***************************************************************************************   //
    //When running with different env with maven commands on terminal use:
    //mvn test -DBASE_URI="https://api.spotify.com" -DACCOUNT_BASE_URI="https://accounts.spotify.com"
    //this is for terminal with mvn command, if we run this maually on test case, it fails.As a solution:
    //->Run-->Edit Configuration-->Templates-->TestNG-->VM options-->(adding parameter):
    //-ea -Dtestng.dtd.http=true -DBASE_URI="https://api.spotify.com" -DACCOUNT_BASE_URI="https://accounts.spotify.com"


    public static RequestSpecification getAccountRequestSpec() {
        return new RequestSpecBuilder().
                setBaseUri(System.getProperty("ACCOUNT_BASE_URI")).
                //            setBaseUri("https://accounts.spotify.com").
                        setContentType(ContentType.URLENC).
                addFilter(new AllureRestAssured()). //adding filter() method from allure-rest-assured dependency
                log(LogDetail.ALL).
                build();
    }


    public static ResponseSpecification getResponseSpec() {
        //1
        // In @Before method was like this in test case
        //    ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
        //            //expectStatusCode(200).//for the put method it turns 201
        //            //expectContentType(ContentType.JSON). //put method is different
        //            log(LogDetail.ALL);
        //    RestAssured.responseSpecification = responseSpecBuilder.build();

        //2
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}
