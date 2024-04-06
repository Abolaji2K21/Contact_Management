package africa.semicolon.services;

import africa.semicolon.contactException.BigContactException;
import africa.semicolon.contactException.UserNotFoundException;
import africa.semicolon.data.models.Contact;
import africa.semicolon.data.models.User;
import africa.semicolon.data.repositories.ContactRepository;
import africa.semicolon.data.repositories.UserRepository;
import africa.semicolon.dtos.requests.*;
import africa.semicolon.dtos.response.CreateContactResponse;
import africa.semicolon.dtos.response.DeleteContactResponse;
import africa.semicolon.dtos.response.EditContactResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ContactServiceImplTest {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        contactRepository.deleteAll();
    }

    @Test
    public void testCreateContact_When_UserIsNotRegistered() {
        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setUsername("penisup");
        createContactRequest.setPhoneNumber("08165269244");
        createContactRequest.setFirstname("PenIs");
        createContactRequest.setLastname("Up");
        createContactRequest.setEmail("PenIsUp@gmail.com");

        assertThrows(BigContactException.class, () -> contactService.createContact(createContactRequest));
    }

    @Test
    public void testCreateContact_When_UserIsRegisteredButNotLoggedIn() {
        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.setFirstname("penIs");
        registerRequest.setLastname("Up");
        registerRequest.setUsername("PenIs");
        registerRequest.setPassword("Holes");
        userService.register(registerRequest);

        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setUsername("penisup");
        createContactRequest.setPhoneNumber("08165269244");
        createContactRequest.setFirstname("PenIs");
        createContactRequest.setLastname("Up");
        createContactRequest.setEmail("PenIsUp@gmail.com");

        assertThrows(BigContactException.class, () -> contactService.createContact(createContactRequest));
    }

    @Test
    public void testCreateContact_When_UserIsRegisteredAndLoggedIn() {
        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.setFirstname("penIs");
        registerRequest.setLastname("Up");
        registerRequest.setUsername("penisup");
        registerRequest.setPassword("Holes");
        userService.register(registerRequest);

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("penisup");
        loginRequest.setPassword("Holes");
        userService.login(loginRequest);

        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setUsername("penisup");
        createContactRequest.setPhoneNumber("08165269244");
        createContactRequest.setFirstname("PenIs");
        createContactRequest.setLastname("Up");
        createContactRequest.setEmail("PenIsUp@gmail.com");


        CreateContactResponse response = contactService.createContact(createContactRequest);
        assertEquals("08165269244", response.getPhoneNumber());
        assertEquals("PenIs", response.getFirstName());
        assertEquals("Up", response.getLastName());
        assertEquals("PenIsUp@gmail.com", response.getEmail());
    }

    @Test
    public void testEditContact_When_UserIsNotLoggedIn() {
        EditContactRequest editContactRequest = new EditContactRequest();
        editContactRequest.setContactId(editContactRequest.getContactId());
        editContactRequest.setUsername("penisup");
        editContactRequest.setPhoneNumber("08165269244");
        editContactRequest.setFirstname("PenIs");
        editContactRequest.setLastname("Up");
        editContactRequest.setEmail("PenIsUp@gmail.com");


        assertThrows(BigContactException.class, () -> contactService.editContact(editContactRequest));
    }

    @Test
    public void testEditContact_When_UserIsLoggedInButNotAuthorized() {
        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.setFirstname("penIs");
        registerRequest.setLastname("Up");
        registerRequest.setUsername("penisup");
        registerRequest.setPassword("Holes");
        userService.register(registerRequest);

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("penisup");
        loginRequest.setPassword("Holes");
        userService.login(loginRequest);

        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setUsername("penisup");
        createContactRequest.setPhoneNumber("08165269244");
        createContactRequest.setFirstname("PenIs");
        createContactRequest.setLastname("Up");
        createContactRequest.setEmail("PenIsUp@gmail.com");
        CreateContactResponse response = contactService.createContact(createContactRequest);

        EditContactRequest editContactRequest = new EditContactRequest();
        editContactRequest.setUsername("penisdown");
        editContactRequest.setContactId(response.getContactId());
        editContactRequest.setPhoneNumber("08165269244");
        editContactRequest.setFirstname("PenIs");
        editContactRequest.setLastname("Down");
        editContactRequest.setEmail("PenIsUp@gmail.com");

        assertThrows(BigContactException.class, () -> contactService.editContact(editContactRequest));
    }

    @Test
    public void testEditContact_When_UserIsLoggedInAndAuthorized() {
        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.setFirstname("penIs");
        registerRequest.setLastname("Up");
        registerRequest.setUsername("penisup");
        registerRequest.setPassword("Holes");
        userService.register(registerRequest);

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("penisup");
        loginRequest.setPassword("Holes");
        userService.login(loginRequest);

        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setUsername("penisup");
        createContactRequest.setPhoneNumber("08165269244");
        createContactRequest.setFirstname("PenIs");
        createContactRequest.setLastname("Up");
        createContactRequest.setEmail("PenIsUp@gmail.com");
        CreateContactResponse response = contactService.createContact(createContactRequest);

        EditContactRequest editContactRequest = new EditContactRequest();
        editContactRequest.setUsername("penisup");
        editContactRequest.setContactId(response.getContactId());
        editContactRequest.setPhoneNumber("08165269244");
        editContactRequest.setFirstname("PenIs");
        editContactRequest.setLastname("Down");
        editContactRequest.setEmail("PenIsUp@gmail.com");


        EditContactResponse editContactResponse = contactService.editContact(editContactRequest);
        assertEquals(response.getContactId(), editContactResponse.getContactId());
        assertEquals("08165269244", editContactResponse.getPhoneNumber());
        assertEquals("PenIs", editContactResponse.getFirstName());
        assertEquals("Down", editContactResponse.getLastName());
        assertEquals("PenIsUp@gmail.com", editContactResponse.getEmail());
    }

    @Test
    public void testingDeleteContactWhenUserIsNotRegistered() {
        DeleteContactRequest deleteContactRequest = new DeleteContactRequest();
        deleteContactRequest.setUsername("penisup");
        deleteContactRequest.setContactId("theyplaymyfans");

        assertThrows(BigContactException.class, () -> contactService.deleteContact(deleteContactRequest));
    }

    @Test
    public void testingDeleteContactWhenUserIsRegisteredButNotLoggedIn() {
        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.setFirstname("penIs");
        registerRequest.setLastname("Up");
        registerRequest.setUsername("penisup");
        registerRequest.setPassword("Holes");
        userService.register(registerRequest);

        DeleteContactRequest deleteContactRequest = new DeleteContactRequest();
        deleteContactRequest.setUsername("penisup");
        deleteContactRequest.setContactId("Theyplaymyfans");

        assertThrows(BigContactException.class, () -> contactService.deleteContact(deleteContactRequest));
    }

    @Test
    public void testingDeleteContactWhenUserIsRegisteredAndLoggedIn() {
        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.setFirstname("penIs");
        registerRequest.setLastname("Up");
        registerRequest.setUsername("penisup");
        registerRequest.setPassword("Holes");
        userService.register(registerRequest);

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("penisup");
        loginRequest.setPassword("Holes");
        userService.login(loginRequest);

        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setUsername("penisup");
        createContactRequest.setPhoneNumber("08165269244");
        createContactRequest.setFirstname("PenIs");
        createContactRequest.setLastname("Up");
        createContactRequest.setEmail("PenIsUp@gmail.com");
        CreateContactResponse response = contactService.createContact(createContactRequest);

        DeleteContactRequest deleteContactRequest = new DeleteContactRequest();
        deleteContactRequest.setUsername("penisup");
        deleteContactRequest.setContactId(response.getContactId());

        DeleteContactResponse deleteContactResponse = contactService.deleteContact(deleteContactRequest);
        assertEquals(response.getContactId(), deleteContactResponse.getContactId());
        assertTrue(deleteContactResponse.isDeleted());
    }

    @Test
    void testThatAnotherUserCannotDeleteContact() {
        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.setFirstname("penIs");
        registerRequest.setLastname("Up");
        registerRequest.setUsername("penisup");
        registerRequest.setPassword("Holes");
        userService.register(registerRequest);

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("penisup");
        loginRequest.setPassword("Holes");
        userService.login(loginRequest);

        CreateContactRequest createContactRequest = new CreateContactRequest();
        createContactRequest.setUsername("penisup");
        createContactRequest.setPhoneNumber("08165269244");
        createContactRequest.setFirstname("PenIs");
        createContactRequest.setLastname("Up");
        createContactRequest.setEmail("PenIsUp@gmail.com");
        CreateContactResponse response = contactService.createContact(createContactRequest);

        RegisterUserRequest registerRequest2 = new RegisterUserRequest();
        registerRequest2.setFirstname("pen");
        registerRequest2.setLastname("isdown");
        registerRequest2.setUsername("penisdown");
        registerRequest2.setPassword("WrongHole");
        userService.register(registerRequest2);

        LoginUserRequest loginRequest2 = new LoginUserRequest();
        loginRequest2.setUsername("penisdown");
        loginRequest2.setPassword("WrongHole");
        userService.login(loginRequest2);

        DeleteContactRequest deleteContactRequest = new DeleteContactRequest();
        deleteContactRequest.setUsername("penisdown");
        deleteContactRequest.setContactId(response.getContactId());

        assertThrows(BigContactException.class, () -> contactService.deleteContact(deleteContactRequest));
    }

}
