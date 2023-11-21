package es.leanmind.mocks;

import org.junit.jupiter.api.Test;

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
}
