package com.plata.Plata.api.advice;

import com.plata.Plata.core.dto.BaseApiResponseDTO;
import com.plata.Plata.core.exception.TranslatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class AppControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppControllerAdvice.class);

    @ExceptionHandler(TranslatedException.class)
    public ResponseEntity<BaseApiResponseDTO<Object>> handleCheckedErrors(TranslatedException e) {
        LOGGER.warn("[Status {}] - {}", e.getStatus().value(), e.getMessage());

        var dto = new BaseApiResponseDTO<>();
        dto.setMessage(e.getMessage());

        return ResponseEntity
                .status(e.getStatus())
                .body(dto);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<BaseApiResponseDTO<Object>> handleUnchecked(Throwable e) {
        var dto = new BaseApiResponseDTO<>();

        if (e instanceof MethodArgumentNotValidException argumentNotValidException) {
            var detailMessage = argumentNotValidException.getBody().getDetail();

            LOGGER.warn("[Status {}]  {}", HttpStatus.BAD_REQUEST, detailMessage);

            dto.setMessage(argumentNotValidException.getBody().getDetail());
            return ResponseEntity.badRequest().body(dto);
        }

        if (e instanceof NoResourceFoundException noResourceFoundException) {
            var detailMessage = noResourceFoundException.getBody().getDetail();
            LOGGER.warn("[Status {}]  {}", HttpStatus.NOT_FOUND, detailMessage);
            dto.setMessage(noResourceFoundException.getBody().getDetail());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
        }

        LOGGER.error("[Status {}]", HttpStatus.INTERNAL_SERVER_ERROR, e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(dto);
    }
}
