package peaksoft.gadgetstoresession.services;

import peaksoft.gadgetstoresession.dto.request.SignInRequest;
import peaksoft.gadgetstoresession.dto.request.SignUpRequest;
import peaksoft.gadgetstoresession.dto.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse signUp(SignUpRequest request);

    AuthenticationResponse signIn(SignInRequest request);
}
