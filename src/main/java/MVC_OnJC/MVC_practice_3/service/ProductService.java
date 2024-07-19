package MVC_OnJC.MVC_practice_3.service;

import MVC_OnJC.MVC_practice_3.DTO.ProductNameAndQuantityDTO;
import MVC_OnJC.MVC_practice_3.DTO.EditProductDescription;
import MVC_OnJC.MVC_practice_3.DTO.ProductNameOnlyDTO;
import MVC_OnJC.MVC_practice_3.DTO.ProductWithoutIdDTO;
import MVC_OnJC.MVC_practice_3.model.ProductEntity;

import java.util.List;

public interface ProductService {

    public List<ProductWithoutIdDTO> getAllProducts();
    public ProductEntity getProductInfoByProductName(ProductNameOnlyDTO productNameOnlyDTO);
    public ProductEntity addNewProductCategory(ProductWithoutIdDTO addNewProductCategory);
    public ProductEntity addExistingProductStock(ProductNameAndQuantityDTO addProductStock);
    public ProductEntity editProductDescription(EditProductDescription editProductDescription);
    public boolean deleteProduct(ProductNameOnlyDTO productNameOnlyDTO);
}
