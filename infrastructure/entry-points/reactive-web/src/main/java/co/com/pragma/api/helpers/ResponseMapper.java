package co.com.pragma.api.helpers;

import co.com.pragma.api.dto.ResponseDto;
import co.com.pragma.model.shared.exceptions.ResponseMessage;

public class ResponseMapper {

    public static <T, E extends ResponseMessage> ResponseDto<T> mapBodyResponse(E responseCode, T data){
        return  new ResponseDto<>(responseCode.getMessage(), responseCode.getCode(), data);
    }

}