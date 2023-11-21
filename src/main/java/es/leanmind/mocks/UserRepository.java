package es.leanmind.mocks;

import java.util.List;

public class UserRepository implements Repository {
    public void save(User user) {

    }

    @Override
    public List<User> findUsersByName(String name) {
        return null;
    }

    @Override
    public List<User> findUsersBySurname(String surname) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
