package MVC_OnJC.MVC_practice_3.DTO;

public class ProductNameAndQuantityDTO {

    private String productName;
    private Integer addQuantity;

    public Integer getAddQuantity() {
        return addQuantity;
    }

    public void setAddQuantity(Integer addQuantity) {
        this.addQuantity = addQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
