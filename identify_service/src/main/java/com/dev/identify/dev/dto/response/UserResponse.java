package com.dev.identify.dev.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    String id;

    String username;

    String firstname;

    String lastname;

    LocalDate dob;

    Set<RoleResponse> roles;
}
