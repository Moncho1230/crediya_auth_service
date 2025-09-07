package co.com.pragma.model.shared.exceptions;

import lombok.Getter;

public interface ResponseMessage  {
    public String getMessage();
    public String getCode();
}