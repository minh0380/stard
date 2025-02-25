package com.stard.common.dto;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultDto<T> {
	
	private HttpStatus statusCode;
	private T resultData;
	
	private String pageTitle;
	
	private boolean useModal;
	private String modalTitle;
	private String modalBody;
	
	private String primaryBtn;
	private String secondaryBtn;
	private String primaryUrl;
	private String secondaryUrl;
	private boolean useSecondaryBtn;

}
