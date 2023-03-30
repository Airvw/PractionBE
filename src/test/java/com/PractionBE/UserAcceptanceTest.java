package com.PractionBE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.PractionBE.UserAPITest.createUser;
import static com.PractionBE.UserAPITest.getUserEmails;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserAcceptanceTest {


    private User admin;
    private User test;
    private UserRequest updateRequest;

    @BeforeEach
    void setUp(){
        admin = new User("admin", "관리자", "1234");
        test = new User("test@scourt.go.kr", "test", "1234qwer");
        updateRequest = new UserRequest("update@scourt.go.kr", "1234");
    }

    /**
     * Given 아이디를 생성하면
     * Then 아이디가 생성된다.
     * Then 회원 목록 조회시 생성한 아이디를 조회할 수 있다.
     */
    @Test
    void userCreateTest(){
        createUser(admin);
        List<String> userEmails = getUserEmails();
        assertThat(userEmails).containsAnyOf("admin");
    }

    /**
     * Given 2개의 아이디를 생성하고,
     * Then 회원 목록을 조회하면
     * Then 2개의 아이디를 조회할 수 있다.
     */
    @Test
    void getUserEmailsTest(){
        createUser(admin);
        createUser(test);
        List<String> userEmails = getUserEmails();
        assertThat(userEmails).containsExactlyInAnyOrder("admin", "test");
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
        updateUser(updateRequest, id);

        List<String> userEmails =getUserEmails();
        assertThat(userEmails).containsExactly("update@scourt.go.kr");
    }


    /**
     * Given 아이디를 생성하고
     * When 아이디를 삭제하면
     * Then 회원 목록 조회시 생성한 아이디를 조회할 수 없다.
     */
    @Test
    void deleteUserTest(){
        var response = createUser(test);
        Long id = getUserId(response);

        deleteUser(id);

        List<String> userEmails = getUserEmails();
        assertThat(userEmails).doesNotContain("test@scourt.go.kr");
    }
}
