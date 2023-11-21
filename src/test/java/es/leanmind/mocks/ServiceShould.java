package es.leanmind.mocks;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

// Tests:
public class ServiceShould {
    @Test
    void save_user_through_repository() {
        Repository repository = mock(Repository.class);
        Service service = new Service(repository);
        User user = new User();

        service.updatePassword(user, new Password("1234"));

        verify(repository).save(user);
    }
}
