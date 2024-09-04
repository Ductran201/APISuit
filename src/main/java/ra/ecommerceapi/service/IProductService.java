package ra.ecommerceapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import ra.ecommerceapi.exception.CheckDuplicateName;
import ra.ecommerceapi.model.dto.request.ProductRequest;
import ra.ecommerceapi.model.dto.response.ProductUserDTO;
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

    Page<ProductUserDTO> findAllPaginationUser(String search, Pageable pageable);

    Page<ProductUserDTO> findAllByCategoryIdAndStatusTrue(Long id, Pageable pageable);



}
