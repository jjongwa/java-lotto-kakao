package lotto.model;

import lotto.model.vo.LottoBall;
import org.junit.jupiter.api.Test;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
class LottoMachineTest {

    @Test
    void 수동으로_로또_그룹을_생성할_수_있다() {
        // given
        final LottoMachine lottoMachine = new LottoMachine(new ProductionLottoNumberSelector());
        final LottoGroup expected = new LottoGroup(of(
                new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(5),
                new LottoBall(6)
        ));

        // when
        final LottoGroup actual = lottoMachine.manualGenerate(of(1, 2, 3, 4, 5, 6));

        // then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
