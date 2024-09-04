package ra.ecommerceapi.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.ecommerceapi.service.IAuthService;
import ra.ecommerceapi.service.IUserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("api.com/v2/user/account")
public class UUserController {
    private final IUserService userService;

    @GetMapping("")
    public ResponseEntity<?> getInformation() {
        return null;
    }

    @PutMapping("")
    public ResponseEntity<?> updateInformation() {
        return null;
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword() {
        return null;
    }

}
