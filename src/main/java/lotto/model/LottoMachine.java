package lotto.model;

import lotto.model.vo.LottoBall;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {

    private static final int MIN_LOTTO_NUM = 1;
    private static final int MAX_LOTTO_NUM = 45;

    private static final List<LottoBall> LOTTO_NUMBERS = IntStream.rangeClosed(MIN_LOTTO_NUM, MAX_LOTTO_NUM)
            .boxed()
            .map(LottoBall::new)
            .collect(Collectors.toList());

    private final LottoNumberSelector lottoNumberSelector;

    public LottoMachine(final LottoNumberSelector lottoNumberSelector) {
        this.lottoNumberSelector = lottoNumberSelector;
    }

    public LottoGroup randomGenerate() {
        return lottoNumberSelector.generate(LOTTO_NUMBERS);
    }

    public LottoGroup manualGenerate(final List<Integer> numbers) {
        return new LottoGroup(LOTTO_NUMBERS.stream()
                .filter(getLottoBallPredicate(numbers))
                .collect(Collectors.toList())
        );
    }

    private Predicate<LottoBall> getLottoBallPredicate(final List<Integer> numbers) {
        return lottoBall -> numbers.stream()
                .anyMatch(lottoBall::hasNumber);
    }
}
