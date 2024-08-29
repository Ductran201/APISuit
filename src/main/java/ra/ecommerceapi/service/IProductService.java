package ra.ecommerceapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import ra.ecommerceapi.exception.CheckDuplicateName;
import ra.ecommerceapi.model.dto.request.ProductRequest;
import ra.ecommerceapi.model.entity.Category;
import ra.ecommerceapi.model.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    Product findById(Long id);

    Product save(ProductRequest productRequest) throws CheckDuplicateName;

    Product save(ProductRequest productRequest,Long id) throws CheckDuplicateName;

    void  delete(Long id);

    Page<Product> findAllPaginationAdmin(String search, Pageable pageable);

    Page<Product> findAllPaginationUser(String search, Pageable pageable);

    List<Product> findByNameOrDescription(String search);

    List<Product> findAllByCategoryId(Long id);
}
