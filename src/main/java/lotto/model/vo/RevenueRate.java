package lotto.model.vo;

import java.util.Objects;

public class RevenueRate {

    private static final int ZERO = 0;
    private static final double DECIMAL_PLACE = 100D;
    private static final String INVALID_VALUE_MESSAGE = "수익률은 음수가 될 수 없습니다.";

    private final double value;

    public RevenueRate(final double value) {
        validateMinusValue(value);
        this.value = Math.round(value * DECIMAL_PLACE) / DECIMAL_PLACE;
    }

    private void validateMinusValue(final double value) {
        if (value < ZERO) {
            throw new IllegalArgumentException(INVALID_VALUE_MESSAGE);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RevenueRate that = (RevenueRate) o;
        return Double.compare(value, that.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
