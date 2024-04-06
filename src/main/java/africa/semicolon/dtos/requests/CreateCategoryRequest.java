package africa.semicolon.dtos.requests;

import lombok.Data;

@Data
public class CreateCategoryRequest {
    private String description;
    private String username;
    private String categoryId;
    private String firstName;

}
