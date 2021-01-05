package com.tamasenyedi.eurocup2020.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WinnerDto extends AbstractDto {
    String email;
    String coupon;
}
