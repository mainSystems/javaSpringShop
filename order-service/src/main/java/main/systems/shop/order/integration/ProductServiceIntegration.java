package main.systems.shop.order.integration;

import lombok.RequiredArgsConstructor;
import main.systems.shop.api.entity.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;

    public ProductDto getProductsById(Long productId) {
        return productServiceWebClient.get()
                .uri("/api/v1/shop/product/" + productId)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new Throwable("Product not found"))
                )
                .bodyToMono(ProductDto.class)
                .block();
    }
}
