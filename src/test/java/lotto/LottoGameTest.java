package lotto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
class LottoGameTest {

    @ParameterizedTest(name = "구입 개수가 {0}일 경우")
    @ValueSource(ints = {1, 2, 3, 4, 10, 15})
    void 로또_그룹을_가지고_있다(final int purchaseNumber) {
        // given
        LottoGame lottoGame = new LottoGame(new PurchaseCount(purchaseNumber), new LottoMachine());

        // when & then
        assertThat(lottoGame.getLottoGroups()).hasSize(purchaseNumber);
    }
}
