package lotto.model.vo;

import java.math.BigDecimal;
import java.util.Objects;

public class PurchaseMoney {

    private static final int ZERO = 0;
    private static final int SINGLE_LOTTO_COST = 1000;
    private static final String INVALID_AMOUNT_MESSAGE = "숫자는 1000단위의 자연수여야 합니다.";

    private final int amount;

    public PurchaseMoney(final int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(final int amount) {
        if (amount % SINGLE_LOTTO_COST != ZERO) {
            throw new IllegalArgumentException(INVALID_AMOUNT_MESSAGE);
        }
        if (amount <= ZERO) {
            throw new IllegalArgumentException(INVALID_AMOUNT_MESSAGE);
        }
    }

    public PurchaseCount toPurchaseCount() {
        return new PurchaseCount(this.amount / SINGLE_LOTTO_COST);
    }

    public BigDecimal toBigDecimal() {
        return new BigDecimal(amount);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PurchaseMoney that = (PurchaseMoney) o;
        return amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
