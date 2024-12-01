package com.dev.profile.dto.request;

import java.time.LocalDate;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
