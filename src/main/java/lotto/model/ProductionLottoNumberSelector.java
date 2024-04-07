package lotto.model;

import lotto.model.vo.LottoBall;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductionLottoNumberSelector implements LottoNumberSelector {

    private static final int ZERO = 0;
    private static final int LOTTO_BALLS_COUNT = 6;

    @Override
    public LottoGroup randomSelect(final List<LottoBall> lottoBalls) {
        Collections.shuffle(lottoBalls);
        return new LottoGroup(lottoBalls.subList(ZERO, LOTTO_BALLS_COUNT));
    }

    @Override
    public LottoGroup customSelect(final List<LottoBall> lottoBalls, final List<Integer>numbers) {
        return new LottoGroup(lottoBalls.stream()
                .filter(getLottoBallPredicate(numbers))
                .collect(Collectors.toUnmodifiableList())
        );
    }

    private Predicate<LottoBall> getLottoBallPredicate(final List<Integer> numbers) {
        return lottoBall -> numbers.stream()
                .anyMatch(lottoBall::hasNumber);
    }
}
