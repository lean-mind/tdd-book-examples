package es.leanmind.mocks;

import java.util.List;

public class UserRepositorySpy implements Repository {
    public User savedUser;

    public void save(User user) {
        this.savedUser = user;
    }

    public List<User> findUsersByName(String name) {
        return null;
    }

    public List<User> findUsersBySurname(String surname) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    public User getSavedUser() {
        return savedUser;
    }
}
