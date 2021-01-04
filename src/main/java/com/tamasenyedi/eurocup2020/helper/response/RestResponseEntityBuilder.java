package com.tamasenyedi.eurocup2020.helper.response;

import com.tamasenyedi.eurocup2020.dto.AbstractDto;
import com.tamasenyedi.eurocup2020.dto.ServiceResponseDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestResponseEntityBuilder {

    private static RestResponseEntityBuilder INSTANCE = new RestResponseEntityBuilder();

    private RestResponseEntityBuilder() {}

    public static RestResponseEntityBuilder getInstance() {
        return INSTANCE;
    }

    public ResponseEntity<AbstractDto> buildResponseEntity(ServiceResponseDto serviceResponseDto) {
        AbstractDto dto = serviceResponseDto.getDataDto();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (serviceResponseDto.getResponseCode() == ResponseCode.SUCCESS) {
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<>(dto, httpStatus);
    }
}
