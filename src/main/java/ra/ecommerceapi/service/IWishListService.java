package ra.ecommerceapi.service;

import ra.ecommerceapi.model.dto.response.ProductUserDTO;

import java.util.List;
import java.util.Set;

public interface IWishListService {
    Set<ProductUserDTO> findAll();

    void toggleWishList(Long idProduct);

    void removeWishList(Long idProduct);
}
