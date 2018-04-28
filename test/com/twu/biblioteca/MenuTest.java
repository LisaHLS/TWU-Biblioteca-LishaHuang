package com.twu.biblioteca;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

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
        menu.printWelcomeMsg();
        assertThat(systemOut().startsWith("Welcome to Biblioteca!")).isTrue();
    }

    @Test
    public void should_print_all_options_after_welcome_msg() {
        menu.printAllOptions();
        assertThat(systemOut().endsWith("1. List Books\n2. Checkout Book\n3. Return Book\n4. Quit\nPlease enter your choice(1～4):\n")).isTrue();
    }


}
