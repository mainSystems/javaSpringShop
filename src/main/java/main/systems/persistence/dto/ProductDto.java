package main.systems.persistence.dto;


import lombok.Data;
import main.systems.persistence.entity.Product;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private double cost;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
    }

    public ProductDto(Long id, String title, double cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public ProductDto() {
    }
}
