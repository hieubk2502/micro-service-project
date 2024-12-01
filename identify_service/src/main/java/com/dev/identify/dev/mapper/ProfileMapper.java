package com.dev.identify.dev.mapper;

import com.dev.identify.dev.dto.request.ProfileCreationRequest;
import com.dev.identify.dev.dto.request.UserCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileCreationRequest toProfileCreationRequest(UserCreateRequest request);


}
