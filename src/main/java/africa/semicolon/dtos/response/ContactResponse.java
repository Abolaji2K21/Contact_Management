package africa.semicolon.dtos.response;

import lombok.Data;

@Data
public class ContactResponse {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String category;

}
