package lotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
class WinningGroupTest {

    @Test
    void 올바르게_당첨_번호를_생성할_수_있다() {
        // given
        final LottoBalls lottoBalls = new LottoBalls(List.of(1, 7, 15, 23, 35, 45));
        final LottoBall bonusBall = new LottoBall(27);

        // when & then
        assertDoesNotThrow(() -> new WinningGroup(lottoBalls, bonusBall));
    }

    @Test
    void 로또_그룹에_속한_볼과_보너스_볼이_중복되면_예외_처리한다() {
        // given
        final LottoBalls lottoBalls = new LottoBalls(List.of(1, 2, 3, 4, 5, 6));
        final LottoBall bonusBall = new LottoBall(1);

        // when & then
        assertThatThrownBy(() -> new WinningGroup(lottoBalls, bonusBall))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 그룹에 속한 볼과 보너스 볼은 중복될 수 없습니다.");
    }
}
