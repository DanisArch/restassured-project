package tests;

import tests.ApiBaseTest;
import dto.UserDTO;
import wrappers.ApiWrapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserApiTests extends ApiBaseTest {

    private ApiWrapper apiWrapper = new ApiWrapper();

    @Test
    public void testGetUserById() {
        Response response = apiWrapper.getUserById(1);
        assertEquals(200, response.getStatusCode());

        UserDTO user = response.as(UserDTO.class);
        assertNotNull(user);
        assertEquals(1, user.getId());
    }

    @Test
    public void testRegisterUser() {
        UserDTO newUser = new UserDTO();
        newUser.setName("John Doe");
        newUser.setEmail("john@example.com");

        Response response = apiWrapper.createUser(newUser);
        assertEquals(201, response.getStatusCode());

        UserDTO createdUser = response.as(UserDTO.class);
        assertEquals("John Doe", createdUser.getName());
    }

    @Test
    public void testLoginAsUser() {
        /*
        UserDTO newUser = new UserDTO();
        newUser.setName("John Doe");
        newUser.setEmail("john@example.com");

        Response response = apiWrapper.createUser(newUser);
        assertEquals(201, response.getStatusCode());

        UserDTO createdUser = response.as(UserDTO.class);
        assertEquals("John Doe", createdUser.getName());*/
    }
}
