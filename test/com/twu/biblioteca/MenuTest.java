package com.twu.biblioteca;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;

public class MenuTest {

    private Menu menu;
    private InputReader reader;
    private ByteArrayOutputStream outputContent;
    private String systemOut() { return outputContent.toString();}

    @Before
    public void setUp() {
        reader = mock(InputReader.class);
        menu = new Menu(reader);
        outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
    }

    @Test
    public void should_print_welcome_message_when_start_Biblioteca_app() {
        when(reader.readChooseUserOrLibrarian()).thenReturn("3");
        menu.init();
        assertThat(systemOut().startsWith("Welcome to Biblioteca!")).isTrue();
        verify(reader, times(1)).readChooseUserOrLibrarian();
    }

    @Test
    public void should_return_true_and_prompt_msg_when_input_not_in_options() {

        when(reader.readLibrarianOption()).thenReturn("5");
        assertTrue(menu.librarianProcessAccordingToOption());
        assertThat(systemOut().endsWith("Select a valid option! Please select again.\n")).isTrue();

        when(reader.readUserOption()).thenReturn("9");
        assertTrue(menu.userProcessAccordingToOption());
        assertThat(systemOut().endsWith("Select a valid option! Please select again.\n")).isTrue();
    }

    @Test
    public void should_return_true_when_user_login_succeed() {
        when(reader.readChooseUserOrLibrarian()).thenReturn("1");
        when(reader.readLibraryNumberAndPassword()).thenReturn("110-1234,123456");
        when(reader.readUserOption()).thenReturn("8");
        menu.init();
        assertTrue(menu.userLogin());
    }

    @Test
    public void should_return_false_when_user_login_fail() {
        when(reader.readChooseUserOrLibrarian()).thenReturn("1");
        when(reader.readLibraryNumberAndPassword()).thenReturn("110-1234,1234567");
        assertFalse(menu.userLogin());
    }

    @Test
    public void should_print_book_list_when_choose_LIST_BOOKS() {
        when(reader.readChooseUserOrLibrarian()).thenReturn("1");
        when(reader.readLibraryNumberAndPassword()).thenReturn("110-1234,123456");
        when(reader.readUserOption()).thenReturn("1").thenReturn("8");
        menu.init();
        String line = "=========================================================================================\n";
        String bookListInfo = String.format("%-25s%-35s%-30s\n" + line,"Name","Author","PublishedYear")
            + String.format("%-40s%-40s%-40s\n","Head First Java","Kent Belt",2003)
            + String.format("%-40s%-40s%-40s\n","Test-Driven Development","Kent Belt",2004)
            + String.format("%-40s%-40s%-40s\n","Refactoring: Improving the Design","Martin Fowler",2010)
            + String.format("%-40s%-40s%-40s\n","Head First Servlets & JSP","O'Reilly",2010)
            + String.format("%-40s%-40s%-40s\n","Thinking in Java","Bruce Eckel ",2006)
            + String.format("%-40s%-40s%-40s\n","Effective Java","Joshua Bloch",2009);
        assertThat(systemOut()).contains(bookListInfo);
        verify(reader, times(2)).readUserOption();
    }

    @Test
    public void should_print_movie_list_when_choose_LIST_MOVIES() {
        when(reader.readChooseUserOrLibrarian()).thenReturn("1");
        when(reader.readLibraryNumberAndPassword()).thenReturn("110-1234,123456");
        when(reader.readUserOption()).thenReturn("2").thenReturn("8");
        menu.init();
        String line = "=========================================================================================\n";
        String movieListInfo = String.format("%-30s%-30s%-20s%-30s\n" + line,"Name","Year","Director","MovieRating")
            + String.format("%-30s%-30s%-25s%-30s\n","The Great Buddha", 2017, "Xinyao Huang", "9")
            + String.format("%-30s%-30s%-25s%-30s\n","Wonder", 2017, "Stephen Jobo", "9")
            + String.format("%-30s%-30s%-25s%-30s\n","The Post", 2017, "Steven Spielberg", "8")
            + String.format("%-30s%-30s%-25s%-30s\n","Lady Bird", 2017, "Greta Geerwig", "8")
            + String.format("%-30s%-30s%-25s%-30s\n","The Faces of My Gene", 2018, "Degang Guo", "4");
        assertThat(systemOut()).contains(movieListInfo);
        verify(reader, times(2)).readUserOption();
    }

    @Test
    public void should_not_show_checked_out_books_after_CHECK_OUT() {
        when(reader.readBook()).thenReturn("<Head First Java>,Kent Belt,2003");
        when(reader.readLibraryNumberAndPassword()).thenReturn("110-1234,123456");
        menu.userLogin();
        menu.checkOutBook();
        menu.printBooksList();
        menu.showCheckOutBookRecord();
        String line = "=========================================================================================\n";
        String bookListInfo = String.format("%-25s%-35s%-30s\n" + line,"Name","Author","PublishedYear")
            + String.format("%-40s%-40s%-40s\n","Test-Driven Development","Kent Belt",2004)
            + String.format("%-40s%-40s%-40s\n","Refactoring: Improving the Design","Martin Fowler",2010)
            + String.format("%-40s%-40s%-40s\n","Head First Servlets & JSP","O'Reilly",2010)
            + String.format("%-40s%-40s%-40s\n","Thinking in Java","Bruce Eckel ",2006)
            + String.format("%-40s%-40s%-40s\n","Effective Java","Joshua Bloch",2009);
        assertThat(systemOut()).contains(bookListInfo);
        assertThat(systemOut()).contains("Thank you! Enjoy the book.\n");
        assertThat(systemOut()).contains("book: Head First Java, user: 110-1234");
    }

    @Test
    public void should_not_show_checked_out_movies_after_CHECK_OUT_MOVIE() {
        when(reader.readMovie()).thenReturn("<The Great Buddha>,2017,Xinyao Huang");
        menu.checkOutMovie();
        menu.printMoviesList();
        String line = "=========================================================================================\n";
        String movieListInfo = String.format("%-30s%-30s%-20s%-30s\n" + line,"Name","Year","Director","MovieRating")
            + String.format("%-30s%-30s%-25s%-30s\n","Wonder", 2017, "Stephen Jobo", "9")
            + String.format("%-30s%-30s%-25s%-30s\n","The Post", 2017, "Steven Spielberg", "8")
            + String.format("%-30s%-30s%-25s%-30s\n","Lady Bird", 2017, "Greta Geerwig", "8")
            + String.format("%-30s%-30s%-25s%-30s\n","The Faces of My Gene", 2018, "Degang Guo", "4");
        assertThat(systemOut()).contains(movieListInfo);
        assertThat(systemOut()).contains("Thank you! Enjoy the movie.\n");
    }

    @Test
    public void should_prompt_msg_when_check_out_movie_fail() {
        when(reader.readMovie()).thenReturn("<The Great Buddha>,2017,Xinyao Huang");
        menu.checkOutMovie();
        when(reader.readMovie()).thenReturn("<The Great Buddha>,2017,Xinyao Huang").thenReturn("<Wonder>,2017,Stephen Jobo");
        menu.checkOutMovie();
        assertThat(systemOut()).contains("That movie is not available.\n");
        verify(reader, times(3)).readMovie();
    }

    @Test
    public void should_show_user_info_when_USER_INFORMATION() {
        when(reader.readChooseUserOrLibrarian()).thenReturn("1");
        when(reader.readLibraryNumberAndPassword()).thenReturn("110-1234,123456");
        when(reader.readUserOption()).thenReturn("6").thenReturn("8");
        menu.init();
        assertThat(systemOut()).contains("name: Jack, emailAddress: 123@163.com, phoneNumber: 15123476587\n");
    }

    @Test
    public void should_prompt_msg_when_check_out_book_fail() {
        when(reader.readBook()).thenReturn("<Head First Java>,Kent Belt,2003");
        menu.checkOutBook();
        when(reader.readBook()).thenReturn("<Head First Java>,Kent Belt,2003").thenReturn("<Test-Driven Development>,Kent Belt,2004");
        menu.checkOutBook();
        assertThat(systemOut()).contains("That book is not available.\n");
        verify(reader, times(3)).readBook();
    }

    @Test
    public void should_show_return_books_and_prompt_msg_when_return_book_success() {
        when(reader.readBook()).thenReturn("<Head First Java>,Kent Belt,2003");
        when(reader.readLibraryNumberAndPassword()).thenReturn("110-1234,123456");
        menu.userLogin();
        menu.checkOutBook();
        menu.returnBook();
        menu.printBooksList();
        menu.showCheckOutBookRecord();
        String line = "=========================================================================================\n";
        String bookListInfo = String.format("%-25s%-35s%-30s\n" + line,"Name","Author","PublishedYear")
            + String.format("%-40s%-40s%-40s\n","Test-Driven Development","Kent Belt",2004)
            + String.format("%-40s%-40s%-40s\n","Refactoring: Improving the Design","Martin Fowler",2010)
            + String.format("%-40s%-40s%-40s\n","Head First Servlets & JSP","O'Reilly",2010)
            + String.format("%-40s%-40s%-40s\n","Thinking in Java","Bruce Eckel ",2006)
            + String.format("%-40s%-40s%-40s\n","Effective Java","Joshua Bloch",2009)
            + String.format("%-40s%-40s%-40s\n","Head First Java","Kent Belt",2003);
        assertThat(systemOut()).contains(bookListInfo);
        assertThat(systemOut()).contains("Thank you for returning the book.\n");
        assertFalse(systemOut().contains("book: Head First Java, user: 110-1234\n"));
    }

    @Test
    public void should_prompt_msg_when_return_book_fail() {
        when(reader.readBook()).thenReturn("<Head First Java>,Kent Belt,2003");
        menu.returnBook();
        assertThat(systemOut()).contains("That is not a valid book to return.\n");
    }

    @Test
    public void should_return_false_and_prompt_quit_msg_when_choose_QUIT() {
        when(reader.readChooseUserOrLibrarian()).thenReturn("3");
        assertFalse(menu.userOrLibrarianLogin());
        menu.init();
        assertThat(systemOut().endsWith("Goodbye! welcome to the next time!\n")).isTrue();

        when(reader.readChooseUserOrLibrarian()).thenReturn("1");
        when(reader.readLibraryNumberAndPassword()).thenReturn("110-1234,123456");
        when(reader.readUserOption()).thenReturn("8");
        assertFalse(menu.userProcessAccordingToOption());
        menu.init();
        assertThat(systemOut().endsWith("Goodbye! welcome to the next time!\n")).isTrue();

        when(reader.readChooseUserOrLibrarian()).thenReturn("2");
        when(reader.readLibrarianOption()).thenReturn("3");
        assertFalse(menu.librarianProcessAccordingToOption());
        menu.init();
        assertThat(systemOut().endsWith("Goodbye! welcome to the next time!\n")).isTrue();
    }

}
