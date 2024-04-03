package lotto.model;

import lotto.model.vo.LottoBall;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
class LottoGroupTest {

    @Test
    void 올바르게_로또_그룹을_생성할_수_있다() {
        assertDoesNotThrow(() -> new LottoGroup(List.of(
                new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(5),
                new LottoBall(6)))
        );
    }

    @Test
    void 로또_그룹에_6개_이외의_로또_볼이_존재할_시_예외_처리한다() {
        // given
        final List<LottoBall> balls = List.of(new LottoBall(1), new LottoBall(2));

        // when & then
        assertThatThrownBy(() -> new LottoGroup(balls))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 볼은 6개가 존재해야 합니다.");
    }

    @Test
    void 중복된_로또_볼이_존재할_시_예외_처리한다() {
        // given
        final List<LottoBall> balls = List.of(
                new LottoBall(1),
                new LottoBall(2),
                new LottoBall(7),
                new LottoBall(7),
                new LottoBall(23),
                new LottoBall(45)
        );

        // when & then
        assertThatThrownBy(() -> new LottoGroup(balls))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 그룹에서 볼은 중복될 수 없습니다.");
    }

    @Test
    void 다른_로또_그룹과_비교해_일치하는_볼의_개수를_반환할_수_있다() {
        // given
        final LottoGroup aGroup = new LottoGroup(List.of(
                new LottoBall(1),
                new LottoBall(2),
                new LottoBall(7),
                new LottoBall(8),
                new LottoBall(23),
                new LottoBall(45))
        );
        final LottoGroup bGroup = new LottoGroup(List.of(
                new LottoBall(1),
                new LottoBall(3),
                new LottoBall(7),
                new LottoBall(10),
                new LottoBall(23),
                new LottoBall(45))
        );

        // when & then
        assertThat(aGroup.countMatch(bGroup)).isEqualTo(4);
    }
}
