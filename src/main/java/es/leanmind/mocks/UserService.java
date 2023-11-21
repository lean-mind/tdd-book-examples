package es.leanmind.mocks;

public class UserService {
    private final Repository repository;

    public UserService(Repository repository) {
        this.repository = repository;
    }

    public void updatePassword(User user, Password password) {
        user.update(password);
        repository.save(user);
    }
}
