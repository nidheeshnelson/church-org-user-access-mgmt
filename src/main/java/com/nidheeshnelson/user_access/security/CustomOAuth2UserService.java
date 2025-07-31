package com.nidheeshnelson.user_access.security;

import com.nidheeshnelson.user_access.entity.User;
import com.nidheeshnelson.user_access.repository.UserRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2UserService;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        // Get user info from OAuth2 provider
        OAuth2User user = super.loadUser(userRequest);
        String email = user.getAttribute("email");

        // Check if user already exists, else create a new one
        User existingUser = userRepository.findByEmail(email).orElse(null);
        if (existingUser == null) {
            existingUser = new User();
            existingUser.setEmail(email);
            userRepository.save(existingUser);
        }

        return user;
    }
}
