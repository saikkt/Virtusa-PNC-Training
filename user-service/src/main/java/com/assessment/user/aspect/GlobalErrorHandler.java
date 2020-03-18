package com.assessment.user.aspect;

import com.assessment.user.dto.JSendStatus;
import com.assessment.user.dto.JSendDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    private JSendDto jSendStructure(Exception ex)
    {
        JSendDto jSendDto = new JSendDto();
        jSendDto.setStatus(JSendStatus.ERROR.toString().toLowerCase());
        jSendDto.setMessage(ex.getMessage());

        return jSendDto;
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<JSendDto> globalErrorHandler(Exception ex){
        JSendDto jSendDto = jSendStructure(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jSendDto);
    }

//    @ExceptionHandler(.class)
//    protected ResponseEntity<JSendStructure> methodHandler(HttpRequestMethodNotSupportedException ex){
//        JSendStructure jSendStructure = jSendStructure(ex);
//        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(jSendStructure);
//    }
}
