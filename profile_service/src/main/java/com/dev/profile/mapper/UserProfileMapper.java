package com.dev.profile.mapper;

import com.dev.profile.dto.request.ProfileCreationRequest;
import com.dev.profile.dto.response.UserProfileResponse;
import com.dev.profile.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileCreationRequest request);

    UserProfileResponse toUserProfileResponse(UserProfile entity);
}
