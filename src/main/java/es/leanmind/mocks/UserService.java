package es.leanmind.mocks;

import java.util.List;

public class UserService {
    private final Repository repository;
    private final BackupService backup;

    public UserService(Repository repository, BackupService backup) {
        this.repository = repository;
        this.backup = backup;
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

    public void backupPremiumUsers() {
        for (User user : repository.findAll()) {
            if (user.isPremium()) {
                backup.create(user.files());
            }
        }
    }
}
