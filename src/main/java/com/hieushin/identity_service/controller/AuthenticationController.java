package com.hieushin.identity_service.controller;

import com.hieushin.identity_service.dto.request.ApiResponse;
import com.hieushin.identity_service.dto.request.AuthenticationRequest;
import com.hieushin.identity_service.dto.request.IntrospectRequest;
import com.hieushin.identity_service.dto.response.AuthenticationResponse;
import com.hieushin.identity_service.dto.response.IntrospectResponse;
import com.hieushin.identity_service.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws KeyLengthException {
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
}
