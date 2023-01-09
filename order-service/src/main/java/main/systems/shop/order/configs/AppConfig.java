package main.systems.shop.order.configs;

import io.netty.channel.ChannelOption;
import lombok.RequiredArgsConstructor;
import main.systems.shop.order.integration.properties.CustomerServiceIntegrationProperties;
import main.systems.shop.order.integration.properties.ProductServiceIntegrationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

@Configuration
@EnableConfigurationProperties({ProductServiceIntegrationProperties.class, CustomerServiceIntegrationProperties.class})
@RequiredArgsConstructor
public class AppConfig {

    private final ProductServiceIntegrationProperties productServiceIntegrationProperties;
    private final CustomerServiceIntegrationProperties customerServiceIntegrationProperties;

    @Bean
    public WebClient productServiceWebClient() {
        TcpClient tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
//                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, productServiceIntegrationProperties.getConnectTimeout())
                .doOnConnected(connection -> {
//                    connection.addHandlerLast(new ReadTimeoutHandler(productServiceIntegrationProperties.getReadTimeout(), TimeUnit.MILLISECONDS));
//                    connection.addHandlerLast(new WriteTimeoutHandler(productServiceIntegrationProperties.getWriteTimeout(), TimeUnit.MILLISECONDS));
                });
        return WebClient
                .builder()
                .baseUrl("http://localhost:8180/app")
//                .baseUrl(productServiceIntegrationProperties.getUrl())
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }

    @Bean
    public WebClient customerServiceWebClient() {
        TcpClient tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
//                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, customerServiceIntegrationProperties.getConnectTimeout())
                .doOnConnected(connection -> {
//                    connection.addHandlerLast(new ReadTimeoutHandler(customerServiceIntegrationProperties.getReadTimeout(), TimeUnit.MILLISECONDS));
//                    connection.addHandlerLast(new WriteTimeoutHandler(customerServiceIntegrationProperties.getWriteTimeout(), TimeUnit.MILLISECONDS));
                });
        return WebClient
                .builder()
                .baseUrl("http://localhost:8180/app")
//                .baseUrl(customerServiceIntegrationProperties.getUrl())
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }
}
