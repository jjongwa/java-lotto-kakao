package lotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
class LottoBallsTest {

    @Test
    void 올바르게_로또_그룹을_생성할_수_있다() {
        assertDoesNotThrow(() -> new LottoBalls(List.of(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void 로또_그룹에_6개_이외의_로또_볼이_존재할_시_예외_처리한다() {
        // given
        final List<Integer> numbers = List.of(1, 2);

        // when & then
        assertThatThrownBy(() -> new LottoBalls(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 볼은 6개가 존재해야 합니다.");
    }

    @Test
    void 중복된_로또_볼이_존재할_시_예외_처리한다() {
        // given
        final List<Integer> numbers = List.of(1, 2, 7, 7, 23, 45);

        // when & then
        assertThatThrownBy(() -> new LottoBalls(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 그룹에서 볼은 중복될 수 없습니다.");
    }

    @Test
    void 다른_로또_그룹과_비교해_일치하는_볼의_개수를_반환할_수_있다() {
        // given
        LottoBalls aGroup = new LottoBalls(List.of(1, 2, 7, 8, 23, 45));
        LottoBalls bGroup = new LottoBalls(List.of(1, 3, 7, 10, 23, 45));

        // when & then
        assertThat(aGroup.countMatch(bGroup)).isEqualTo(4);
    }
}
