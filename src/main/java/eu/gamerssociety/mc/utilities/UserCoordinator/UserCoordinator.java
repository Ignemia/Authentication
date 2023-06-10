package eu.gamerssociety.mc.utilities.UserCoordinator;

import java.util.UUID;

public class UserCoordinator {
    private static UserCoordinator instance;
    // private DbUserManagement userManagement = DatabaseClient.Get().getUserManagement();

    private UserCoordinator() {
    }

    public boolean checkIfPlayerExists(UUID player) {
        return true;
    }

    public boolean checkIfPlayerVerified(UUID player) {
        return true;
    }

    public boolean userHasMFA(UUID player) {
        return true;
    }

    public boolean requestMFA(UUID player) {
        return true;
    }

    public void openRegistration() {
    }

    public void openSignIn() {
    }

    public void openVerification() {
    }

    public void kickPlayer(UUID player) {
    }

    public void allowLogin(UUID player) {
    }

    public void initiatePingpong(UUID player) {
    }

    public void welcomePlayer(UUID player) {
    }

    public static UserCoordinator Get() {
        if (instance == null) {
            instance = new UserCoordinator();
        }
        return instance;
    }
}
