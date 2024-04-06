package africa.semicolon.dtos.response;

import lombok.Data;

@Data
public class EditCategoryResponse {
    private String categoryId;
    private String description;
    private String username;
}
