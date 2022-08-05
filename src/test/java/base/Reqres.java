package base;

import base.apiCalls.user.User;

public class Reqres {

    private static final Reqres instance = new Reqres();

    public User user = new User();

    private Reqres() {
    }

    public static Reqres getInstance() {
        return instance;
    }
}
