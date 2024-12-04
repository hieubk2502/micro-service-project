package com.dev.profile.service;

import com.dev.profile.dto.request.ProfileCreationRequest;
import com.dev.profile.dto.response.ProfileCreationResponse;
import com.dev.profile.entity.UserProfile;
import com.dev.profile.mapper.UserProfileMapper;
import com.dev.profile.repository.UserProfileRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserProfileMapper userProfileMapper;

    public ProfileCreationResponse createProfile(ProfileCreationRequest request) {
        UserProfile userProfile = userProfileMapper.toUserProfile(request);
        userProfile = userProfileRepository.save(userProfile);

        return userProfileMapper.toUserProfileResponse(userProfile);
    }


    public ProfileCreationResponse getProfile(String id) {
        UserProfile userProfile =
                userProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));

        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<ProfileCreationResponse> getAllProfiles() {
        List<UserProfile> userProfiles =
                userProfileRepository.findAll();

        return userProfiles.stream().map(userProfileMapper::toUserProfileResponse).toList();
    }
}