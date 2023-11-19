package es.leanmind.matchers;

import org.junit.jupiter.api.Test;

import java.util.List;

import static es.leanmind.matchers.fixtures.ListMatchers.assertThatList;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ListMatchersShould {
    @Test
    void be_the_same() {
        List<Integer> list = List.of(1, 2, 3);
        assertThatList(list).isExactly(1, 2, 3);
    }

    @Test
    void be_different() {
        List<Integer> list = List.of(1, 2, 3);

        assertThrows(AssertionError.class, () -> {
            assertThatList(list).isExactly(1, 2, 4);
        });
    }
}