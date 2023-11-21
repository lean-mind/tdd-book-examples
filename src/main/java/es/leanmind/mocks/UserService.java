package es.leanmind.mocks;

import java.util.List;

public class UserService {
    private final Repository repository;

    public UserService(Repository repository) {
        this.repository = repository;
    }

    public void updatePassword(User user, Password password) {
        user.update(password);
        repository.save(user);
    }

    public List<User> findUsers(String name) {
        var usersByName = repository.findUsersByName(name);
        if (usersByName != null && !usersByName.isEmpty()) {
            return usersByName;
        } else {
            var usersBySurname = repository.findUsersBySurname(name);
            if (usersBySurname != null && !usersBySurname.isEmpty()) {
                return usersBySurname;
            }
        }
        return List.of();
    }
}
