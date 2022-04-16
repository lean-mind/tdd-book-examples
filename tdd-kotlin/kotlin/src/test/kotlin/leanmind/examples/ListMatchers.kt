package leanmind.examples

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.AssertionError

fun assertThatList(list: List<Number>): ListMatchers {
    return ListMatchers(list)
}

class ListMatchers(private val givenList: List<Number>) {

    fun isExactly(vararg numbers: Number) {
        assertThat(numbers).hasSize(givenList.size).hasSameElementsAs(givenList)
    }
}

class ListMatchersShould {

    @Test
    fun compare_same_elements_in_both_lists() {
        assertThatList(listOf(1, 2)).isExactly(1, 2)

        assertThrows<AssertionError> {
            assertThatList(listOf(1, 2)).isExactly(1, 3)
        }
    }

}

