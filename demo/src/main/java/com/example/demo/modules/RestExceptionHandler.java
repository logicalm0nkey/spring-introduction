package com.example.demo.modules;

import com.example.demo.model.ErrorResponse;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * ControllerAdvice/RestControllerAdvice をつけることで、コントローラ全体にわたる共通処理を書ける。
 * いわゆるアスペクト志向におけるアドヴァイスである。
 */
@RestControllerAdvice
public class RestExceptionHandler {
    private final Logger logger = LogManager.getLogger();

    /**
     * 集約例外ハンドラ。 全てのコントローラの end pt. の例外をここで拾う。
     * @param ex
     * @return
     */
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> onError(Exception ex) {
        logger.error(ex.getMessage() + "on console", ex);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorResponse response = new ErrorResponse("例外発生", ex.getMessage(), status.value());

        return new ResponseEntity<ErrorResponse>(response, status);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 半角だけ trim される
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
