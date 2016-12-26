package net.btnz.pri.java.myjavabasetest.keyword.volatilekey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangsongwei on 2016/12/26.
 */

/**
 * 该模式是前面模式的扩展；将某个值发布以在程序内的其他地方使用，但是与一次性事件的发布不同，这是一系列独立事件。这个模式要求被发布的值是有效不可变的 —— 即值的状态在发布后不会更改。使用该值的代码需要清楚该值可能随时发生变化。
 */
public class IndependentObservation {
    public volatile String lastUser;
    List<User> activeUsers = new ArrayList<>();

    public boolean authenticate(String user, String passwd) {
        boolean valid = passwordIsValid(user, passwd);
        if (valid) {
            User u = new User();
            activeUsers.add(u);
            lastUser = user;
        }
        return valid;
    }

    public boolean passwordIsValid(String user, String password) {
        return true;
    }

    class User {

    }
}
