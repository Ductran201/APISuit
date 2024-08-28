package ra.ecommerceapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ra.ecommerceapi.exception.CheckDuplicateName;
import ra.ecommerceapi.model.dto.request.ProductRequest;
import ra.ecommerceapi.model.entity.Product;
import ra.ecommerceapi.repository.IProductRepo;
import ra.ecommerceapi.service.ICategoryService;
import ra.ecommerceapi.service.IProductService;
import ra.ecommerceapi.service.UploadService;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final IProductRepo productRepo;
    private final ICategoryService categoryService;
    private final UploadService uploadService;
    private final ModelMapper modelMapper;

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Not found this product"));
    }

    @Override
    public Product save(ProductRequest productRequest) throws CheckDuplicateName {

        if (productRepo.existsByName(productRequest.getName())){
            throw new CheckDuplicateName("Exist this category name");
        }

        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .status(true)
                .createdDate(new Date())
                .updatedDate(new Date())
                .category(categoryService.findById(productRequest.getCategoryId()))
                .build();

        if (productRequest.getFile() != null && productRequest.getFile().getSize() > 0) {
            product.setImage(uploadService.uploadFileToServer(productRequest.getFile()));
        } else {
            product.setImage(null);
        }

        return productRepo.save(product);
    }

    @Override
    public Product save(ProductRequest productRequest, Long id) throws CheckDuplicateName {

        if (!Objects.equals(productRequest.getName(), findById(id).getName()) && productRepo.existsByName(productRequest.getName())){
            throw new CheckDuplicateName("Exist this category name");
        }

        Product oldProduct = findById(id);

        oldProduct.setName(productRequest.getName());
        oldProduct.setDescription(productRequest.getDescription());
        oldProduct.setCategory(categoryService.findById(productRequest.getCategoryId()));
        oldProduct.setUpdatedDate(new Date());

        if (productRequest.getFile() != null && productRequest.getFile().getSize() > 0) {
            oldProduct.setImage(uploadService.uploadFileToServer(productRequest.getFile()));
        } else {
            oldProduct.setImage(productRepo.getImgById(id));
        }

        return productRepo.save(oldProduct);
    }


    @Override
    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public List<Product> findByNameOrDescription(String search) {
        return productRepo.findByNameOrDescription(search);
    }

    @Override
    public List<Product> findAllByCategoryId(Long id) {
        return productRepo.findAllByCategoryId(id);
    }

}
