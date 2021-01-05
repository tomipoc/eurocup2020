package com.tamasenyedi.eurocup2020.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WinnerResponseDto extends AbstractDto {
    private List<WinnerDto> winnerList;
}
