package com.solmi.shorket.global;

import com.solmi.shorket.global.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(EmailLoginFailedCException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse emailLoginFailedException(HttpServletRequest request, EmailLoginFailedCException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("emailLoginFailed.code"),
                getMessage("emailLoginFailed.message")
        );
    }

    @ExceptionHandler(UserSignupFailedCException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse userSignupFailedException(HttpServletRequest request, UserSignupFailedCException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("userSignupFailed.code"),
                getMessage("userSignupFailed.message")
        );
    }

    @ExceptionHandler(UserNotFoundCException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse userNotFoundException(HttpServletRequest request, UserNotFoundCException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("userNotFound.code"),
                getMessage("userNotFound.message")
        );
    }

    @ExceptionHandler(RefreshTokenExpiredCException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse refreshTokenExpiredException(HttpServletRequest request, RefreshTokenExpiredCException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("refreshTokenExpired.code"),
                getMessage("refreshTokenExpired.message")
        );
    }

    @ExceptionHandler(RefreshTokenNotFoundCException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse refreshTokenNotFoundException(HttpServletRequest request, RefreshTokenNotFoundCException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("refreshTokenNotFound.code"),
                getMessage("refreshTokenNotFound.message")
        );
    }

    @ExceptionHandler(UserRoleNotFoundCException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse userRoleNotFoundException(HttpServletRequest request, UserRoleNotFoundCException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("userRoleNotFound.code"),
                getMessage("userRoleNotFound.message")
        );
    }

    /**
     * Market
     */
    @ExceptionHandler(MarketNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse marketNotFoundException(MarketNotFoundException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("marketNotFound.code"),
                getMessage("marketNotFound.message")
        );
    }

    @ExceptionHandler(MarketInterestNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse marketInterestNotFoundException(MarketInterestNotFoundException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("marketInterestNotFound.code"),
                getMessage("marketInterestNotFound.message")
        );
    }

    @ExceptionHandler(DuplicateMarketInterestException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse duplicateMarketInterestException(DuplicateMarketInterestException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("duplicateMarketInterest.code"),
                getMessage("duplicateMarketInterest.message")
        );
    }

    @ExceptionHandler(GetBoothFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse getBoothFailedCException(HttpServletRequest request, BoothNotFoundException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("getBoothFailed.code"),
                getMessage("getBoothFailed.message")
        );
    }

    @ExceptionHandler(ChangePasswordFailedCException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse changePasswordFailedCException(HttpServletRequest request, ChangePasswordFailedCException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("changePasswordFailed.code"),
                getMessage("changePasswordFailed.message")
        );
    }

    private String getMessage(String code) {
        return getMessage(code, null);
    }

    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
