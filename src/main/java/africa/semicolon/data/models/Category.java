package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("Categories")
public class Category {
    @Id
    private String id;
    private List<Contact> contacts;
    private String description;
    private String username;
}
