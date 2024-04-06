package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("Categories")
public class Category {
    private String id;
    private List<Contact> contacts;
    private String description;
    private String username;
}
