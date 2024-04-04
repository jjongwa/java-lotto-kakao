package lotto.model.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
class PurchaseCountTest {

    @ParameterizedTest(name = "개수가 {0}일 때")
    @ValueSource(ints = {1, 2, 5, 10, 100, 1000})
    void 올바르게_구입_개수를_생성할_수_있다(final int count) {
        Assertions.assertDoesNotThrow(() -> new PurchaseCount(count));
    }

    @ParameterizedTest(name = "개수가 {0}일 때")
    @ValueSource(ints = {-1, -20, -100, -1000})
    void 구입_개수가_자연수가_아니면_예외_처리한다(final int count) {
        assertThatThrownBy(() -> new PurchaseCount(count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 개수는 1개 이상이어야 합니다.");
    }
}

