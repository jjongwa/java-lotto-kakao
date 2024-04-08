package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
class LottoMachineTest {

    @Test
    void 수동으로_로또_그룹을_생성할_수_있다() {
        // given
        final LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumberSelector(), new CustomLottoNumberSelector(List.of("1,2,3,4,5,6")));

        // when
        final List<Integer> lottoBallNumbers = lottoMachine.manualGenerate().getLottoBallNumbers();

        // then
        assertAll(
                () -> assertThat(lottoBallNumbers).contains(1),
                () -> assertThat(lottoBallNumbers).contains(2),
                () -> assertThat(lottoBallNumbers).contains(3),
                () -> assertThat(lottoBallNumbers).contains(4),
                () -> assertThat(lottoBallNumbers).contains(5),
                () -> assertThat(lottoBallNumbers).contains(6)
        );
    }
}
