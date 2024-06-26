package cmc.cmc15th_hackathon.global.exception;

import cmc.cmc15th_hackathon.global.common.CustomResponseEntity;
import cmc.cmc15th_hackathon.global.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CommonRestExceptionHandler extends RuntimeException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public CustomResponseEntity<String> handleExceptionHandler(HttpServletRequest request, Exception e) {
        log.error("defaultExceptionHandler", e);
        return CustomResponseEntity.fail(Result.UNEXPECTED_EXCEPTION);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomException.class)
    public CustomResponseEntity<String> handleCustomExceptionHandler(CustomException exception) {
        log.error("CustomExceptionHandler code : {}, message : {}",
                exception.getResult().getCode(), exception.getResult().getMessage());
        return CustomResponseEntity.fail(exception.getResult());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public CustomResponseEntity<String> illegalArgumentExceptionHandler(
            IllegalArgumentException e, HttpServletRequest request
    ) {
        log.error("url: \"{}\", message: {}", request.getRequestURI(), e.getMessage());

        return CustomResponseEntity.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public CustomResponseEntity<Object> handleBadRequest(
            MethodArgumentNotValidException e, HttpServletRequest request
    ) {
        FieldError error = e.getBindingResult().getFieldErrors().get(0);
        String errorMessage = "[" + error.getField() + "] " + error.getDefaultMessage();
        log.error("url: \"{}\", message: {}", request.getRequestURI(), errorMessage);

        return CustomResponseEntity.fail(errorMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
            MissingServletRequestParameterException.class
    )
    public CustomResponseEntity<String> handleBadRequest(
            MissingServletRequestParameterException e, HttpServletRequest request
    ) {
        String errorMessage = e.getParameterName() + " 값이 등록되지 않았습니다.";
        log.error("url: \"{}\", message: {}", request.getRequestURI(), errorMessage);

        return CustomResponseEntity.fail(errorMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
            MissingServletRequestPartException.class
    )
    public CustomResponseEntity<String> handleBadRequest(
            MissingServletRequestPartException e, HttpServletRequest request
    ) {
        String errorMessage = e.getRequestPartName() + " 값을 요청받지 못했습니다.";
        log.error("url: \"{}\", message: {}", request.getRequestURI(), errorMessage);

        return CustomResponseEntity.fail(errorMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public CustomResponseEntity<String> nullPointerExceptionHandler(
            Exception e, HttpServletRequest request
    ) {
        log.error("url: \"{}\", message: {}", request.getRequestURI(), e.getMessage());

        return CustomResponseEntity.fail(e.getMessage());
    }
}