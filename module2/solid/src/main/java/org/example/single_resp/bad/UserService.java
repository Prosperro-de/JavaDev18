package org.example.single_resp.bad;

public class UserService {
    public void updateUserNameAndNotify(String userName) {
        System.out.println("User name updated to: " + userName);

        System.out.println("User notified");
    }
}
