package lotto.model.vo;

import lotto.model.vo.WinningMoney;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
class WinningMoneyTest {

    @ParameterizedTest(name = "금액이 {0}일 떄")
    @ValueSource(ints = {0, 50000, 100000, 2000000, Integer.MAX_VALUE})
    void 올바르게_당첨금을_생성할_수_있다(final int amount) {
        assertDoesNotThrow(() -> new WinningMoney(BigInteger.valueOf(amount)));
    }

    @ParameterizedTest(name = "금액이 {0}일 떄")
    @ValueSource(ints = {-1, -1000, -10000, -50000})
    void 당첨금이_음수이면_예외_처리한다(final int amount) {
        // given
        final BigInteger winningAmount = BigInteger.valueOf(amount);

        // when & then
        assertThatThrownBy(() -> new WinningMoney(winningAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨금은 음수일 수 없습니다.");
    }

    @ParameterizedTest(name = "금액이 {0}일 떄")
    @ValueSource(strings = {"123456789123456789", "123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789"})
    void 당첨금이_int_범위값을_넘어도_올바르게_생성할_수_있다(final String amount) {
        assertDoesNotThrow(() -> new WinningMoney(new BigInteger(amount)));
    }
}
