package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class UserAccounts {

    private List<User> userList ;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public UserAccounts() {
        userList = new ArrayList<User>(){{
            add(new User("110-1234","123456", "Jack", "123@163.com", "15123476587"));
            add(new User("110-4567","123456", "Rose", "456@163.com", "15123498765"));
            add(new User("120-5678","123456", "Belt", "789@163.com", "15134567587"));
            add(new User("120-1234","123456", "Shelly", "123456@163.com", "15189789587"));
            add(new User("119-1234","123456", "Sun", "12356@163.com", "15123423456"));
            add(new User("119-5678","123456", "Black", "12378@163.com", "15145676587"));
        }};
    }

    public boolean login(String libraryNumber, String passWord) {
        return false;
    }

    public String showUserInformation(String libraryNumber, String passWord) {
        return null;
    }

    private User getUserByLibraryNumberAndPassWord () {
        return null;
    }
}
