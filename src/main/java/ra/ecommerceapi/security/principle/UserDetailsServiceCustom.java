package ra.ecommerceapi.security.principle;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.ecommerceapi.model.entity.User;
import ra.ecommerceapi.repository.IUserRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceCustom implements UserDetailsService {
    private final IUserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByEmail(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return UserDetailsCustom.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .phone(user.getPhone())
                    .address(user.getAddress())
                    .status(user.getStatus())
                    .gender(user.getGender())
                    .avatar(user.getAvatar())
                    .authorities(user.getRoleSet().stream()
                            .map(roles -> new SimpleGrantedAuthority(roles.getRoleName().name()))
                            .toList())
                    .build();
        }
        throw new UsernameNotFoundException("Email not found");
    }
}
