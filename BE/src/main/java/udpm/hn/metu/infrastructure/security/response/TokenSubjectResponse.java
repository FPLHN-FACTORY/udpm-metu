package udpm.hn.metu.infrastructure.security.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TokenSubjectResponse {

    private String userId;

    private String userCode;

    private String fullName;

    private String pictureUrl;

    private String host;

    private String rolesCode;

    private String rolesName;

    private String email;

}
