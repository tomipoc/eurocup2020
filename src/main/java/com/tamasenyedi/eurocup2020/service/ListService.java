package com.tamasenyedi.eurocup2020.service;

import com.tamasenyedi.eurocup2020.dto.WinnerDto;

import java.util.List;

public interface ListService {
    List<WinnerDto> retrieveWinnersList();
}
