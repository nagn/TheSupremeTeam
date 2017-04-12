package com.cs2340.smores.m4;

import com.cs2340.smores.m4.model.Model;
import com.cs2340.smores.m4.model.User;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import static com.cs2340.smores.m4.model.Model.checkLogin;
import static com.cs2340.smores.m4.model.Model.getUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * All JUnits tests focus on methods from the Model.java class.
 * They have been compiled into one document.
 *
 * @author Team 48
 */
public class JUnits {

    private static final int TIMEOUT = 2000;
    private User[] sampleUsers;

    private void initSampleUsers() {
        sampleUsers = new User[7];
        sampleUsers[0] = new User("1", "u1", "wow", "N/A", "N/A", "N/A", 3);
        sampleUsers[1] = new User("2", "u2", "wow", "N/A", "N/A", "N/A", 2);
        sampleUsers[2] = new User("3", "u3", "wow", "N/A", "N/A", "N/A", 1);
        sampleUsers[3] = new User("4", "u4", "wow", "N/A", "N/A", "N/A", 0);
        sampleUsers[4] = new User("5", "u5", "wow",
                "N/A", "N/A", "N/A", 0);
        sampleUsers[5] = new User("6", "!*?", "wow", "N/A", "N/A", "N/A", 3);
    }

    @Before
    public void init() {
        initSampleUsers();
        Model.user = null;
        Model.users = new ArrayList<>();
        Model.log = new ArrayList<>();
    }

    // Sam Mohr's test of the checkLogin() method:
    // public static User checkLogin(String username, String password) {
    //     String logMessage = "Login -- Time of Login Attempt: "
    //             + DateFormat.getDateTimeInstance()
    //             .format(new Date()) + ", User: " + username + ", Status: ";
    //     for (int i = 0; i < users.size(); i++) {
    //         if (users.get(i).unlock(username, password)) {
    //             addLog(logMessage + "Success");
    //             return users.get(i);
    //         }
    //     }
    //     addLog(logMessage + ((exists(username))
    //             ? "Bad Password" : "Unknown ID"));
    //     return null;
    // }
    // "User: " + [userX + ", Status: " + (Success, Bad Password, Unknown ID)];
    @Test(timeout = TIMEOUT)
    public void testCheckLogin() {
        String logMessage;
        User u;

        Model.users.addAll(Arrays.asList(sampleUsers).subList(0, 4));

        for (int j = 0; j < 4; j++) {
            u = checkLogin("u" + (j + 1), "wow");
            logMessage = Model.log.get(Model.log.size() - 1);
            logMessage = logMessage.substring(logMessage
                    .length() - 19, logMessage.length());
            assertEquals(u, sampleUsers[j]);
            assertEquals(logMessage, "u" + (j + 1) + ", Status: Success");
        }

        u = checkLogin("u1", "wrong");
        logMessage = Model.log.get(Model.log.size() - 1);
        logMessage = logMessage.substring(logMessage
                .length() - 24, logMessage.length());
        assertEquals(u, null);
        assertEquals(logMessage, "u1, Status: Bad Password");

        u = checkLogin("badID", "who cares");
        logMessage = Model.log.get(Model.log.size() - 1);
        logMessage = logMessage.substring(logMessage
                .length() - 25, logMessage.length());
        assertEquals(u, null);
        assertEquals(logMessage, "badID, Status: Unknown ID");
    }

    // Mayuri Mamtani's tests of the exists() method:
    // public static boolean exists(String username) {
    //     for (User u : users) {
    //         if (u.getUsername().equals(username)) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }
    @Test(timeout = TIMEOUT)
    public void testExists() {
        Model.users.add(sampleUsers[0]);
        assertTrue(Model.exists("u1"));

        assertFalse(Model.exists("u99"));

        Model.users.add(sampleUsers[3]);
        User mayuri = new User("Mayuri", "u4", "password", "???", "?", "?", 3);
        Model.users.add(mayuri);
        assertTrue(Model.exists("u4"));
    }

    // Kenan's tests of the removeUser() method:
    // public static void removeUser(User user, User admin) {
    //     if ((user != null) && (admin != null) && (admin.getType() >= 3)) {
    //         addLog("Account Deletion -- Time of Removal: "
    //                 + DateFormat.getDateTimeInstance().format(new Date())
    //                 + ", Admin: " + admin.getUsername()
    //                 + ", Deleted User: " + user.getUsername());
    //         users.remove(user);
    //         userDBHandler.removeUser(user);
    //     }
    // }
    @Test(timeout = TIMEOUT)
    public void testRemoveUser() {
        assertEquals(Model.log.size(), 0);

        Model.users.add(sampleUsers[0]);
        Model.users.add(sampleUsers[2]);
        Model.users.add(sampleUsers[3]);

        assertEquals(Model.log.size(), 0);

        Model.removeUser(sampleUsers[3], sampleUsers[0]);
        assertEquals(Model.log.size(), 1);

        Model.removeUser(sampleUsers[0], null);
        assertEquals(Model.log.size(), 1);

        Model.removeUser(null, sampleUsers[0]);
        assertEquals(Model.log.size(), 1);

        Model.removeUser(sampleUsers[0], sampleUsers[2]);
        assertEquals(Model.log.size(), 1);

        Model.removeUser(sampleUsers[0], sampleUsers[0]);
        assertEquals(Model.log.size(), 2);
    }

    // Nikki Cantrell's tests of the getUser() method:
    // public static User getUser(String username) {
    //     for (User user : users) {
    //         if (user.getUsername().equals(username)) {
    //             return user;
    //         }
    //     }
    //     return null;
    // }
    @Test(timeout = TIMEOUT)
    public void testGetUser() {
        assertEquals(null, getUser("u1"));

        Model.users.add(sampleUsers[0]);
        assertEquals(sampleUsers[0], getUser("u1"));
        assertEquals(null, getUser("unregistered"));

        Model.users.add(sampleUsers[1]);
        User newUser = new User("new", "u2", "wow", "x", "x", "x", 3);
        assertEquals(newUser, getUser("u2"));
    }

    // Yi Li's tests of the isValid() method:
    // public static boolean isValid(String username) {
    //     for (int i = 0; i < username.length() - 1; i++) {
    //         if (username.substring(i, i + 1).matches("[^A-Za-z0-9]")) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }
    @Test(timeout = TIMEOUT)
    public void testIsValid() {
        String goodUsername = "YiLi01";
        assertTrue(Model.isValid(goodUsername));

        String[] badUsernames = new String[]{"Yi Li", "!YiLi01",
            "Yi-Li", "YiLi.", ".YiLi"};
        for (int i=0; i<badUsernames.length; ++i) {
            assertFalse(badUsernames[i], Model.isValid(badUsernames[i]));
        }
    }
}
