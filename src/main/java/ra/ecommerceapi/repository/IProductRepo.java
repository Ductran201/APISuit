package ra.ecommerceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.ecommerceapi.model.entity.Product;

import java.util.List;

public interface IProductRepo extends JpaRepository<Product,Long> {
    @Query("select p.image from Product p where p.id= :id")
    String getImgById(@Param("id") Long id);

    Boolean existsByName(String name);

//    @Query("from Product p where p.name like %:search% or p.description like %:search%")
//    List<Product> findByNameOrDescription(String search);

//    void findTop5ByC

    List<Product> findAllByNameContainsOrDescriptionContains(String name, String description);

    Page<Product> findAllByNameContains(String name, Pageable pageable);

    Page<Product> findAllByNameContainsAndStatusTrue(String name,Pageable pageable);

    @Query("from Product p where p.category.id =: id")
    List<Product> findAllByCategoryId(@Param("id") Long id);



}
