package com.PractionBE;

import com.PractionBE.dtos.request.UserRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAPITest {

    public static ExtractableResponse<Response> createUser(UserRequest userRequest){
        var response =
                RestAssured.given().log().all()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(userRequest)
                        .when().post("/users")
                        .then().log().all()
                        .extract();
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        return response;
    }

    public static List<String> getUserNicknames(){
        List<String> userIds =
                RestAssured.given().log().all()
                        .when().get("/users")
                        .then().log().all()
                        .extract().jsonPath().getList("nickName", String.class);
        return userIds;
    }

    public static ExtractableResponse<Response> updateUser(Long id, UserRequest updateRequest){
        var response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(updateRequest)
                .when().put("/users/" + id)
                .then().log().all()
                .extract();
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        return response;
    }

    public static ExtractableResponse<Response> deleteUser(Long id){
        var response = RestAssured.given().log().all()
                .when().delete("/users/" + id)
                .then().log().all()
                .extract();
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
        return response;
    }

    public static Long getUserId(ExtractableResponse<Response> response){
        return response.jsonPath().getLong("id");
    }

    public static UUID generateUUID(){
        return UUID.randomUUID();
    }

}
