package lotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.LottoRoundResult.BOOM;
import static lotto.LottoRoundResult.FIFTH;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
class WinningStatisticsTest {

    @Test
    void 게임_결과의_개수가_0초과이면_잘_생성된다() {
        assertDoesNotThrow(() -> new WinningStatistics(List.of(BOOM, FIFTH)));
    }

    @Test
    void 게임_결과의_개수가_0이면_예외_처리한다() {
        // given
        final List<LottoRoundResult> emptyResults = List.of();

        // when & then
        assertThatThrownBy(() -> new WinningStatistics(emptyResults))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 통계는 최소 1개 이상의 로또 결과가 존재해야 합니다.");
    }
}
