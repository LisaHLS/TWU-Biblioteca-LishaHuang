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


}
