package ra.ecommerceapi.controller.user;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.ecommerceapi.model.constant.EHttpStatus;
import ra.ecommerceapi.model.dto.ResponseWrapper;
import ra.ecommerceapi.model.dto.response.ProductUserDTO;
import ra.ecommerceapi.service.IWishListService;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api.com/v2/u/wish-list")
public class WishListController {
    private final IWishListService wishListService;
    private final ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity<?> findAll(){
        Set<ProductUserDTO> wishList= wishListService.findAll();
        return ResponseEntity.ok().body(new ResponseWrapper<>(wishList,EHttpStatus.SUCCESS,200));
    }

    @PostMapping("/{idProduct}")
    public ResponseEntity<?> toggleWishList(@PathVariable Long idProduct){
        wishListService.toggleWishList(idProduct);
        return ResponseEntity.ok().body(new ResponseWrapper<>(null, EHttpStatus.SUCCESS,200));
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<?> removeWishList(@PathVariable Long idProduct){
        wishListService.removeWishList(idProduct);
        return ResponseEntity.ok().body(new ResponseWrapper<>(null,EHttpStatus.SUCCESS,200));
    }

}
