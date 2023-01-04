package main.systems.shop.core.persistence.converters;

import main.systems.shop.core.persistence.entity.model.dto.ProductDto;
import main.systems.shop.core.persistence.entity.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getCost());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getCost());
    }
}
