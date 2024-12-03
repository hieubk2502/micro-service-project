package api.gateway.service;

import api.gateway.dto.ApiResponse;
import api.gateway.dto.request.IntrospectRequest;
import api.gateway.dto.response.IntrospectResponse;
import api.gateway.repository.IdentityClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService {

    IdentityClient identityClient;


    public Mono<ApiResponse<IntrospectResponse>> introspect(String token) {
        return identityClient
                .introspect(IntrospectRequest
                        .builder()
                        .token(token)
                        .build());
    }
}
