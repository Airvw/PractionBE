package com.PractionBE;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAPITest {

    public static ExtractableResponse<Response> createUser(User user){
        ExtractableResponse<Response> response =
                RestAssured.given().log().all()
                        .body(user)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .when().post("/users")
                        .then().log().all()
                        .extract();
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        return response;
    }

    public static List<String> getUserEmails(){
        List<String> userIds =
                RestAssured.given().log().all()
                        .when().get("/users")
                        .then().log().all()
                        .extract().jsonPath().getList("email", String.class);
        return userIds;
    }

    public static UUID generateUUID(){
        return UUID.randomUUID();
    }

}
