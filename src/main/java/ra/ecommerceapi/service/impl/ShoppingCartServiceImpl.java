package ra.ecommerceapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.ecommerceapi.model.entity.ShoppingCart;
import ra.ecommerceapi.repository.ICategoryRepo;
import ra.ecommerceapi.repository.IShoppingCartRepo;
import ra.ecommerceapi.service.IShoppingCartService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements IShoppingCartService {
    private final IShoppingCartRepo shoppingCartRepo;


    @Override
    public List<ShoppingCart> findAll() {
        return shoppingCartRepo.findAll();
    }

    @Override
    public ShoppingCart findById(Long id) {
        return shoppingCartRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Not found this shopping cart"));
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepo.save(shoppingCart);
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
