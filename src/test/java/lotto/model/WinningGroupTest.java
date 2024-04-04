package lotto.model;

import lotto.model.vo.LottoBall;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
class WinningGroupTest {

    @ParameterizedTest(name = "당첨 번호: {0} 보너스 번호: {1}")
    @CsvSource(delimiter = ':', value = {"1, 2, 3, 4, 5, 6 : 7", "1, 7, 15, 23, 35, 45 : 27", "1, 7, 15, 23, 25, 34 : 45"})
    void 올바르게_당첨_번호를_생성할_수_있다(final String winningNumbers, final int bonusNumber) {
        assertDoesNotThrow(() -> new WinningGroup(winningNumbers, bonusNumber));
    }

    @ParameterizedTest(name = "당첨 번호: {0} 보너스 번호: {1}")
    @CsvSource(delimiter = ':', value = {"1, 2, 3, 4, 5, 6 : 6", "1, 7, 15, 23, 35, 45 : 1", "1, 7, 15, 23, 25, 34 : 15"})
    void 로또_그룹에_속한_볼과_보너스_볼이_중복되면_예외_처리한다(final String winningNumbers, final int bonusNumber) {
        assertThatThrownBy(() -> new WinningGroup(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 그룹에 속한 볼과 보너스 볼은 중복될 수 없습니다.");
    }

    @ParameterizedTest(name = "당첨 번호: {0} 보너스 번호: {1} 결과: {2}")
    @CsvSource(delimiter = ':', value = {
            "1, 2, 3, 4, 5, 6 : 7 : FIRST",
            "1, 2, 3, 4, 5, 45 : 6 : SECOND",
            "1, 2, 3, 4, 5, 44 : 45 : THIRD",
            "1, 2, 3, 4, 43, 44 : 45 : FOURTH",
            "1, 2, 3, 42, 43, 44 : 45 : FIFTH",
            "1, 2, 41, 42, 43, 44 : 45 : BOOM"
    })
    void 로또_그룹과_비교해_결과를_반환할_수_있다(final String winningNumbers, final int bonusNumber, final LottoRoundResult lottoRoundResult) {
        // given
        final WinningGroup winningGroup = new WinningGroup(winningNumbers, bonusNumber);
        final LottoGroup lottoGroup = new LottoGroup(List.of(
                new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(5),
                new LottoBall(6))
        );

        // when
        assertThat(winningGroup.compareAndMakeResult(lottoGroup)).isEqualTo(lottoRoundResult);
    }
}
