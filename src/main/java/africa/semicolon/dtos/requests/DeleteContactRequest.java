package africa.semicolon.dtos.requests;

import lombok.Data;

@Data
public class DeleteContactRequest {
    private String username;
    private String phoneNumber;
    private String email;
}
