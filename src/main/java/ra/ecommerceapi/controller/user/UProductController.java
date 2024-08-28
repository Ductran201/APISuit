package ra.ecommerceapi.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ra.ecommerceapi.exception.CheckDuplicateName;
import ra.ecommerceapi.model.dto.request.ProductRequest;
import ra.ecommerceapi.model.dto.response.ResponseDataError;
import ra.ecommerceapi.model.dto.response.ResponseDataSuccess;
import ra.ecommerceapi.model.entity.Product;
import ra.ecommerceapi.service.IProductService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api.com/v2/u/product")
public class UProductController {
    private final IProductService productService;

    @GetMapping("/")
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(new ResponseDataSuccess<>(productService.findAll(), HttpStatus.OK),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(new ResponseDataSuccess<>(productService.findById(id), HttpStatus.OK),HttpStatus.OK );
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByNameOrDescription(@RequestParam String search){
        List<Product> products = productService.findByNameOrDescription(search);
        if (products.isEmpty()) {
            return new ResponseEntity<>(new ResponseDataSuccess<>("Not found product with name",HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseDataSuccess<>(products,HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> findByCategoryId(@PathVariable Long categoryId){
        List<Product> products= productService.findAllByCategoryId(categoryId);
        if (products.isEmpty()){
            return new ResponseEntity<>(new ResponseDataSuccess<>("Not found product in category",HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseDataSuccess<>(products,HttpStatus.OK), HttpStatus.OK);
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
