package com.ecommerce.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.order.external.decoder.CustomErrorDecoder;

import feign.codec.ErrorDecoder;
import lombok.Builder;

@Configuration
public class FeignConfig {
	
	@Bean
	ErrorDecoder errorDecoder() {
		return new CustomErrorDecoder();
	}
}
