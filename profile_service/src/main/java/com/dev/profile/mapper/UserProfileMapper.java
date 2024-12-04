package com.dev.profile.mapper;

import com.dev.profile.dto.request.ProfileCreationRequest;
import com.dev.profile.dto.response.ProfileCreationResponse;
import com.dev.profile.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileCreationRequest request);

    ProfileCreationResponse toUserProfileResponse(UserProfile entity);
}
