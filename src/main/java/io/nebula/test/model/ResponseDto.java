package io.nebula.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> implements Serializable {
    private int code;
    private String msg;
    private T payload;

    public static <T> ResponseDto buildResponse(int code, String msg, T payload){
        return new ResponseDto().builder().code(code).msg(msg).payload(payload).build();
    }
}
