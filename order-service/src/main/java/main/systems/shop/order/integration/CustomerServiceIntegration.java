package main.systems.shop.order.integration;

import lombok.RequiredArgsConstructor;
import main.systems.shop.api.entity.dto.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomerServiceIntegration {
    private final WebClient customerServiceWebClient;

    public CustomerDto getCustomerById(Long id) {
        return customerServiceWebClient.get()
                .uri("/api/v1/shop/customer/" + id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new Throwable("Customer not found"))
                )
                .bodyToMono(CustomerDto.class)
                .block();
    }
}
