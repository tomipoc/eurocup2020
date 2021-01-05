package com.tamasenyedi.eurocup2020.controller;

import com.tamasenyedi.eurocup2020.dto.AbstractDto;
import com.tamasenyedi.eurocup2020.dto.BasicResponseDto;
import com.tamasenyedi.eurocup2020.dto.ServiceResponseDto;
import com.tamasenyedi.eurocup2020.dto.UserDto;
import com.tamasenyedi.eurocup2020.helper.response.RestResponseEntityBuilder;
import com.tamasenyedi.eurocup2020.repository.UserRepository;
import com.tamasenyedi.eurocup2020.service.WinnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexRestController {

    private WinnerService winnerService;

    public IndexRestController(WinnerService winnerService) {
        this.winnerService = winnerService;
    }

    @PostMapping(
            value = {"", "/", "/index"},
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AbstractDto> incomingCoupon(@RequestBody final UserDto userData) {
        ServiceResponseDto<BasicResponseDto> serviceResponseDto = winnerService.verify(
                userData.getEmail(),
                userData.getAge(),
                userData.getCoupon(),
                userData.getTerritory());

        ResponseEntity<AbstractDto> response =
                RestResponseEntityBuilder.getInstance().buildResponseEntity(serviceResponseDto);

        return response;
    }

//    @PostMapping(
//            value = {"winners"},
//            consumes = MediaType.APPLICATION_JSON_VALUE
//    )
//    public ResponseEntity<AbstractDto> winners() {
//        ServiceResponseDto<BasicResponseDto> serviceResponseDto = listService.listWinners();
//
//        ResponseEntity<AbstractDto> response =
//                RestResponseEntityBuilder.getInstance().buildResponseEntity(serviceResponseDto);
//
//        return response;
//    }
}
