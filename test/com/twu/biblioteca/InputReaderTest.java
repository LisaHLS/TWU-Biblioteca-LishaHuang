package com.twu.biblioteca;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class InputReaderTest {

    private InputReader reader;

    @Before
    public void setUp(){
        reader = new InputReader();
    }

    private void setInputStream(String input) throws NoSuchFieldException, IllegalAccessException {

        Field scannerField = reader.getClass().getDeclaredField("scanner");
        Scanner scannerWithMockStream = new Scanner(new ByteArrayInputStream(input.getBytes()));
        scannerField.setAccessible(true);
        scannerField.set(reader, scannerWithMockStream);
    }

    @Test
    public void should_return_input_option_itself_1_when_input_is_digit_and_in_options () throws NoSuchFieldException, IllegalAccessException {
        setInputStream("1");
        assertThat(reader.readOption()).isEqualTo("1");
    }

    @Test
    public void should_return_input_option__itself_4_when_input_is_digit_and_in_options () throws NoSuchFieldException, IllegalAccessException {
        setInputStream("4");
        assertThat(reader.readOption()).isEqualTo("4");
    }

    @Test
    public void should_return_select_a_valid_option_when_input_5_is_not_in_options () throws NoSuchFieldException, IllegalAccessException {
        setInputStream("5");
        assertThat(reader.readOption()).isEqualTo("Select a valid option!");
    }

    @Test
    public void should_return_select_a_valid_option_when_input_is_not_digit () throws NoSuchFieldException, IllegalAccessException {
        setInputStream("a");
        assertThat(reader.readOption()).isEqualTo("Select a valid option!");
    }

    @Test
    public void should_return_book_info_when_input_has_all_book_info_and_order_is_name_author_publishedYear () throws NoSuchFieldException, IllegalAccessException {
        setInputStream("<Head First Java>,Kent Belt,2003");
        assertThat(reader.readBook()).isEqualTo("<Head First Java>,Kent Belt,2003");
    }

    @Test
    public void should_return_that_book_info_is_invalid_when_input_not_has_name() throws NoSuchFieldException, IllegalAccessException {
        setInputStream("Kent Belt,2003");
        assertThat(reader.readBook()).isEqualTo("That book information is invalid");
    }

    @Test
    public void should_return_that_book_info_is_invalid_when_input_not_has_author() throws NoSuchFieldException, IllegalAccessException {
        setInputStream("<Head First Java>,2003");
        assertThat(reader.readBook()).isEqualTo("That book information is invalid");
    }

    @Test
    public void should_return_that_book_info_is_invalid_when_input_not_has_publishedYear() throws NoSuchFieldException, IllegalAccessException {
        setInputStream("<Head First Java>,Kent Belt");
        assertThat(reader.readBook()).isEqualTo("That book information is invalid");
    }

    @Test
    public void should_return_that_book_info_is_invalid_when_input_order_not_as_name_author_publishedYear() throws NoSuchFieldException, IllegalAccessException {
        setInputStream("<Head First Java>,2003,Kent Belt");
        assertThat(reader.readBook()).isEqualTo("That book information is invalid");
    }

    @Test
    public void should_return_that_book_info_is_invalid_when_publishedYear_is_not_digit() throws NoSuchFieldException, IllegalAccessException {
        setInputStream("<Head First Java>,Kent Belt,jjj");
        assertThat(reader.readBook()).isEqualTo("That book information is invalid");
    }

    @Test
    public void should_return_input_itself_when_libraryNumber_and_password_is_valid() throws NoSuchFieldException, IllegalAccessException {
        setInputStream("110-1234,123456");
        assertThat(reader.readLibraryNumberAndPassword()).isEqualTo("110-1234,123456");
    }

    @Test
    public void should_return_prompt_msg_when_libraryNumber_and_password_is_invalid() throws NoSuchFieldException, IllegalAccessException {
        setInputStream("1101234,123456");
        assertThat(reader.readLibraryNumberAndPassword()).isEqualTo("That libraryNumber or password is invalid");
    }

}
