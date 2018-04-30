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
    public void should_return_input_itself_when_user_option_is_digit_and_in_options_else_return_prompt_msg () throws NoSuchFieldException, IllegalAccessException {
        setInputStream("1");
        assertThat(reader.readUserOption()).isEqualTo("1");
        setInputStream("7");
        assertThat(reader.readUserOption()).isEqualTo("7");
        setInputStream("8");
        assertThat(reader.readUserOption()).isEqualTo("Select a valid option!");
        setInputStream("a");
        assertThat(reader.readUserOption()).isEqualTo("Select a valid option!");
    }

    @Test
    public void should_return_input_itself_when_input_has_all_book_info_and_order_is_name_author_publishedYear_else_return_prompt_msg () throws NoSuchFieldException, IllegalAccessException {
        setInputStream("<Head First Java>,Kent Belt,2003");
        assertThat(reader.readBook()).isEqualTo("<Head First Java>,Kent Belt,2003");
        setInputStream("Kent Belt,2003");
        assertThat(reader.readBook()).isEqualTo("That book information is invalid");
        setInputStream("<Head First Java>,2003");
        assertThat(reader.readBook()).isEqualTo("That book information is invalid");
        setInputStream("<Head First Java>,Kent Belt");
        assertThat(reader.readBook()).isEqualTo("That book information is invalid");
        setInputStream("<Head First Java>,2003,Kent Belt");
        assertThat(reader.readBook()).isEqualTo("That book information is invalid");
        setInputStream("<Head First Java>,Kent Belt,jjj");
        assertThat(reader.readBook()).isEqualTo("That book information is invalid");
    }

    @Test
    public void should_return_input_itself_when_libraryNumber_and_password_is_valid_else_return_prompt_msg() throws NoSuchFieldException, IllegalAccessException {
        setInputStream("110-1234,123456");
        assertThat(reader.readLibraryNumberAndPassword()).isEqualTo("110-1234,123456");
        setInputStream("1101234,123456");
        assertThat(reader.readLibraryNumberAndPassword()).isEqualTo("That libraryNumber or password is invalid");
    }

    @Test
    public void should_return_itself_when_choose_is_in_1_to_3_and_else_return_prompt_msg() throws NoSuchFieldException, IllegalAccessException {
        setInputStream("1");
        assertThat(reader.readChooseUserOrLibrarian()).isEqualTo("1");
        setInputStream("3");
        assertThat(reader.readChooseUserOrLibrarian()).isEqualTo("3");
        setInputStream("4");
        assertThat(reader.readChooseUserOrLibrarian()).isEqualTo("That choose is invalid");
    }

    @Test
    public void should_return_input_itself_when_movie_has_name_year_director_and_order_is_right_else_return_prompt_msg () throws NoSuchFieldException, IllegalAccessException {
        setInputStream("<The Post>,2017,Steven Spielberg");
        assertThat(reader.readMovie()).isEqualTo("<The Post>,2017,Steven Spielberg");
        setInputStream("2017,Steven Spielberg");
        assertThat(reader.readMovie()).isEqualTo("That movie information is invalid");
        setInputStream("<The Post>,2017");
        assertThat(reader.readMovie()).isEqualTo("That movie information is invalid");
        setInputStream("<The Post>,Steven Spielberg");
        assertThat(reader.readMovie()).isEqualTo("That movie information is invalid");
        setInputStream("<The Post>,Steven Spielberg,2017");
        assertThat(reader.readMovie()).isEqualTo("That movie information is invalid");
        setInputStream("<The Post>,kkk,Steven Spielberg");
        assertThat(reader.readMovie()).isEqualTo("That movie information is invalid");
    }

    @Test
    public void should_return_input_itself_when_librarian_option_is_digit_and_in_options_else_return_prompt_msg () throws NoSuchFieldException, IllegalAccessException {
        setInputStream("1");
        assertThat(reader.readLibrarianOption()).isEqualTo("1");
        setInputStream("3");
        assertThat(reader.readLibrarianOption()).isEqualTo("3");
        setInputStream("4");
        assertThat(reader.readLibrarianOption()).isEqualTo("Select a valid option!");
        setInputStream("a");
        assertThat(reader.readLibrarianOption()).isEqualTo("Select a valid option!");
    }

}
