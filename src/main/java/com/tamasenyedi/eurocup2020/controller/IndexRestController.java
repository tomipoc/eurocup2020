package com.tamasenyedi.eurocup2020.controller;

import com.tamasenyedi.eurocup2020.dto.*;
import com.tamasenyedi.eurocup2020.helper.response.ResponseCode;
import com.tamasenyedi.eurocup2020.helper.response.RestResponseEntityBuilder;
import com.tamasenyedi.eurocup2020.service.ListService;
import com.tamasenyedi.eurocup2020.service.WinnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexRestController {

    private WinnerService winnerService;
    private ListService listService;

    public IndexRestController(WinnerService winnerService, ListService listService) {
        this.winnerService = winnerService;
        this.listService = listService;
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

    @GetMapping(value = {"/winners"})
    public ResponseEntity<AbstractDto> winners() {
        List<WinnerDto> winners = listService.retrieveWinnersList();
        winners.forEach(w -> System.out.println(w.toString()));

        WinnerResponseDto listDto = new WinnerResponseDto();
        listDto.setWinnerList(winners);

        return new ResponseEntity<>(listDto, HttpStatus.OK);
    }
}
