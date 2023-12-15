import org.example.diff_finder.Engine;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class EngineTest {

    public Set<String> list1 =
            Set.of("apple, banana, strawber.01, orange, grape, lemon, pear-01, watermelon, cherry");

    public Set<String> list2 =
            Set.of("ap-ple, grape, pear,lemon, strawber.ry, pineapple, kiwi, blueberry, raspberry, oran-01, cherry");

    @Test
    void findPartialMatchesTest() {
        assertThat(Engine.findPartialMatches(list1, list2).toString())
                .contains("strawberry")
                .contains("pear")
                .contains("orange");
    }

    @Test
    void isSimilarTest() {
        var answer1 = Engine.isSimilar("apple", "ap.ple");
        var answer2 = Engine.isSimilar("strawber.01", "strawber.ry");

        assertThat(answer1).isFalse();
        assertThat(answer2).isTrue();
    }
}