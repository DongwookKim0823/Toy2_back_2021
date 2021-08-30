package team2.study_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team2.study_project.domain.User;
import team2.study_project.jwt.SecurityUtil;
import team2.study_project.repository.UserRepository;

import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 이메일 검증
     */
    @Transactional
    public String validateEmail(User user) {

        String email = user.getEmail();

        Optional<User> byEmail = userRepository.findByEmail(email);
        if(byEmail.isPresent())
            return "failed";

        userRepository.save(user);
        return "success";
    }

    public Optional<Long> getUserId() {
        return SecurityUtil.getCurrentUserId();
    }
}
