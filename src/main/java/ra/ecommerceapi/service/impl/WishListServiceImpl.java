package ra.ecommerceapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ra.ecommerceapi.model.dto.response.ProductUserDTO;
import ra.ecommerceapi.model.entity.Product;
import ra.ecommerceapi.model.entity.User;
import ra.ecommerceapi.repository.IProductRepo;
import ra.ecommerceapi.repository.IUserRepo;
import ra.ecommerceapi.service.IAuthService;
import ra.ecommerceapi.service.IProductService;
import ra.ecommerceapi.service.IUserService;
import ra.ecommerceapi.service.IWishListService;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements IWishListService {
    private final IUserService userService;
    private final IProductService productService;
    private final IAuthService authService;
    private final ModelMapper modelMapper;

    @Override
    public Set<ProductUserDTO> findAll() {
        User userCurrent=authService.getCurrentUser().getUser();
        return userCurrent.getWishList().stream().map(item->modelMapper.map(item,ProductUserDTO.class)).collect(Collectors.toSet());
    }

    @Override
    public void toggleWishList(Long idProduct) {

        Product productWishList= productService.findById(idProduct);
        User userCurrent = userService.findById(authService.getCurrentUser().getUser().getId());
        Set<Product> products= userCurrent.getWishList();
        // check exist
        if (products.stream().anyMatch(p->p.getId().equals(idProduct))){
            products.remove(productWishList);
        }else {
            products.add(productWishList);
        }
        userCurrent.setWishList(products);
        userService.save(userCurrent);
    }

    @Override
    public void removeWishList(Long idProduct) {
        Product product=productService.findById(idProduct);
        User userCurrent = userService.findById(authService.getCurrentUser().getUser().getId());
        userCurrent.getWishList().remove(product);
        userService.save(userCurrent);
    }
}
