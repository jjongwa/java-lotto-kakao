package lotto.model.vo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
class PurchaseMoneyTest {

    @ParameterizedTest(name = "숫자가 {0}일 경우")
    @ValueSource(ints = {1000, 2000, 3000, 10000, 20000, 50000, 1000000})
    void 올바르게_구입_금액을_생성할_수_있다(final int amount) {
        assertDoesNotThrow(() -> new PurchaseMoney(amount));
    }

    @ParameterizedTest(name = "숫자가 {0}일 경우")
    @ValueSource(ints = {-1, 1, 2, 500, 999, 1001, 1111, 12001})
    void 숫자가_1000단위의_자연수가_아니면_예외_처리한다(final int amount) {
        assertThatThrownBy(() -> new PurchaseMoney(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자는 1000단위의 자연수여야 합니다.");
    }
}
