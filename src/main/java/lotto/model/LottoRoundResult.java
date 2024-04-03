package lotto.model;

import lotto.model.vo.WinningMoney;

import java.math.BigInteger;
import java.util.Arrays;

public enum LottoRoundResult {

    BOOM(0, 6),
    FIFTH(5_000, 5),
    FOURTH(50_000, 4),
    THIRD(1_500_000, 3),
    SECOND(30_000_000, 2),
    FIRST(2_000_000_000, 1);

    private static final int TWO = 2;
    private static final int FIVE = 5;
    private static final int SIX = 6;

    private final WinningMoney price;
    private final int rank;

    LottoRoundResult(final int price, int rank) {
        this.price = new WinningMoney(BigInteger.valueOf(price));
        this.rank = rank;
    }

    public static LottoRoundResult makeRoundResult(final int count, final boolean bonusMatch) {
        if (count == SIX) {
            return LottoRoundResult.FIRST;
        }
        if (count == FIVE && bonusMatch) {
            return LottoRoundResult.SECOND;
        }
        if (count > TWO) {
            return findResultByRank(SIX - count + TWO);
        }
        return LottoRoundResult.BOOM;
    }

    private static LottoRoundResult findResultByRank(final int rank) {
        return Arrays.stream(values())
                .filter(value -> value.rank == rank)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public WinningMoney getPrice() {
        return price;
    }
}
