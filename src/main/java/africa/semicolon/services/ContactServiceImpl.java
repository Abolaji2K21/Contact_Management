package africa.semicolon.services;

import africa.semicolon.contactException.BigContactException;
import africa.semicolon.contactException.UserNotFoundException;
import africa.semicolon.data.models.Contact;
import africa.semicolon.data.models.User;
import africa.semicolon.data.repositories.ContactRepository;
import africa.semicolon.data.repositories.UserRepository;
import africa.semicolon.dtos.requests.CreateContactRequest;
import africa.semicolon.dtos.requests.DeleteContactRequest;
import africa.semicolon.dtos.requests.EditContactRequest;
import africa.semicolon.dtos.response.CreateContactResponse;
import africa.semicolon.dtos.response.DeleteContactResponse;
import africa.semicolon.dtos.response.EditContactResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static africa.semicolon.utils.Mapper.*;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Override
    public CreateContactResponse createContact(CreateContactRequest createContactRequest) {
        String username = createContactRequest.getUsername();
        mapStatusOf(username);
        validate(createContactRequest);
        User user = findUserBy(username);
        Contact contact = mapContact(createContactRequest, username, user);
        contact.setDateTimeCreated(LocalDateTime.now());
        Contact savedContact = contactRepository.save(contact);
        return mapCreateContactResponse(savedContact);
    }


    @Override
    public EditContactResponse editContact(EditContactRequest editContactRequest) {
        String username = editContactRequest.getUsername();
        mapStatusOf(username);
        User user = findUserBy(username);
        Contact existingContact = checkingStatus(editContactRequest, user);
        mapEdit(editContactRequest, existingContact);
        Contact updatedContact = contactRepository.save(existingContact);
        return mapEditContactResponse(updatedContact);
    }

    @Override
    public User findUserBy(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("Username not found");
        }
        return user;
    }

    @Override
    public DeleteContactResponse deleteContact(DeleteContactRequest deleteContactRequest) {
        String username = deleteContactRequest.getUsername();
        mapStatusOf(username);
        User user = findUserBy(username);
        Contact existingContact = checkingStatus(deleteContactRequest, user);
        contactRepository.delete(existingContact);
        return mapDeleteContactResponse(existingContact, username);
    }

    private void validate(CreateContactRequest createContactRequest) {
        String username = createContactRequest.getUsername();
        if (username == null || username.isEmpty()) {
            throw new BigContactException("Username cannot be null or empty");
        }
        String phoneNumber = createContactRequest.getPhoneNumber();

        boolean contactExistsByPhoneNumber = contactRepository.existsByPhoneNumber(phoneNumber);
        if (contactExistsByPhoneNumber) {
            throw new BigContactException("Contact already exists");
            }
    }

    private void mapStatusOf(String username) {
        if (!userService.isUserRegistered(username)) {
            throw new BigContactException("User with username " + username + " is not registered");
        }

        if (!userService.isUserLoggedIn(username)) {
            throw new BigContactException("User with username " + username + " is not logged in");
        }
    }

    private Contact checkingStatus(EditContactRequest editContactRequest, User user) {
        String contactId = editContactRequest.getContactId();
        String userId = user.getId();
        Contact existingContact = contactRepository.findContactByContactIdAndUserId(contactId, userId);
        if (existingContact == null) {
            throw new BigContactException("Contact with ID " + contactId + " not found");
        }

        if (!existingContact.getUserId().equals(user.getId())) {
            throw new BigContactException("You are not authorized to edit this contact");
        }
        return existingContact;
    }

    private Contact checkingStatus(DeleteContactRequest deleteContactRequest, User user) {
        String contactId = deleteContactRequest.getContactId();
        String userId = user.getId();
        Contact existingContact = contactRepository.findContactByContactIdAndUserId(contactId, userId);

        if (existingContact == null) {
            throw new BigContactException("Contact with ID " + contactId + " not found");
        }

        if (!existingContact.getUserId().equals(userId)) {
            throw new BigContactException("You are not authorized to delete this contact");
        }

        return existingContact;
    }


}
