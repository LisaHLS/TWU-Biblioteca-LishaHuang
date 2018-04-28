package com.twu.biblioteca;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        assertThat(systemOut().endsWith("1. List Books\n2. Check Out\n3. Return Book\n4. Quit\nPlease enter your choice(1～4):\n")).isTrue();
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

    public void should_return_false_and_prompt_quit_msg_when_choose_Quit() {
        when(reader.readOption()).thenReturn("4");
        assertFalse(menu.processingBusinessAccordingToOption());
        menu.init();
        assertThat(systemOut().endsWith("Goodbye! welcome to the next time!\n")).isTrue();
    }

}
