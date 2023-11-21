package es.leanmind.mocks;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class UserServiceShould {
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
        var repository = new UserRepositoryStub();
        var service = new UserService(repository);
        var irrelevantName = "irrelevantName";
        var user = new User();
        repository.stubListOfUsersByName = List.of(user);

        var users = service.findUsers(irrelevantName);

        assertThat(users).containsOnly(user);
    }

    @Test
    void search_users_by_surname_when_nothing_is_found_by_name() {
        var repository = new UserRepositoryStub();
        var service = new UserService(repository);
        var irrelevantName = "irrelevantName";
        var user = new User();
        repository.stubListOfUsersBySurname = List.of(user);

        var users = service.findUsers(irrelevantName);

        assertThat(users).containsOnly(user);
    }
}
