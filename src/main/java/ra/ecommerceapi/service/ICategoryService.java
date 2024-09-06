package ra.ecommerceapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.ecommerceapi.exception.CustomException;
import ra.ecommerceapi.model.entity.Category;

import java.util.List;

public interface ICategoryService {
    // COMMON

    List<Category> findAll();

    // ADMIN
    Page<Category> findAllPaginationAdmin(String search ,Pageable pageable);

    //USER
    Page<Category> findAllPaginationUser(String search, Pageable pageable);

    Category findById(Long id);

    Category save(Category category) throws CustomException;

    Category save(Category category,Long id) throws CustomException;

    void delete(Long id);



}
