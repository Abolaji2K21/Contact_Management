package africa.semicolon.dtos.requests;

import lombok.Data;

@Data
public class CreateContactRequest {
    private String username;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String email;
}
