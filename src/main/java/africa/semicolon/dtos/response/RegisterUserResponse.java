package africa.semicolon.dtos.response;

import lombok.Data;

@Data
public class RegisterUserResponse {
    private String id;
    private String username;
    private String dateRegistered;
    private boolean loggedIn;

}
