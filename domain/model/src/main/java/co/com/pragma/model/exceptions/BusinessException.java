package co.com.pragma.model.exceptions;
import co.com.pragma.model.shared.exceptions.BusinessErrorCode;
import lombok.Getter;

import java.util.List;

@Getter
public class BusinessException extends RuntimeException{
    private String code;
    private List<String> errors;

    public BusinessException(BusinessErrorCode errorCode){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.errors = null;
    }

    public BusinessException(List<String> errors, BusinessErrorCode errorCode){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.errors = errors;
    }
}
