package ra.ecommerceapi.service;

import ra.ecommerceapi.exception.CheckDuplicateName;
import ra.ecommerceapi.model.dto.request.SignInRequest;
import ra.ecommerceapi.model.dto.request.SignUpRequest;
import ra.ecommerceapi.model.dto.response.JWTResponse;

public interface IAuthService{
    Boolean signUp(SignUpRequest signUpRequest) throws CheckDuplicateName;

    JWTResponse signIn(SignInRequest signInRequest);
}
