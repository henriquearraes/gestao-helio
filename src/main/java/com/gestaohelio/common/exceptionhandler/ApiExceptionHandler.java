package com.gestaohelio.common.exceptionhandler;

import com.gestaohelio.common.exceptions.ElementoNaoEncontradoException;
import com.gestaohelio.common.exceptions.NaoPodeSerDataAnteriorException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais campos estão inválidos");
        problemDetail.setType(URI.create("https://heliofilhocardans.com/erros/campos-invalidos"));

        Map<String, String> fields = ex.getBindingResult().getAllErrors()
                .stream()
                .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
                            objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));
        problemDetail.setProperty("fields", fields);


        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    @ExceptionHandler(ElementoNaoEncontradoException.class)
    ProblemDetail handleElementoNaoEncontradoException(ElementoNaoEncontradoException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        problemDetail.setTitle("Elemento não encontrado!");
        problemDetail.setDetail("É preciso informar um número de elemento válido!");
        problemDetail.setType(URI.create("https://heliofilhocardans.com/erros/elemento-nao-encontrado"));
        problemDetail.setProperty("TimeStamp", Instant.now());

        return problemDetail;
    }

    @ExceptionHandler(NaoPodeSerDataAnteriorException.class)
    ProblemDetail handleNaoPodeSerDataAnteriorException(NaoPodeSerDataAnteriorException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_ACCEPTABLE, ex.getLocalizedMessage());
        problemDetail.setTitle("Data saída inválida");
        problemDetail.setDetail("É preciso informar uma data que seja após a data de entrada");
        problemDetail.setType(URI.create("http://heliofilhocardans.com/erros/data-invalida"));
        problemDetail.setProperty("TimeStamp:", Instant.now());

        return problemDetail;
    }


}
