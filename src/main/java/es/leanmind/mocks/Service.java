package es.leanmind.mocks;

// Production code:
public class Service {
    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public void updatePassword(User user, Password password) {
        user.update(password);
        repository.save(user);
    }
}
