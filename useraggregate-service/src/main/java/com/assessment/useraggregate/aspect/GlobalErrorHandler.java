package com.assessment.useraggregate.aspect;

import com.assessment.useraggregate.dto.JSendDto;
import com.assessment.useraggregate.dto.JSendStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    private JSendDto jSendParser(Exception ex)
    {
        JSendDto jSendDto = new JSendDto();
        jSendDto.setStatus(JSendStatus.ERROR.toString().toLowerCase());
        jSendDto.setMessage(ex.getMessage());

        return jSendDto;
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<JSendDto> globalErrorHandler(Exception ex){
        JSendDto jSendDto = jSendParser(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jSendDto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<JSendDto> unproccsableEntityHandler(IllegalArgumentException ex){
        JSendDto jSendDto = jSendParser(ex);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(jSendDto);
    }

}
