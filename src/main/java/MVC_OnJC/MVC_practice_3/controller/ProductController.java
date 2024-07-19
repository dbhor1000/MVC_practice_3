package MVC_OnJC.MVC_practice_3.controller;

import MVC_OnJC.MVC_practice_3.DTO.EditProductDescription;
import MVC_OnJC.MVC_practice_3.DTO.ProductNameAndQuantityDTO;
import MVC_OnJC.MVC_practice_3.DTO.ProductNameOnlyDTO;
import MVC_OnJC.MVC_practice_3.DTO.ProductWithoutIdDTO;
import MVC_OnJC.MVC_practice_3.model.ProductEntity;
import MVC_OnJC.MVC_practice_3.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProductWithoutIdDTO>> fetchAllProducts() {

        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/getOneProduct")
    public ResponseEntity<ProductEntity> fetchOneProduct(@RequestBody ProductNameOnlyDTO productNameOnlyDTO) {

        ProductEntity productFound = productService.getProductInfoByProductName(productNameOnlyDTO);
        if(productFound == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productFound);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<ProductEntity> newProduct(@RequestBody ProductWithoutIdDTO productWithoutIdDTO) {

        ProductEntity createdProduct = productService.addNewProductCategory(productWithoutIdDTO);

        if(createdProduct == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(createdProduct);
    }

    @PatchMapping("/addStock")
    public ResponseEntity<ProductEntity> addProductStock(@RequestBody ProductNameAndQuantityDTO productNameAndQuantityDTO) {

        ProductEntity product = productService.addExistingProductStock(productNameAndQuantityDTO);

        if(product == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(product);
    }

    @PatchMapping("")
    public ResponseEntity<ProductEntity> updateProductDescription(@RequestBody EditProductDescription editProductDescription) {

        ProductEntity updatedProduct = productService.editProductDescription(editProductDescription);

        if(updatedProduct == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteProduct(@RequestBody ProductNameOnlyDTO productNameOnlyDTO) {

        if (productService.deleteProduct(productNameOnlyDTO)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
