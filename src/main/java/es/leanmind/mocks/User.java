package es.leanmind.mocks;

import java.util.List;

public class User {

    private Boolean premium = false;

    public User(boolean isPremium) {
        this.premium = isPremium;
    }

    public static User premium() {
        return new User(true);
    }

    public static User freemium() {
        return new User(false);
    }

    public void update(Password password) {

    }

    public boolean isPremium() {
        return premium;
    }

    public List<String> files() {
        return List.of("file1", "file2");
    }
}
