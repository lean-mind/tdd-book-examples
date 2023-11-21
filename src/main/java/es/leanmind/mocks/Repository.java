package es.leanmind.mocks;

import java.util.List;

public interface Repository {
    void save(User user);

    List<User> findUsersByName(String name);

    List<User> findUsersBySurname(String surname);

    List<User> findAll();
}
