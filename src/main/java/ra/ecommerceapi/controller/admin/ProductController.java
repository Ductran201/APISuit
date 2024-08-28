package ra.ecommerceapi.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ra.ecommerceapi.exception.CheckDuplicateName;
import ra.ecommerceapi.model.dto.request.ProductRequest;
import ra.ecommerceapi.model.dto.response.ResponseDataSuccess;
import ra.ecommerceapi.service.IProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping("api.com/v2/product")
public class ProductController {
    private final IProductService productService;

    @GetMapping("/")
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(new ResponseDataSuccess<>(productService.findAll(), HttpStatus.OK),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(new ResponseDataSuccess<>(productService.findById(id), HttpStatus.OK),HttpStatus.OK );
    }

    @PostMapping("/")
    public ResponseEntity<?> add(@Valid @ModelAttribute ProductRequest productRequest) throws CheckDuplicateName {
        return new ResponseEntity<>(new ResponseDataSuccess<>(productService.save(productRequest), HttpStatus.CREATED),HttpStatus.CREATED );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id,@Valid @ModelAttribute ProductRequest productRequest) throws CheckDuplicateName {
        return new ResponseEntity<>(new ResponseDataSuccess<>(productService.save(productRequest,id), HttpStatus.OK),HttpStatus.OK );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productService.findById(id);
        productService.delete(id);
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }



}
