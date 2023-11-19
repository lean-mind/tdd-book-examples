package es.leanmind.matchers.fixtures;

import org.assertj.core.api.Assertions;

import java.util.List;

public class ListMatchers {
    private final List<Integer> actual;

    public ListMatchers(List<Integer> actual) {
        this.actual = actual;
    }

    public void isExactly(Number... expected) {
        Assertions.assertThat(expected)
                .hasSize(actual.size())
                .hasSameElementsAs(actual);
    }

    public static ListMatchers assertThatList(List<Integer> list) {
        return new ListMatchers(list);
    }
}