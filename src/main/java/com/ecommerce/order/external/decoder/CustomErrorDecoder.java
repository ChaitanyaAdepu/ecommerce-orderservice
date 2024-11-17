package com.ecommerce.order.external.decoder;

import java.io.IOException;

import com.ecommerce.order.exception.AppServiceException;
import com.ecommerce.order.model.ErrorResponse;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
		ObjectMapper om = new ObjectMapper();
		
		log.info("msg {}",response.request().url());
		try {
			ErrorResponse errResponse = om.readValue(response.body().asInputStream(), ErrorResponse.class);
			throw new AppServiceException(errResponse.getErrorMessage(), errResponse.getErrorCode(), errResponse.getStatusCode());
		} catch (StreamReadException e) {
			log.error("StreamReadException {}",e);
		} catch (DatabindException e) {
			log.error("DatabindException {}",e);
		} catch (IOException e) {
			log.error("IOException {}",e);
			throw new AppServiceException("ERR500", 500);
		}
		return null;
	}
	
}
