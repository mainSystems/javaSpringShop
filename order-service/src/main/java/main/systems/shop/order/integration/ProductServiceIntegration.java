package main.systems.shop.order.integration;

import lombok.RequiredArgsConstructor;
import main.systems.shop.api.entity.dto.ProductDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductsById(Long id) {
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8180/app/api/v1/shop/product/" + id, ProductDto.class));
    }
}
