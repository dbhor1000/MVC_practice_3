package MVC_OnJC.MVC_practice_3.DTO;

import java.math.BigDecimal;

public class ProductWithoutIdDTO {

    private String productName;
    private String description;
    private BigDecimal price;
    private Integer quantityInStock;

    public ProductWithoutIdDTO(String description, BigDecimal price, String productName, Integer quantityInStock) {
        this.description = description;
        this.price = price;
        this.productName = productName;
        this.quantityInStock = quantityInStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
