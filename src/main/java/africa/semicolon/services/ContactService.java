package africa.semicolon.services;

import africa.semicolon.data.models.Contact;
import africa.semicolon.data.models.User;
import africa.semicolon.dtos.requests.CreateContactRequest;
import africa.semicolon.dtos.requests.DeleteContactRequest;
import africa.semicolon.dtos.requests.EditContactRequest;
import africa.semicolon.dtos.response.CreateContactResponse;
import africa.semicolon.dtos.response.DeleteContactResponse;
import africa.semicolon.dtos.response.EditContactResponse;

public interface ContactService {
    CreateContactResponse createContact(CreateContactRequest createContactRequest);

    EditContactResponse editContact(EditContactRequest editContactRequest);

    User findUserBy(String username);

    DeleteContactResponse deleteContact(DeleteContactRequest deleteContactRequest);

}
