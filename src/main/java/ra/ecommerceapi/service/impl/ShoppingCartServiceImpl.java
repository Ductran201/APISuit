package ra.ecommerceapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ra.ecommerceapi.exception.CustomException;
import ra.ecommerceapi.model.dto.request.CartRequest;
import ra.ecommerceapi.model.dto.response.CartResponse;
import ra.ecommerceapi.model.entity.Product;
import ra.ecommerceapi.model.entity.ShoppingCart;
import ra.ecommerceapi.model.entity.User;
import ra.ecommerceapi.repository.IShoppingCartRepo;
import ra.ecommerceapi.service.IAuthService;
import ra.ecommerceapi.service.IProductService;
import ra.ecommerceapi.service.IShoppingCartService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements IShoppingCartService {
    private final IShoppingCartRepo shoppingCartRepo;
    private final IProductService productService;
    private final IAuthService authService;
    private final ModelMapper modelMapper;


    @Override
    public List<CartResponse> findAll() {
        User userCurrent = authService.getCurrentUser().getUser();
        return shoppingCartRepo.findAllByUser(userCurrent).stream().map(c -> modelMapper.map(c, CartResponse.class)).toList();
    }

    // Check cart by userCurrent
    @Override
    public ShoppingCart findByUserAndId(Long id) {

        User userCurrent = authService.getCurrentUser().getUser();

        return shoppingCartRepo.findByUserAndId(userCurrent,id).orElseThrow(()-> new NoSuchElementException("Not found this cart"));
    }

    @Override
    public CartResponse save(CartRequest cartRequest) throws CustomException {
        User userCurrent = authService.getCurrentUser().getUser();

        Product product = productService.findById(cartRequest.getProductId());
        //check exist product in cart
        if (shoppingCartRepo.existsByUserAndProduct(userCurrent, product)) {
            throw new CustomException("This product already in cart");
        }

        ShoppingCart newCart = ShoppingCart.builder()
                .orderQuantity(cartRequest.getQuantity())
                .product(product)
                .user(userCurrent)
                .build();
        newCart.setStatus(true);
        shoppingCartRepo.save(newCart);

        return CartResponse.builder()
                .productId(newCart.getProduct().getId())
                .price(newCart.getProduct().getPrice())
                .productName(newCart.getProduct().getName())
                .quantity(newCart.getOrderQuantity())
                .build();

    }

    @Override
    public CartResponse save(CartRequest cartRequest, Long id) {
        // find cart by user and id cart
        ShoppingCart oldCart= findByUserAndId(id);

        if (!oldCart.getProduct().getId().equals(cartRequest.getProductId())) {
            throw new IllegalArgumentException("Product ID does not match with the cart item");
        }

        // change quantity
        oldCart.setOrderQuantity(oldCart.getOrderQuantity()+cartRequest.getQuantity());

        ShoppingCart editCart= shoppingCartRepo.save(oldCart);
        return CartResponse.builder()
                .productId(editCart.getProduct().getId())
                .productName(editCart.getProduct().getName())
                .quantity(editCart.getOrderQuantity())
                .price(editCart.getProduct().getPrice())
                .build();
    }

    @Override
    public void delete(Long id) {
        findByUserAndId(id);
        shoppingCartRepo.deleteById(id);
    }

    @Override
    public void clear() {
        User userCurrent = authService.getCurrentUser().getUser();
        shoppingCartRepo.deleteAllByUser(userCurrent);
    }



}
