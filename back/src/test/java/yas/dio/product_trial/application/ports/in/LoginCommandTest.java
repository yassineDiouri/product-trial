package yas.dio.product_trial.application.ports.in;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;
import yas.dio.product_trial.application.ports.out.TokenProvider;
import yas.dio.product_trial.domain.exception.LoginFailureException;
import yas.dio.product_trial.infrastructure.out.jpa.UserJpaRepository;
import yas.dio.product_trial.infrastructure.out.jpa.entities.UserEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@Transactional
@DisplayName("LoginCommand Tests")
class LoginCommandTest {

    @Autowired
    private LoginCommand command;
    @Autowired
    private UserJpaRepository userRepository;
    @MockitoBean
    private TokenProvider tokenProvider;

    private final String TEST_EMAIL = "test@example.com";
    private final String TEST_PASSWORD = "password123";

    @BeforeEach
    void beforeEach() {
        userRepository.deleteAll();

        UserEntity testUser = new UserEntity();
        testUser.setEmail(TEST_EMAIL);
        testUser.setPassword(TEST_PASSWORD);
        testUser.setUsername("testuser");
        testUser.setFirstname("userFirstName");
        userRepository.save(testUser);
    }

    @Test
    @DisplayName("Should successfully login with valid credentials")
    void shouldLoginSuccessfullyWithValidCredentials() {
        // Given
        when(tokenProvider.generateToken(any(), any())).thenReturn("token");

        // When
        String token = command.login(TEST_EMAIL, TEST_PASSWORD);

        // Then
        assertThat(token).isNotNull();
        assertThat(token).isNotEmpty();
        assertThat(token).isEqualTo("token");
    }

    @Test
    @DisplayName("Should throw LoginFailureException when user does not exist")
    void shouldThrowExceptionWhenUserDoesNotExist() {
        // Given
        String nonExistentEmail = "nonexistent@example.com";

        // When & Then
        assertThatThrownBy(() -> command.login(nonExistentEmail, TEST_PASSWORD))
                .isInstanceOf(LoginFailureException.class)
                .hasMessage("Invalid email or password");
    }

    @Test
    @DisplayName("Should throw LoginFailureException when password is incorrect")
    void shouldThrowExceptionWhenPasswordIsIncorrect() {
        // Given
        String wrongPassword = "wrongpassword";

        // When & Then
        assertThatThrownBy(() -> command.login(TEST_EMAIL, wrongPassword))
                .isInstanceOf(LoginFailureException.class)
                .hasMessage("Invalid email or password");
    }
}