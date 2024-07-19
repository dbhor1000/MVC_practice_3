package MVC_OnJC.MVC_practice_3.service;

import MVC_OnJC.MVC_practice_3.DTO.ProductNameOnlyDTO;
import MVC_OnJC.MVC_practice_3.DTO.ProductWithoutIdDTO;
import MVC_OnJC.MVC_practice_3.DTO.ProductNameAndQuantityDTO;
import MVC_OnJC.MVC_practice_3.DTO.EditProductDescription;
import MVC_OnJC.MVC_practice_3.model.ProductEntity;
import MVC_OnJC.MVC_practice_3.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductWithoutIdDTO> getAllProducts() {
        List<ProductEntity> allProducts = productRepository.findAll();

        List<ProductWithoutIdDTO> output = allProducts.stream()
                .map(entity -> new ProductWithoutIdDTO(
                        entity.getDescription(),
                        entity.getPrice(),
                        entity.getProductName(),
                        entity.getQuantityInStock()
                ))
                .toList();

        return output;
    }

    public ProductEntity getProductInfoByProductName(ProductNameOnlyDTO productNameOnlyDTO) {

        ProductEntity modifiableEntity = productRepository.getReferenceByProductName(productNameOnlyDTO.getProductName());

        if(modifiableEntity == null) {
            return null;
        }

        return modifiableEntity;
    }

    public ProductEntity addNewProductCategory(ProductWithoutIdDTO addNewProductCategory) {

        if(productRepository.getReferenceByProductName(addNewProductCategory.getProductName()) != null) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(0);
        productEntity.setProductName(addNewProductCategory.getProductName());
        productEntity.setDescription(addNewProductCategory.getDescription());
        productEntity.setPrice(addNewProductCategory.getPrice());
        productEntity.setQuantityInStock(addNewProductCategory.getQuantityInStock());
        productRepository.save(productEntity);

        return productEntity;
    }

    public ProductEntity addExistingProductStock(ProductNameAndQuantityDTO addProductStock) {

        ProductEntity modifiableEntity = productRepository.getReferenceByProductName(addProductStock.getProductName());

        if(modifiableEntity == null) {
            return null;
        }

        modifiableEntity.setQuantityInStock(modifiableEntity.getQuantityInStock() + addProductStock.getAddQuantity());
        productRepository.save(modifiableEntity);

        return modifiableEntity;
    }

    public ProductEntity editProductDescription(EditProductDescription editProductDescription){

        ProductEntity modifiableEntity = productRepository.getReferenceByProductName(editProductDescription.getProductName());

        if(modifiableEntity == null) {
            return null;
        }

        modifiableEntity.setDescription(editProductDescription.getDescription());
        productRepository.save(modifiableEntity);
        return modifiableEntity;
    }

    @Transactional
    public boolean deleteProduct(ProductNameOnlyDTO productNameOnlyDTO) {

        ProductEntity modifiableEntity = productRepository.getReferenceByProductName(productNameOnlyDTO.getProductName());

        if(modifiableEntity == null) {
            return false;
        }

        productRepository.deleteByProductName(productNameOnlyDTO.getProductName());
        return true;

    }

}
