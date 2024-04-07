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
    private String contactId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String userId;
    private LocalDateTime dateTimeCreated;
    private LocalDateTime dateTimeUpdated;
}

