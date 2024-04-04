package lotto.model;

import lotto.model.vo.WinningMoney;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum LottoRoundResult {

    BOOM(0, (count, bonusMatch) -> count < 3),
    FIFTH(5_000, (count, bonusMatch) -> count == 3),
    FOURTH(50_000, (count, bonusMatch) -> count == 4),
    THIRD(1_500_000, (count, bonusMatch) -> count == 5 && !bonusMatch),
    SECOND(30_000_00, (count, bonusMatch) -> count == 5 && bonusMatch),
    FIRST(2_000_000_000, (count, bonusMatch) -> count == 6);

    private final WinningMoney price;
    private final BiPredicate<Integer, Boolean> matchPredicate;

    LottoRoundResult(final int price, final BiPredicate<Integer, Boolean> matchPredicate) {
        this.price = new WinningMoney(BigInteger.valueOf(price));
        this.matchPredicate = matchPredicate;
    }

    public static LottoRoundResult makeRoundResult(final int count, final boolean bonusMatch) {
        return Arrays.stream(LottoRoundResult.values())
                .filter(result -> result.matchPredicate.test(count, bonusMatch))
                .findAny()
                .orElse(LottoRoundResult.BOOM);
    }

    public WinningMoney calculateWinningMoneyByRank(final int count) {
        return price.multiply(count);
    }
}
