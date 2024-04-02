package lotto.model.vo;

import java.util.Objects;

public class LottoBall {

    private static final int RANGE_MIN = 1;
    private static final int RANGE_MAX = 45;
    private static final String INVALID_NUMBER_RANGE_MESSAGE = "번호는 1이상 45이하만 가능합니다.";

    private final int number;

    public LottoBall(final int number) {
        validateNumberRange(number);
        this.number = number;
    }

    private void validateNumberRange(final int number) {
        if (number < RANGE_MIN || number > RANGE_MAX) {
            throw new IllegalArgumentException(INVALID_NUMBER_RANGE_MESSAGE);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LottoBall lottoBall = (LottoBall) o;
        return number == lottoBall.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
