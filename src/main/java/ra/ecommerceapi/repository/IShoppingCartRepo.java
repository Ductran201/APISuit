package ra.ecommerceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.ecommerceapi.model.entity.ShoppingCart;

public interface IShoppingCartRepo extends JpaRepository<ShoppingCart,Long> {

}
