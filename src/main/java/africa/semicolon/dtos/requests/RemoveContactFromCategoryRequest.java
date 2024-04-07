package africa.semicolon.dtos.requests;

import africa.semicolon.data.models.Contact;
import lombok.Data;

@Data
public class RemoveContactFromCategoryRequest {
    private String username;
    private String description;
    private Contact contact;

}
