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
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ExceptionResponse marketNotFoundException(MarketNotFoundException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("marketNotFound.code"),
                getMessage("marketNotFound.message")
        );
    }

    @ExceptionHandler(MarketUnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ExceptionResponse marketUnauthorizedException(MarketUnauthorizedException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("marketUnauthorized.code"),
                getMessage("marketUnauthorized.message")
        );
    }

    @ExceptionHandler(MarketInterestNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ExceptionResponse marketInterestNotFoundException(MarketInterestNotFoundException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("marketInterestNotFound.code"),
                getMessage("marketInterestNotFound.message")
        );
    }

    @ExceptionHandler(DuplicateMarketInterestException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ExceptionResponse duplicateMarketInterestException(DuplicateMarketInterestException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("duplicateMarketInterest.code"),
                getMessage("duplicateMarketInterest.message")
        );
    }

    /**
     * Booth
     */
    @ExceptionHandler(BoothNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse marketNotFoundException(BoothNotFoundException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("boothNotFound.code"),
                getMessage("boothNotFound.message")
        );
    }

    @ExceptionHandler(BoothInterestNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse boothInterestNotFoundException(BoothInterestNotFoundException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("boothInterestNotFound.code"),
                getMessage("boothInterestNotFound.message")
        );
    }

    @ExceptionHandler(DuplicateBoothInterestException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse duplicateBoothInterestException(DuplicateBoothInterestException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("duplicateBoothInterest.code"),
                getMessage("duplicateBoothInterest.message")
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

    @ExceptionHandler(UserAlreadyDeletedCException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse userAlreadyDeletedCException(HttpServletRequest request, UserAlreadyDeletedCException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("userAlreadyDeleted.code"),
                getMessage("userAlreadyDeleted.message")
        );
    }

    @ExceptionHandler(EmailAlreadyExistCException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse emailAlreadyExistCException(HttpServletRequest request, EmailAlreadyExistCException e) {
        log.info(String.valueOf(e));
        return new ExceptionResponse(
                getMessage("emailAlreadyExist.code"),
                getMessage("emailAlreadyExist.message")
        );
    }

    private String getMessage(String code) {
        return getMessage(code, null);
    }

    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
