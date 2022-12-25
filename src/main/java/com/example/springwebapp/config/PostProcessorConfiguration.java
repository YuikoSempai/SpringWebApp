package com.example.springwebapp.config;

import com.example.springwebapp.PostProcessing.PostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostProcessorConfiguration {

    @Bean
    public PostProcessor BeanPostProcessor() {
        return new PostProcessor();
    }

}
