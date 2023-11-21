package es.leanmind.mocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class UserServiceShould {
    private Repository repository;
    private UserService service;
    private BackupService backup;
    private final String irrelevantName = "irrelevantName";
    private User user;

    @BeforeEach
    void setup() {
        repository = mock(Repository.class);
        backup = mock(BackupService.class);
        service = new UserService(repository, backup);
        user = new User(false);
    }
    @Test
    void save_user_through_repository() {
        var userRepository = new UserRepositorySpy();
        var userService = new UserService(userRepository, backup);

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

    @Test
    void backup_premium_users_files() {
        var repository = mock(Repository.class);
        var backup = mock(BackupService.class);
        var service = new UserService(repository, backup);
        var premium = User.premium();
        when(repository.findAll())
                .thenReturn(List.of(premium, User.freemium()));

        service.backupPremiumUsers();

        verify(backup).create(premium.files());
    }
}
