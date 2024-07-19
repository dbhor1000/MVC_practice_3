package MVC_OnJC.MVC_practice_3.repository;

import MVC_OnJC.MVC_practice_3.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    ProductEntity getReferenceByProductName(String productName);
    @Modifying
    @Query("DELETE FROM product_table b WHERE b.productName = :productName")
    void deleteByProductName(@Param("productName") String productName);

}
