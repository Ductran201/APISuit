package ra.ecommerceapi.service;

import ra.ecommerceapi.exception.CheckDuplicateName;
import ra.ecommerceapi.model.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();

    List<Category> findAllByStatusTrue();

    Category findById(Long id);

    Category save(Category category) throws CheckDuplicateName;

    Category save(Category category,Long id) throws CheckDuplicateName;

    void  delete(Long id);

}
