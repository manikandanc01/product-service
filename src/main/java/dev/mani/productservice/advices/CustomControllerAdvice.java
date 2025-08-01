package dev.mani.productservice.advices;

import dev.mani.productservice.dtos.ErrorDto;
import dev.mani.productservice.exceptions.ExternalServiceException;
import dev.mani.productservice.exceptions.NoProductsFoundException;
import dev.mani.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CustomControllerAdvice {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> handleNullPointerException(){
       ErrorDto errorDto = new ErrorDto();
       errorDto.setMessage("Something went wrong");

        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<ErrorDto> handleExternalServiceException(ExternalServiceException ex){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException (ProductNotFoundException ex){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoProductsFoundException.class)
    public ResponseEntity<ErrorDto> handleNoProductsFoundException (NoProductsFoundException ex){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
