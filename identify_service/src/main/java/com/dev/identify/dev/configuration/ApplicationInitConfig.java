package com.dev.identify.dev.configuration;

import com.dev.identify.dev.entity.Permission;
import com.dev.identify.dev.entity.Roles;
import com.dev.identify.dev.entity.User;
import com.dev.identify.dev.enums.Role;
import com.dev.identify.dev.repository.PermissionRepository;
import com.dev.identify.dev.repository.RoleRepository;
import com.dev.identify.dev.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Profile("dev")
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME ="admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

    @ConditionalOnProperty(value = "spring.datasource.url", havingValue = "jdbc:mysql://localhost:3306/identify")
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, PermissionRepository permissionRepository, RoleRepository roleRepository) {
        log.info("Init Application config .............");
        return args -> {

            userRepository.deleteAll();

            // add Permission list
            log.info("Add permission: READ-DATA, WRITE-DATA, REJECT-DATA");

            var permissionList = Arrays.asList(
                    Permission.builder().name("READ-DATA").description("Permission read data").build(),
                    Permission.builder().name("WRITE-DATA").description("Permission write data").build(),
                    Permission.builder().name("REJECT-DATA").description("Permission reject data").build()
            );

            permissionRepository.saveAll(permissionList);

//             add Role admin
            log.info("Add role ADMIN with permission: READ-DATA, WRITE-DATA, REJECT-DATA");
            Roles rolesADMIN = Roles
                    .builder()
                    .name(Role.ADMIN.name())
                    .description("ROLE ADMIN")
                    .permissions(new HashSet<>(permissionList))
                    .build();

            var permissionUSER = Arrays.asList(
                    Permission.builder().name("READ-DATA").description("Permission read data").build()
            );
            Roles rolesUSER = Roles
                    .builder()
                    .name(Role.USER.name())
                    .description("ROLE USER")
                    .permissions(new HashSet<>(permissionUSER))
                    .build();

            roleRepository.save(rolesADMIN);
            roleRepository.save(rolesUSER);

            boolean userAdminExist = userRepository.existsByUsername("admin");

            if (!userAdminExist) {

                Set<Roles> roleAdmin = new HashSet<>();
                Roles role = Roles.builder()
                        .name(Role.ADMIN.name())
                        .build();
                roleAdmin.add(role);

                User userAdmin = User
                        .builder()
                        .username(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .roles(roleAdmin)
                        .build();
                userRepository.save(userAdmin);
                log.warn("Admin user has been created with username: admin . password: admin");
            }
        };
    }
}
