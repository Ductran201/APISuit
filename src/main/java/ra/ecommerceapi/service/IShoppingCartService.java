package ra.ecommerceapi.service;

import ra.ecommerceapi.model.entity.ShoppingCart;

import java.util.List;

public interface IShoppingCartService {
    List<ShoppingCart> findAll();

    ShoppingCart findById(Long id);

    ShoppingCart save(ShoppingCart shoppingCart);

    ShoppingCart save(ShoppingCart shoppingCart,Long id);

    void delete(Long id);
}
