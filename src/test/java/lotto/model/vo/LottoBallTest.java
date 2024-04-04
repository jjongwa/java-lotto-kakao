package lotto.model.vo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@SuppressWarnings("NonAsciiCharacters")
class LottoBallTest {

    @ParameterizedTest(name = "번호가 {0}일 경우")
    @ValueSource(ints = {
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
            21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
            31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
            41, 42, 43, 44, 45
    })
    void 로또_번호를_올바르게_생성할_수_있다(final int number) {
        assertDoesNotThrow(() -> new LottoBall(number));
    }

    @ParameterizedTest(name = "번호가 {0}일 경우")
    @ValueSource(ints = {-1, 0, 46, 86, 231})
    void 번호가_1에서_45이외일_경우_예외_처리_한다(final int number) {
        assertThatThrownBy(() -> new LottoBall(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("번호는 1이상 45이하만 가능합니다.");
    }
}
