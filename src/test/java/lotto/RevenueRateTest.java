package lotto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
class RevenueRateTest {

    @ParameterizedTest
    @CsvSource(value = {"1.6666, 1.67", "1.111111, 1.11", "123.123, 123.12", "9.999, 10"})
    void 수익률은_소수점_둘쨰_자리까지_가진다(final double actualRate, final double expectedRate) {
        // given
        final RevenueRate actualRevenue = new RevenueRate(actualRate);
        final RevenueRate expectedRevenue = new RevenueRate(expectedRate);

        // when & then
        assertThat(actualRevenue).isEqualTo(expectedRevenue);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.01, -1.555, -10, -3.3333})
    void 수익률은_음수가_될_수_없다(final double value) {
        assertThatThrownBy(() -> new RevenueRate(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수익률은 음수가 될 수 없습니다.");
    }
}
