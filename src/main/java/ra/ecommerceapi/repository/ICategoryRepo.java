package ra.ecommerceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ra.ecommerceapi.model.entity.Category;
import ra.ecommerceapi.model.entity.Product;
import ra.ecommerceapi.model.entity.User;

import java.util.List;

public interface ICategoryRepo extends JpaRepository<Category,Long> {
    Boolean existsByName(String name);

    List<Category> findAllByStatusTrue();

    // FOR USER
    Page<Category> findAllByNameContainsAndStatusTrue(String name, Pageable pageable);


    // FOR ADMIN
    Page<Category> findAllByNameContains(String name, Pageable pageable);

}
