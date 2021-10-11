//package com.example.springboot.Test;
//
//import com.example.springboot.dao.User.User;
//import com.example.springboot.dao.repository.UserRepository;
//import org.apache.catalina.connector.Response;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.support.AnnotationConfigContextLoader;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(
//        classes = { ConfigTest.class, PersistenceJPAConfig.class },
//        loader = AnnotationConfigContextLoader.class)
//public class ChangePasswordApiTest {
//    private final String URL_PREFIX = "http://localhost:8080/";
//    private final String URL = URL_PREFIX + "/user/updatePassword";
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    FormAuthConfig formConfig = new FormAuthConfig(
//            URL_PREFIX + "/login", "username", "password");
//
//    @Before
//    public void init() {
//        User user = userRepository.findByUsername("test@test.com");
//        if (user == null) {
//            user = new User();
//            user.setName("Test");
//            user.setPassword(passwordEncoder.encode("test"));
//            userRepository.save(user);
//        } else {
//            user.setPassword(passwordEncoder.encode("test"));
//            userRepository.save(user);
//        }
//    }
//}
//    @Test
//    public void givenLoggedInUser_whenChangingPassword_thenCorrect() {
//        RequestSpecification request = RestAssured.given().auth()
//                .form("test@test.com", "test", formConfig);
//
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("oldpassword", "test");
//        params.put("password", "newtest");
//
//        Response response = request.with().params(params).post(URL);
//
//        assertEquals(200, response.statusCode());
//        assertTrue(response.body().asString().contains("Password updated successfully"));
//    }
//    @Test
//    public void givenWrongOldPassword_whenChangingPassword_thenBadRequest() {
//        RequestSpecification request = RestAssured.given().auth()
//                .form("test@test.com", "test", formConfig);
//
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("oldpassword", "abc");
//        params.put("password", "newtest");
//
//        Response response = request.with().params(params).post(URL);
//
//        assertEquals(400, response.statusCode());
//        assertTrue(response.body().asString().contains("Invalid Old Password"));
//    }
//    @Test
//    public void givenNotAuthenticatedUser_whenChangingPassword_thenRedirect() {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("oldpassword", "abc");
//        params.put("password", "xyz");
//
//        Response response = RestAssured.with().params(params).post(URL);
//
//        assertEquals(302, response.statusCode());
//        assertFalse(response.body().asString().contains("Password updated successfully"));
//    }
//}
