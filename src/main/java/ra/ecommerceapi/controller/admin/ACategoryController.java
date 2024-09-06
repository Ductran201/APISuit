package ra.ecommerceapi.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ra.ecommerceapi.exception.CustomException;
import ra.ecommerceapi.model.constant.EHttpStatus;
import ra.ecommerceapi.model.dto.ResponseWrapper;
import ra.ecommerceapi.model.entity.Category;
import ra.ecommerceapi.service.ICategoryService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api.com/v2/admin/categories")
public class ACategoryController {
    private final ICategoryService categoryService;

    /**
     * @param search   String
     * @param pageable Pageable
     * @apiNote handle get all categories with pagination and search for admin role
     */
    @GetMapping("")
    public ResponseEntity<?> listPagination(@RequestParam(defaultValue = "") String search
            , @PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok().body(
                ResponseWrapper.builder()
                        .data(categoryService.findAllPaginationAdmin(search, pageable))
                        .status(EHttpStatus.SUCCESS)
                        .code(200)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(
                ResponseWrapper.builder()
                        .data(categoryService.findById(id))
                        .status(EHttpStatus.SUCCESS)
                        .code(200)
                        .build()
        );
    }

    @PostMapping("")
    public ResponseEntity<?> add(@Valid @RequestBody Category category) throws CustomException {
        return new ResponseEntity<>(ResponseWrapper.builder()
                .data(categoryService.save(category))
                .status(EHttpStatus.SUCCESS)
                .code(201)
                .build(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody Category category) throws CustomException {
        return new ResponseEntity<>(ResponseWrapper.builder()
                .data(categoryService.save(category, id))
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .build(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryService.findById(id);
        categoryService.delete(id);
        return new ResponseEntity<>(ResponseWrapper.builder()
                .data("Delete successfully")
                .status(EHttpStatus.SUCCESS)
                .code(200)
                .build(), HttpStatus.OK);
    }

}
