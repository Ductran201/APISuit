package ra.ecommerceapi.service;

import ra.ecommerceapi.exception.CustomException;
import ra.ecommerceapi.model.dto.request.CartRequest;
import ra.ecommerceapi.model.dto.response.CartResponse;
import ra.ecommerceapi.model.entity.ShoppingCart;
import ra.ecommerceapi.model.entity.User;

import java.util.List;

public interface IShoppingCartService {
    List<CartResponse> findAll();

    ShoppingCart findByUserAndId(Long id);

    CartResponse save(CartRequest cartRequest) throws CustomException;

    CartResponse save(CartRequest cartRequest,Long id);

    void delete(Long id);

    void clear();

}
