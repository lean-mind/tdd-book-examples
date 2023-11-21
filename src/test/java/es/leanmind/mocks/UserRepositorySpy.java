package es.leanmind.mocks;

public class UserRepositorySpy implements Repository {
    public User savedUser;

    public void save(User user) {
        this.savedUser = user;
    }

    public User getSavedUser() {
        return savedUser;
    }
}
