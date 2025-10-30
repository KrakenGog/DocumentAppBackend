package com.mse.dapp.service;


import com.mse.dapp.model.Role;
import com.mse.dapp.model.User;
import com.mse.dapp.repository.RoleRepo;
import com.mse.dapp.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepo userRepository;
    private RoleRepo roleRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepo.class);
        roleRepository = mock(RoleRepo.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserService(userRepository, roleRepository, passwordEncoder);
    }

    @Nested
    @DisplayName("Тесты loadUserByUsername")
    class LoadUserByUsernameTests {

        @Test
        @DisplayName("Успешная загрузка пользователя")
        void loadUserByUsername_success() {
            User user = new User("alice", "encodedPass");
            when(userRepository.findByLogin("alice")).thenReturn(Optional.of(user));

            var result = userService.loadUserByUsername("alice");

            assertEquals("alice", result.getUsername());
            verify(userRepository, times(1)).findByLogin("alice");
        }

        @Test
        @DisplayName("Пользователь не найден")
        void loadUserByUsername_notFound() {
            when(userRepository.findByLogin("bob")).thenReturn(Optional.empty());

            assertThrows(UsernameNotFoundException.class,
                    () -> userService.loadUserByUsername("bob"));
        }
    }

    @Nested
    @DisplayName("Тесты registerUser")
    class RegisterUserTests {

        @Test
        @DisplayName("Регистрация нового пользователя с ролью ROLE_USER")
        void registerUser_success() {
            when(userRepository.existsByLogin("newUser")).thenReturn(false);
            when(passwordEncoder.encode("12345")).thenReturn("encoded12345");
            when(roleRepository.findByName("ROLE_USER"))
                    .thenReturn(Optional.of(new Role(1L, "ROLE_USER")));

            when(userRepository.save(Mockito.any(User.class)))
    .thenAnswer(invocation -> invocation.getArgument(0));
;

            User result = userService.registerUser("newUser", "12345");
            assertTrue(result.getRoles().size() == 1);
            assertEquals("newUser", result.getUsername());
            assertEquals("encoded12345", result.getPassword());
            assertTrue(result.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
        }

        @Test
        @DisplayName("Регистрация пользователя с уже существующим логином")
        void registerUser_alreadyExists() {
            when(userRepository.existsByLogin("alice")).thenReturn(true);

            assertThrows(RuntimeException.class,
                    () -> userService.registerUser("alice", "12345"));
        }

        @Test
        @DisplayName("Регистрация без роли ROLE_USER в БД")
        void registerUser_noRoleFound() {
            when(userRepository.existsByLogin("bob")).thenReturn(false);
            when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.empty());

            assertThrows(RuntimeException.class,
                    () -> userService.registerUser("bob", "12345"));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"user1", "user2", "user3"})
    @DisplayName("Параметризованный тест: проверка поиска разных пользователей")
    void loadUserByUsername_parameterized(String username) {
        User user = new User(username, "pass");
        when(userRepository.findByLogin(username)).thenReturn(Optional.of(user));

        var result = userService.loadUserByUsername(username);

        assertEquals(username, result.getUsername());
    }
}
