package com.dev.notification.service;

import com.dev.notification.dto.request.EmailRequest;
import com.dev.notification.dto.request.SendEmailRequest;
import com.dev.notification.dto.request.Sender;
import com.dev.notification.dto.response.EmailResponse;
import com.dev.notification.exception.AppException;
import com.dev.notification.exception.ErrorCode;
import com.dev.notification.repository.httpclient.EmailClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {

    EmailClient emailClient;

    @NonFinal
    String apiKey = "your-key-gen";

    public EmailResponse sendEmail( SendEmailRequest requestDTO) {

        SendEmailRequest emailRequest = SendEmailRequest
                .builder()
                .sender(Sender
                        .builder()
                        .name("H3")
                        .email("your-email@gmail.com")
                        .build())
                .htmlContent(requestDTO.getHtmlContent())
                .subject(requestDTO.getSubject())
                .to(requestDTO.getTo())
                .build();

try {
    return emailClient.sendEmail(apiKey,emailRequest);

}catch (Exception e) {
    throw new AppException(ErrorCode.CANNOT_SEND_EMAIL);
}
    }
}
