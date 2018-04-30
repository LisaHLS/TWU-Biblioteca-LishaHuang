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
        when(reader.readOption()).thenReturn("4");
        menu.init();
        assertThat(systemOut().startsWith("Welcome to Biblioteca!")).isTrue();
        verify(reader, times(1)).readOption();
    }

    @Test
    public void should_return_true_and_prompt_msg_when_input_not_in_options() {
        when(reader.readOption()).thenReturn("5");
        assertTrue(menu.processingBusinessAccordingToOption());
        assertThat(systemOut().endsWith("Select a valid option! Please select again.\n")).isTrue();
    }

    @Test
    public void should_return_true_and_prompt_msg_when_input_not_digit() {
        when(reader.readOption()).thenReturn("jjj");
        assertTrue(menu.processingBusinessAccordingToOption());
        assertThat(systemOut().endsWith("Select a valid option! Please select again.\n")).isTrue();
    }

    @Test
    public void should_print_book_list_when_choose_LIST_BOOKS() {
        when(reader.readOption()).thenReturn("1").thenReturn("4");
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
        verify(reader, times(2)).readOption();
    }

    @Test
    public void should_not_show_checked_out_books_after_CHECK_OUT() {
        when(reader.readBook()).thenReturn("<Head First Java>,Kent Belt,2003");
        menu.checkOut();
        menu.printBooksList();
        String line = "=========================================================================================\n";
        String bookListInfo = String.format("%-25s%-35s%-30s\n" + line,"Name","Author","PublishedYear")
            + String.format("%-40s%-40s%-40s\n","Test-Driven Development","Kent Belt",2004)
            + String.format("%-40s%-40s%-40s\n","Refactoring: Improving the Design","Martin Fowler",2010)
            + String.format("%-40s%-40s%-40s\n","Head First Servlets & JSP","O'Reilly",2010)
            + String.format("%-40s%-40s%-40s\n","Thinking in Java","Bruce Eckel ",2006)
            + String.format("%-40s%-40s%-40s\n","Effective Java","Joshua Bloch",2009);
        assertThat(systemOut()).contains(bookListInfo);
        assertThat(systemOut()).contains("Thank you! Enjoy the book.\n");
    }

    @Test
    public void should_prompt_msg_when_check_out_fail() {
        when(reader.readBook()).thenReturn("<Head First Java>,Kent Belt,2003");
        menu.checkOut();
        when(reader.readBook()).thenReturn("<Head First Java>,Kent Belt,2003").thenReturn("<Test-Driven Development>,Kent Belt,2004");
        menu.checkOut();
        assertThat(systemOut()).contains("That book is not available.\n");
        verify(reader, times(3)).readBook();
    }

    @Test
    public void should_show_return_books_and_prompt_msg_when_return_book_success() {
        when(reader.readBook()).thenReturn("<Head First Java>,Kent Belt,2003");
        menu.checkOut();
        menu.returnBook();
        menu.printBooksList();
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
    }

    @Test
    public void should_prompt_msg_when_return_book_fail() {
        when(reader.readBook()).thenReturn("<Head First Java>,Kent Belt,2003");
        menu.returnBook();
        assertThat(systemOut()).contains("That is not a valid book to return.\n");
    }

    @Test
    public void should_return_false_and_prompt_quit_msg_when_choose_QUIT() {
        when(reader.readOption()).thenReturn("4");
        assertFalse(menu.processingBusinessAccordingToOption());
        menu.init();
        assertThat(systemOut().endsWith("Goodbye! welcome to the next time!\n")).isTrue();
    }

}
