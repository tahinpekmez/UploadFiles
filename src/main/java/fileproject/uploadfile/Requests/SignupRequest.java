package fileproject.uploadfile.Requests;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data

public class SignupRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
