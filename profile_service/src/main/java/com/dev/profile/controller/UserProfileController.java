package com.dev.profile.controller;

import com.dev.profile.dto.ApiResponse;
import com.dev.profile.dto.response.ProfileCreationResponse;
import com.dev.profile.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileController {

    UserProfileService userProfileService;

    @GetMapping("/user/{profileId}")
    ApiResponse<ProfileCreationResponse> getProfile(@PathVariable String profileId) {
        return ApiResponse.<ProfileCreationResponse>builder()
                .result(userProfileService.getProfile(profileId))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    ApiResponse<List<ProfileCreationResponse>> getProfile() {
        return ApiResponse.<List<ProfileCreationResponse>>builder()
                .result(userProfileService.getAllProfiles())
                .build();
    }
}
