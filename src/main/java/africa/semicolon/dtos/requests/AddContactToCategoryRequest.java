package africa.semicolon.dtos.requests;

import africa.semicolon.data.models.Contact;
import lombok.Data;

@Data
public class AddContactToCategoryRequest {
    private String username;
    private String description;
    private Contact contact;
}
