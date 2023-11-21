package es.leanmind.mocks;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserServiceShould {
    @Test
    void save_user_through_repository() {
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);
        User user = new User();

        userService.updatePassword(user, new Password("1234"));

        verify(userRepository).save(user);
    }
}
