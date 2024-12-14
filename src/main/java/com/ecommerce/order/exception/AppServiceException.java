package com.ecommerce.order.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppServiceException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private int status;
	
	public AppServiceException(String message, String errCode) {
		super(message);
		errorCode = errCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public AppServiceException(String message, String errorCode, int status) {
		super(message);
		this.errorCode = errorCode;
		this.status = status;
	}

	
	
}
