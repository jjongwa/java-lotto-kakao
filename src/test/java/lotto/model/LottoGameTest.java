package lotto.model;

import lotto.model.vo.PurchaseCount;
import lotto.model.vo.PurchaseMoney;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static lotto.model.LottoRoundResult.BOOM;
import static lotto.model.LottoRoundResult.FIRST;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
class LottoGameTest {

    @ParameterizedTest(name = "구입 개수가 {0}일 경우")
    @ValueSource(ints = {1, 2, 3, 4, 10, 15})
    void 로또_그룹을_가지고_있다(final int purchaseNumber) {
        // given
        final RandomLottoNumberSelector randomLottoNumberSelector = new RandomLottoNumberSelector();
        final LottoGame lottoGame = new LottoGame(
                new PurchaseCounts(new PurchaseMoney(purchaseNumber * 1000), new PurchaseCount(0)),
                new LottoMachine(randomLottoNumberSelector, randomLottoNumberSelector)
        );

        // when & then
        assertThat(lottoGame.getLottoGroups()).hasSize(purchaseNumber);
    }

    @Test
    void 당첨_그룹을_받아_당첨_통계를_반환할_수_있다() {
        // given
        final TestLottoNumberGenerator testLottoNumberGenerator = new TestLottoNumberGenerator(List.of(1, 2, 3, 4, 5, 6), List.of(7, 8, 9, 10, 11, 12));
        final LottoGame lottoGame = new LottoGame(
                new PurchaseCounts(new PurchaseMoney(2000), new PurchaseCount(0)),
                new LottoMachine(testLottoNumberGenerator, testLottoNumberGenerator)
        );
        final WinningGroup winningGroup = new WinningGroup("1, 2, 3, 4, 5, 6", 7);
        final WinningStatistics expectedStatistics = new WinningStatistics(List.of(FIRST, BOOM));

        // when
        final WinningStatistics actualStatistics = lottoGame.makeResult(winningGroup);

        // then
        assertThat(actualStatistics).usingRecursiveComparison().isEqualTo(expectedStatistics);
    }
}
