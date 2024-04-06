package africa.semicolon.controller;

import africa.semicolon.contactException.BigContactException;
import africa.semicolon.data.repositories.ContactRepository;
import africa.semicolon.dtos.requests.CreateContactRequest;
import africa.semicolon.dtos.requests.DeleteContactRequest;
import africa.semicolon.dtos.requests.EditContactRequest;
import africa.semicolon.dtos.response.ApiResponse;
import africa.semicolon.dtos.response.CreateContactResponse;
import africa.semicolon.dtos.response.DeleteContactResponse;
import africa.semicolon.dtos.response.EditContactResponse;
import africa.semicolon.services.ContactService;
import africa.semicolon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/Contact")
public class ContactController {

    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createContact(@RequestBody CreateContactRequest createContactRequest) {
        try {
            CreateContactResponse result = contactService.createContact(createContactRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (BigContactException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }


    @PostMapping("/edit")
    public ResponseEntity<?> editContact(@RequestBody EditContactRequest editContactRequest) {
        try {
            EditContactResponse result = contactService.editContact(editContactRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (BigContactException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteContact(@RequestBody DeleteContactRequest deleteContactRequest) {
        try {
            DeleteContactResponse result = contactService.deleteContact(deleteContactRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (BigContactException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }



}
