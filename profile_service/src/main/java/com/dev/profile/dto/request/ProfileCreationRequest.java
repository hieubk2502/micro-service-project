package com.dev.profile.dto.request;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.core.serializer.Deserializer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProfileCreationRequest {

    String userId;

    String firstName;

    String lastName;

    LocalDate dob;

    String city;

}
