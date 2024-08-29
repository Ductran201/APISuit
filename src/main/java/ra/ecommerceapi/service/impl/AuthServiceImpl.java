package ra.ecommerceapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.ecommerceapi.exception.CheckDuplicateName;
import ra.ecommerceapi.model.constant.RoleName;
import ra.ecommerceapi.model.dto.request.SignInRequest;
import ra.ecommerceapi.model.dto.request.SignUpRequest;
import ra.ecommerceapi.model.dto.response.JWTResponse;
import ra.ecommerceapi.model.entity.Role;
import ra.ecommerceapi.model.entity.User;
import ra.ecommerceapi.security.jwt.JWTProvider;
import ra.ecommerceapi.security.principle.UserDetailsCustom;
import ra.ecommerceapi.service.IAuthService;
import ra.ecommerceapi.service.IRoleService;
import ra.ecommerceapi.service.IUserService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final IUserService userService;
    private final IRoleService roleService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean signUp(SignUpRequest signUpRequest) throws CheckDuplicateName {
        if (userService.existsByEmail(signUpRequest.getEmail())){
            throw new CheckDuplicateName("This email already exist");
        }

        Set<Role> roleSet = new HashSet<>();

        if (signUpRequest.getRoles() == null || signUpRequest.getRoles().isEmpty()) {
            roleSet.add(roleService.findByRoleName(RoleName.ROLE_USER));
        } else {
            signUpRequest.getRoles().forEach(role -> {
                switch (role) {
                    case "admin":
                        roleSet.add(roleService.findByRoleName(RoleName.ROLE_ADMIN));
                    case "manager":
                        roleSet.add(roleService.findByRoleName(RoleName.ROLE_MANAGER));
                    case "user":
                        roleSet.add(roleService.findByRoleName(RoleName.ROLE_USER));
                        break;
                    default:
                        throw new RuntimeException("role not found");
                }
            });
        }

        User user = modelMapper.map(signUpRequest, User.class);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRoleSet(roleSet);

        userService.save(user);
        return true;
    }

    @Override
    public JWTResponse signIn(SignInRequest signInRequest) {
        Authentication authentication;
        try{
            authentication= authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));
        }catch (AuthenticationException e){
            throw new RuntimeException("Invalid email or password");
        }
        UserDetailsCustom userDetailsCustom = (UserDetailsCustom) authentication.getPrincipal();
        String accessToken = jwtProvider.generateAccessToken(userDetailsCustom);

        JWTResponse jwtResponse= modelMapper.map(userDetailsCustom , JWTResponse.class);
        jwtResponse.setAccessToken(accessToken);
        jwtResponse.setRoles(userDetailsCustom.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));


        return jwtResponse;
    }

    @Override
    public UserDetailsCustom getCurrentUser() {
        return (UserDetailsCustom) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
