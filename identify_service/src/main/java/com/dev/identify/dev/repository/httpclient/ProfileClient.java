package com.dev.identify.dev.repository.httpclient;

import com.dev.identify.dev.configuration.AuthenticationRequestInterceptor;
import com.dev.identify.dev.dto.request.ProfileCreationRequest;
import com.dev.identify.dev.dto.response.UserProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profile-service", url = "${app.services.profile}",
        configuration = {AuthenticationRequestInterceptor.class})
public interface ProfileClient {

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    UserProfileResponse create(@RequestBody ProfileCreationRequest request);


}
