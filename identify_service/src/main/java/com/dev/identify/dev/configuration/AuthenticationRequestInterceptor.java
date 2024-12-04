package com.dev.identify.dev.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//@Component
@Slf4j
public class AuthenticationRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            var authHeader = servletRequestAttributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);

            log.info("Header: {}", authHeader);

            if(StringUtils.hasText(authHeader)) {
                requestTemplate.header(HttpHeaders.AUTHORIZATION, authHeader);
            }

    }
}
