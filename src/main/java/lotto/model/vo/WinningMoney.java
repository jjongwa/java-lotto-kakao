package lotto.model.vo;

import java.math.BigInteger;
import java.util.Objects;

public class WinningMoney {

    private static final int ZERO_NUMBER = 0;
    public static final String INVALID_AMOUNT_MESSAGE = "당첨금은 음수일 수 없습니다.";

    public static final WinningMoney ZERO = new WinningMoney(BigInteger.ZERO);

    private final BigInteger amount;

    public WinningMoney(final BigInteger amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(final BigInteger amount) {
        if (isNegativeAmount(amount)) {
            throw new IllegalArgumentException(INVALID_AMOUNT_MESSAGE);
        }
    }

    public WinningMoney add(final WinningMoney otherWinningMoney) {
        return new WinningMoney(amount.add(otherWinningMoney.amount));
    }

    public WinningMoney multiply(final int value) {
        return new WinningMoney(amount.multiply(BigInteger.valueOf(value)));
    }

    private boolean isNegativeAmount(final BigInteger amount) {
        return amount.compareTo(BigInteger.ZERO) < ZERO_NUMBER;
    }

    public BigInteger getAmount() {
        return amount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final WinningMoney that = (WinningMoney) o;
        return Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
