package lotto.model;

import lotto.model.LottoRoundResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
class LottoRoundResultTest {

    @ParameterizedTest(name = "맞은 볼 수가 {0}이고, bounsMatch가 {1}일 때")
    @CsvSource(value = {"0, false", "1, false", "1, true", "2, false", "2, true"})
    void 번호가_2개_이하로_일치하면_꽝을_반환한다(final int count, final boolean bonusMatch) {
        assertThat(LottoRoundResult.makeRoundResult(count, bonusMatch)).isEqualTo(LottoRoundResult.BOOM);
    }

    @ParameterizedTest(name = "bounsMatch가 {0}일 때")
    @ValueSource(booleans = {true, false})
    void 번호가_3개_일치하면면_5등을_반환한다(final boolean bonusMatch) {
        assertThat(LottoRoundResult.makeRoundResult(3, bonusMatch)).isEqualTo(LottoRoundResult.FIFTH);
    }

    @ParameterizedTest(name = "bounsMatch가 {0}일 때")
    @ValueSource(booleans = {true, false})
    void 번호가_4개_일치하면_4등을_반환한다(final boolean bonusMatch) {
        assertThat(LottoRoundResult.makeRoundResult(4, bonusMatch)).isEqualTo(LottoRoundResult.FOURTH);
    }

    @ParameterizedTest(name = "bounsMatch가 {0}일 때")
    @ValueSource(booleans = {true, false})
    void 번호가_5개_일치하면_3등을_반환한다(final boolean bonusMatch) {
        assertThat(LottoRoundResult.makeRoundResult(5, bonusMatch)).isEqualTo(LottoRoundResult.THIRD);
    }

    @Test
    void 번호가_6개_일치하고_일치한_번호들_중_하나가_보너스_번호이면_2등을_반환한다() {
        assertThat(LottoRoundResult.makeRoundResult(6, true)).isEqualTo(LottoRoundResult.SECOND);
    }

    @Test
    void 번호가_6개_일치하고_일치한_번호들_중_보너스_번호가_없으면_1등을_반환한다() {
        assertThat(LottoRoundResult.makeRoundResult(6, false)).isEqualTo(LottoRoundResult.FIRST);
    }
}
