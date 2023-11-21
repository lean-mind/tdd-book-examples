package es.leanmind.mocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UserServiceShould {
    private Repository repository;
    private UserService service;
    private String irrelevantName = "irrelevantName";
    private User user;

    @BeforeEach
    void setup() {
        repository = mock(Repository.class);
        service = new UserService(repository);
        user = new User();
    }
    @Test
    void save_user_through_repository() {
        var userRepository = new UserRepositorySpy();
        var userService = new UserService(userRepository);
        var user = new User();

        userService.updatePassword(user, new Password("1234"));

        assertThat(userRepository.savedUser).isEqualTo(user);
    }

    @Test
    void search_users_by_name() {
        when(repository.findUsersByName(irrelevantName))
                .thenReturn(List.of(user));

        assertThat(service.findUsers(irrelevantName)).containsOnly(user);
    }

    @Test
    void search_users_by_surname_when_nothing_is_found_by_name() {
        when(repository.findUsersBySurname(irrelevantName))
                .thenReturn(List.of(user));

        assertThat(service.findUsers(irrelevantName)).containsOnly(user);
    }
}
