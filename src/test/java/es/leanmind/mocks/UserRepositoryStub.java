package es.leanmind.mocks;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryStub implements Repository {
    public List<User> stubListOfUsersByName = new ArrayList<>();
    public List<User> stubListOfUsersBySurname = new ArrayList<>();

    public List<User> findUsersByName(String name) {
        return stubListOfUsersByName;
    }

    public List<User> findUsersBySurname(String name) {
        return stubListOfUsersBySurname;
    }

    public void save(User user) {
        // Do nothing
    }
}
