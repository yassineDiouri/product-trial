package yas.dio.product_trial.application.ports.in;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;
import yas.dio.product_trial.domain.model.User;
import yas.dio.product_trial.infrastructure.out.jpa.UserJpaRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@Transactional
class CreateUserCommandTest {

    @Autowired
    private CreateUserCommand command;
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    public void createUser_shouldSaveUser() {
        // GIVEN
        User user = User.builder()
                .username("jdoe")
                .firstname("John")
                .email("john.doe@example.com")
                .password("secret")
                .build();

        // WHEN
        command.createUser(user);

        // THEN
        assertThat(userJpaRepository.findById("jdoe")).isPresent();
    }
}