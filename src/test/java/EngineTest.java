import org.example.diff_finder.Engine;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EngineTest {

    public List<String> list1 =
            List.of("apple, banana, strawber-01, orange, grape, lemon, pear-01, watermelon, cherry");

    public List<String> list2 =
            List.of("ap-ple, grape, pear,lemon, strawberry, pineapple, kiwi, blueberry, raspberry, oran-01, cherry");

    @Test
    void findPartialMatchesTest() {
        assertThat(Engine.findPartialMatches(list1, list2).toString())
                .contains("strawberry")
                .contains("pear")
                .contains("orange");
    }

    @Test
    void isSimilarTest() {
        var answer1 = Engine.isSimilar(list1.get(0), list2.get(0));
        var answer2 = Engine.isSimilar(list1.get(2), list2.get(4));

        assertThat(answer1).isFalse();
        assertThat(answer2).isTrue();
    }
}