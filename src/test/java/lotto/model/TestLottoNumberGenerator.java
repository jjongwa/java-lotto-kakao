package lotto.model;

import lotto.model.vo.LottoBall;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestLottoNumberGenerator implements LottoNumberSelector {

    private final LinkedList<LottoGroup> lottoGroups;

    @SafeVarargs
    public TestLottoNumberGenerator(final List<Integer>... groupNumbers) {
        this.lottoGroups = new LinkedList<>(Arrays.stream(groupNumbers)
                .map(numbers -> new LottoGroup(numbers.stream()
                        .map(LottoBall::new)
                        .collect(Collectors.toUnmodifiableList()))
                ).collect(Collectors.toUnmodifiableList())
        );
    }

    @Override
    public LottoGroup randomSelect(final List<LottoBall> lottoBalls) {
        return lottoGroups.remove();
    }

    @Override
    public LottoGroup customSelect(final List<LottoBall> lottoBalls, final List<Integer> numbers) {
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
