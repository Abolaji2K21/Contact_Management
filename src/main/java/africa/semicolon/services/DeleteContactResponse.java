package africa.semicolon.services;

import lombok.Data;

@Data
public class DeleteContactResponse {
    private String contactId;
    private boolean deleted;
    private String username;
}
