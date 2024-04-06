package africa.semicolon.data.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("Contacts")
public class Contact {
    @Id
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDateTime dateTimeCreated;
    private String userId;
    private LocalDateTime dateTimeUpdated;
    @DBRef
    private Category category;
}

