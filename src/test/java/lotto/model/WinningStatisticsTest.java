package lotto.model;

import lotto.model.vo.RevenueRate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.model.LottoRoundResult.BOOM;
import static lotto.model.LottoRoundResult.FIFTH;
import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    void 수익률을_계산할_수_있다() {
        // given
        final WinningStatistics winningStatistics = new WinningStatistics(List.of(BOOM, BOOM, FIFTH));
        final RevenueRate expectedRevenue = new RevenueRate(1.67);

        // when
        final RevenueRate actualRevenue = winningStatistics.calculateRevenueRate();

        // & then
        assertThat(actualRevenue).isEqualTo(expectedRevenue);
    }
}
