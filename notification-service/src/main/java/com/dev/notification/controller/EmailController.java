package com.dev.notification.controller;

import com.dev.notification.dto.ApiResponse;
import com.dev.notification.dto.request.SendEmailRequest;
import com.dev.notification.dto.response.EmailResponse;
import com.dev.notification.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailController {

    EmailService emailService;

    @PostMapping("/create-email")
    public ApiResponse<EmailResponse> sendEmail(@RequestBody SendEmailRequest requestDTO){
        log.info("Call API success");
        return ApiResponse.<EmailResponse>builder()
                .result(emailService.sendEmail(requestDTO))
                .build();
    }
}
