/**
 *
 *
 *
 */
public class User {

    private String realName;
    private String username;
    private String password;
    private boolean isWorker;
    private boolean isManager;
    private boolean isAdmin;

    /**
     * Constructor for a User.
     * @param realName The real name of the User.
     * @param username The username of the User.
     * @param password The password of the User.
     */
    public User(String realName, String username, String password) {
        this.realName = realName;
        this.username = username;
        this.password = password;
        this.isWorker = false;
        this.isManager = false;
        this.isAdmin = false;
    }

    /**
     * Checker if the given credentials match this User's name and password.
     * @param username The proposed username.
     * @param password The matching proposed password.
     * @return Whether the login info is correct.
     */
    public boolean unlock(String username, String password) {
        return ((this.username.equals(username)) && (this.password.equals(password)));
    }

    /**
     * Setter for the User's name. Requires the current password to change it.
     * @param username The new name of the User.
     * @param password The current password of the User.
     */
    public boolean setUsername(String username, String password) {
        if (this.password.equals(password)) {
            this.username = username;
            return true;
        }
        return false;
    }

    /**
     * Setter for the User's password. Requires the old password.
     * @param oldPassword The current password of the User.
     * @param newPassword The new password for the User to use.
     */
    public boolean setPassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
        }
    }

    public boolean setAdmin(String password) {
        if (password.equals("Open Sesame!")) {
            this.isWorker = true;
            this.isManager = true;
            this.isAdmin = true;
            return true;
        }
        return false;
    }

    public boolean setManager(String password) {
        if (password.equals("Abra Kadabra!")) {
            this.isWorker = true;
            this.isManager = true;
            this.isAdmin = true;
            return true;
        }
        return false;
    }

    public boolean setAdmin(String password) {
        if (password.equals("Open Sesame!")) {
            this.isWorker = true;
            this.isManager = true;
            this.isAdmin = true;
            return true;
        }
        return false;
    }

    public String getRealName() {
        return this.realName;
    }

    public String getUsername(User user) {
        if (user.isAdmin()) {
            return this.username;
        }
        return null;
    }

    public String getPassword(User user) {
        if (user.isAdmin()) {
            return this.password;
        }
        return null;
    }

    public boolean isWorker() {
        return this.isWorker;
    }

    /**
     *
     * @return
     */
    public boolean isManager() {
        return this.isManager;
    }

    /**
     * Getter for whether the User is an Admin.
     * @return Whether the User is an Admin.
     */
    public boolean isAdmin() {
        return this.isAdmin;
    }
}