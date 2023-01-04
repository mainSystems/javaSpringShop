package main.systems.shop.core.security.configs;

import main.systems.shop.core.security.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:secret.properties")
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfiguration {
}

