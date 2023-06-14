package peaksoft.gadgetstoresession.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.gadgetstoresession.enums.Role;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthenticationResponse {

    private String token;
    private String email;
    private Role role;
}
