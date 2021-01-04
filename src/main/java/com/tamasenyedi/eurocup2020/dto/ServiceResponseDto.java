package com.tamasenyedi.eurocup2020.dto;

import com.tamasenyedi.eurocup2020.helper.response.ResponseCode;
import lombok.Getter;

import java.util.Optional;

@Getter
public class ServiceResponseDto<T extends AbstractDto> {

    private Optional<T> optionalDataDto;
    private ResponseCode responseCode;

    public ServiceResponseDto(T dataDto, ResponseCode responseCode) {
        this.optionalDataDto = Optional.of(dataDto);
        this.responseCode = responseCode;
    }

    public T getDataDto() {
        return optionalDataDto.orElseGet(null);
    }
}
