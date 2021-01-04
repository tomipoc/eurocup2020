package com.tamasenyedi.eurocup2020.dto;

import com.tamasenyedi.eurocup2020.consts.ResponseConsts;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BasicResponseDto extends AbstractDto {

    private String status;
    private String status_text;

    public void statusOK(final String status_text) {
        setAll(ResponseConsts.RESPONSE_STATUS_OK, status_text);
    }

    public void statusFAIL(final String status_text) {
        setAll(ResponseConsts.RESPONSE_STATUS_FAIL, status_text);
    }

    private void setAll(final String status, final String status_text) {
        setStatus(status);
        setStatus_text(status_text);
    }
}
