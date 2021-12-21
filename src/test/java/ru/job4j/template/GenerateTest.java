package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GenerateTest {

    @Ignore
    @Test
    public void whenAllisNorm() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Dmitrii");
        map.put("subject", "you");
        Generate generate = new Generate();
        String expected = "I am a Dmitrii, Who are you? ";
        String result = generate.produce(template, map);
        assertThat(result, is(expected));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenNoArg() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Dmitrii");
        Generate generate = new Generate();
        String expected = "I am a Dmitrii, Who are you? ";
        String result = generate.produce(template, map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenMoreArgs() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Dmitrii");
        map.put("surname", "Zwei");
        map.put("subject", "you");
        Generate generate = new Generate();
        String expected = "I am a Dmitrii, Who are you? ";
        String result = generate.produce(template, map);
    }


}