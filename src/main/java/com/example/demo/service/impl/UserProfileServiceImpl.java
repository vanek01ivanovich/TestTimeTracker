package com.example.demo.service.impl;

import com.example.demo.data.entity.Language;
import com.example.demo.data.entity.User;
import com.example.demo.data.entity.UserProfile;
import com.example.demo.data.enums.ELanguage;
import com.example.demo.data.enums.Position;
import com.example.demo.persistence.repository.LanguageRepository;
import com.example.demo.persistence.repository.UserProfileRepository;
import com.example.demo.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final LanguageRepository languageRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, LanguageRepository languageRepository) {
        this.userProfileRepository = userProfileRepository;
        this.languageRepository = languageRepository;
    }

    @Override
    @Transactional
    public void createUserProfile(User user) {
        log.info("createUserProfile with user id {}", user.getId());

        Language no_such_role = languageRepository.findByName(ELanguage.ENGLISH)
                .orElseThrow(() -> new RuntimeException("No Such Role"));
        log.info("language {}", no_such_role.getId());

        UserProfile userProfile = UserProfile.builder()
                .position(Position.DEVELOPER)
                .user(user)
                .age(22)
                .languages(Stream.of(languageRepository.findByName(ELanguage.ENGLISH)
                        .orElseThrow(() -> new RuntimeException("No Such Role")))
                        .collect(Collectors.toSet()))
                .build();

        log.info("userProfile {}", userProfile);
        try {
            userProfileRepository.save(userProfile);
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }
}
