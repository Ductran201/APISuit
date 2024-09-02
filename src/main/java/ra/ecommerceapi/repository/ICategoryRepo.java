package ra.ecommerceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ra.ecommerceapi.model.entity.Category;


public interface ICategoryRepo extends JpaRepository<Category,Long> {
    Boolean existsByName(String name);

    // FOR USER
    Page<Category> findAllByNameContainsAndStatusTrue(String name, Pageable pageable);

    // FOR ADMIN
    Page<Category> findAllByNameContains(String name, Pageable pageable);

}
