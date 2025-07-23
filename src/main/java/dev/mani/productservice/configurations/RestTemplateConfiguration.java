package dev.mani.productservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration // Not an optional thing, it should be mandatory
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
