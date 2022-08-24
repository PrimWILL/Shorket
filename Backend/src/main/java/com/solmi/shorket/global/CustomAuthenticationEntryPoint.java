package com.solmi.shorket.global;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final MessageSource messageSource;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        String exception = (String) request.getAttribute("exception");
        log.info("exception: {}", exception);

        if (exception == null) {
            setResponse(response, "unknownAuthenticaitonError");
        } else if (exception.equals("expiredJwt")) {
            setResponse(response, exception);
        } else if (exception.equals("incorrectJwtSign")) {
            setResponse(response, exception);
        } else if (exception.equals("unsupportedJwt")) {
            setResponse(response, exception);
        } else if (exception.equals("illegalArgumentJwt")) {
            setResponse(response, exception);
        }
    }

    private void setResponse(HttpServletResponse response, String exception) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("code", getMessage(exception + ".code"));
        responseJson.addProperty("message", getMessage(exception + ".message"));

        response.getWriter().print(responseJson);
    }

    private String getMessage(String code) {
        return getMessage(code, null);
    }

    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
