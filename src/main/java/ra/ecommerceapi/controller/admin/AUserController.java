package ra.ecommerceapi.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ra.ecommerceapi.model.dto.response.ResponseDataSuccess;
import ra.ecommerceapi.model.entity.User;
import ra.ecommerceapi.service.IUserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api.com/v2/users")
public class AUserController {
    private final IUserService userService;

    @GetMapping("/")
    public ResponseEntity<?> listUser() {
        return new ResponseEntity<>(new ResponseDataSuccess<>(userService.findAllExceptAdmin(), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> pagination(Pageable pageable) {
        return new ResponseEntity<>(new ResponseDataSuccess<>(userService.findAllPagination(pageable), HttpStatus.OK), HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> findById(@PathVariable Long id) {
//        return new ResponseEntity<>(new ResponseDataSuccess<>(userService.findById(id),HttpStatus.OK), HttpStatus.OK);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> toggleStatus(@PathVariable Long id) {
        userService.findUserExceptAdminById(id);
        userService.toggleStatus(id);
        return new ResponseEntity<>(new ResponseDataSuccess<>(userService.findUserExceptAdminById(id),HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> findByFullName(@RequestParam String fullName) {
        List<User> users = userService.findAllByFullName(fullName);
        if (users.isEmpty()){
            return new ResponseEntity<>(new ResponseDataSuccess<>("Not found user with name",HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseDataSuccess<>(users,HttpStatus.OK), HttpStatus.OK);
    }

//    @PostMapping("/")
//    public ResponseEntity<?> add(@Valid @RequestBody Category category) throws CheckDuplicateName {
//        return new ResponseEntity<>(new ResponseDataSuccess<>(userService.save(category),HttpStatus.CREATED), HttpStatus.CREATED);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> edit(@PathVariable Long id,@Valid @RequestBody Category category) throws CheckDuplicateName {
//        return new ResponseEntity<>(new ResponseDataSuccess<>(userService.save(category,id),HttpStatus.OK), HttpStatus.OK);
//    }


//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//        userService.findById(id);
//        userService.delete(id);
//        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
//    }


}
