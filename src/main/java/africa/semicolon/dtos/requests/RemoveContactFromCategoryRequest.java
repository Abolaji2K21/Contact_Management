package africa.semicolon.dtos.requests;

import africa.semicolon.data.models.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RemoveContactFromCategoryRequest {
    private String contactId;
    private String categoryId;

}
