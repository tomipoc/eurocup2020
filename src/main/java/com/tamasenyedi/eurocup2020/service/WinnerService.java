package com.tamasenyedi.eurocup2020.service;

import com.tamasenyedi.eurocup2020.dto.BasicResponseDto;
import com.tamasenyedi.eurocup2020.dto.ServiceResponseDto;

public interface WinnerService {
    ServiceResponseDto<BasicResponseDto> verify(String email, int age, String couponCode, String territory);
}
