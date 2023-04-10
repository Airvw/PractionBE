package com.PractionBE;

import com.PractionBE.dtos.request.UserRequest;
import com.PractionBE.service.DataService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static com.PractionBE.UserAPITest.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAcceptanceTest {

    private UserRequest admin;
    private UserRequest test;
    private UserRequest updateRequest;

    @LocalServerPort
    private int port;

    @Autowired
    private DataService dataService;

    @BeforeEach
    void setUp(){
        RestAssured.port = port;
        dataService.truncateTables();
        admin = new UserRequest("admin", "관리자", "1234", "관리자");
        test = new UserRequest("test@scourt.go.kr", "테스트계정", "1234qwer", "테스트");
        updateRequest = new UserRequest("1234", "테스트변경");
    }

    /**
     * Given 아이디를 생성하면
     * Then 아이디가 생성된다.
     * Then 회원 목록 조회시 생성한 아이디를 조회할 수 있다.
     */
    @Test
    void userCreateTest(){
        createUser(admin);
        List<String> userEmails = getUserNicknames();
        assertThat(userEmails).containsAnyOf("관리자");
    }

    /**
     * Given 2개의 아이디를 생성하고,
     * Then 회원 목록을 조회하면
     * Then 2개의 아이디를 조회할 수 있다.
     */
    @Test
    void getUserNicknamesTest(){
        createUser(admin);
        createUser(test);
        List<String> userEmails = getUserNicknames();
        assertThat(userEmails).containsExactlyInAnyOrder("관리자", "테스트");
    }

    /**
     * Given 아이디를 생성하고,
     * Then 아이디의 닉네임과 비밀번호를 변경하면
     * Then 아이디의 닉네임과 비밀번호가 변경된다.
     */
    @Test
    void updateUserTest(){
        var response = createUser(test);
        Long id = getUserId(response);
        updateUser(id, updateRequest);

        List<String> userEmails = getUserNicknames();
        assertThat(userEmails).containsExactly("테스트변경");
    }


    /**
     * Given 아이디를 생성하고
     * When 아이디를 삭제하면
     * Then 회원 목록 조회시 생성한 아이디를 조회할 수 없다.
     */
    @Test
    void deleteUserTest(){
        dataService.truncateTables();
        var response = createUser(test);
        Long id = getUserId(response);

        deleteUser(id);

        List<String> userEmails = getUserNicknames();
        assertThat(userEmails).doesNotContain("테스트");
    }
}
