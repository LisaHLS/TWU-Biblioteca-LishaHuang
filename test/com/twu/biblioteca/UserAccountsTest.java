package com.twu.biblioteca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UserAccountsTest {
    private UserAccounts userAccounts;

    @Before
    public void setUp() {
        userAccounts = new UserAccounts();
    }

    @Test
    public void should_return_true_when_user_login_succeed() {
        assertTrue(userAccounts.login("110-1234", "123456"));
    }

    @Test
    public void should_return_false_when_libraryNumber_is_wrong() {
        assertFalse(userAccounts.login("1101234", "123456"));
    }

    @Test
    public void should_return_false_when_password_is_wrong() {
        assertFalse(userAccounts.login("110-1234", "1111111"));
    }

    @Test
    public void should_return_false_when_user_not_exist() {
        assertFalse(userAccounts.login("199-9876", "123456"));
    }

    @Test
    public void should_return_user_name_email_phone_when_user_login() {
        userAccounts.login("110-1234", "123456");
        assertEquals(userAccounts.showUserInformation(), " name: Jack, emailAddress: 123@163.com, phoneNumber: 15123476587");
    }
}
