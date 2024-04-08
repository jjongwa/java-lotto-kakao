package lotto.model;

import lotto.model.vo.LottoBall;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CustomLottoNumberSelector implements LottoNumberSelector {

    private static final String SPLIT_REGEX = ",";

    private final LinkedList<List<Integer>> lottoNumbers;

    public CustomLottoNumberSelector(final List<String> manualLottoNumbersInput) {
        this.lottoNumbers = new LinkedList<>(manualLottoNumbersInput.stream()
                .map(input -> input.split(SPLIT_REGEX))
                .map(convertToNumbers())
                .collect(Collectors.toUnmodifiableList()));
    }

    private Function<String[], List<Integer>> convertToNumbers() {
        return numbers -> Arrays.stream(numbers)
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public LottoGroup select(final List<LottoBall> lottoBalls) {
        return manualGenerate(lottoBalls, lottoNumbers.remove());
    }

    public LottoGroup manualGenerate(final List<LottoBall> lottoBalls, final List<Integer> numbers) {
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
