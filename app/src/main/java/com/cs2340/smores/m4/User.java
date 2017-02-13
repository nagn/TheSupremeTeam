/**
 *
 *
 *
 */
public class User {

    private String name;
    private String password;

    /**
     * Constructor for a User.
     * @param name The name of the User.
     * @param password The password of the User.
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * Checker if the given credentials match this User's name and password.
     * @param name The proposed username.
     * @param password The matching proposed password.
     * @return Whether the login info is correct.
     */
    public boolean unlock(String name, String password) {
        return ((this.name.equals(name)) && (this.password.equals(password)));
    }

    /**
     * Setter for the User's name. Requires the current password to change it.
     * @param name The new name of the User.
     * @param password The current password of the User.
     */
    public void setName(String name, String password) {
        if (this.password.equals(password)) {
            this.name = name;
        }
    }

    /**
     * Setter for the User's password. Requires the old password.
     * @param oldPassword The current password of the User.
     * @param newPassword The new password for the User to use.
     */
    public void setPassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
        }
    }
}