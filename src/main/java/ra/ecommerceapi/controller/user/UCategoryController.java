package ra.ecommerceapi.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ra.ecommerceapi.exception.CheckDuplicateName;
import ra.ecommerceapi.model.dto.response.ResponseDataSuccess;
import ra.ecommerceapi.model.entity.Category;
import ra.ecommerceapi.service.ICategoryService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api.com/v2/u/categories")
public class UCategoryController {
    private final ICategoryService categoryService;

//    @GetMapping("/")
//    public ResponseEntity<?> list() {
//        return new ResponseEntity<>(new ResponseDataSuccess<>(categoryService.findAllByStatusTrue(), HttpStatus.OK), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(new ResponseDataSuccess<>(categoryService.findById(id),HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> add(@Valid @RequestBody Category category) throws CheckDuplicateName {
        return new ResponseEntity<>(new ResponseDataSuccess<>(categoryService.save(category),HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id,@Valid @RequestBody Category category) throws CheckDuplicateName {
        return new ResponseEntity<>(new ResponseDataSuccess<>(categoryService.save(category,id),HttpStatus.OK), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryService.findById(id);
        categoryService.delete(id);
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }
}
