package lotto.model;

import lotto.model.vo.LottoBall;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ManualLottoNumbers {

    private final List<Integer> numbers;

    public ManualLottoNumbers(final String[] numbersInput) {
        this.numbers = Arrays.stream(numbersInput)
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toUnmodifiableList());
    }

    public Predicate<LottoBall> getLottoBallPredicate() {
        return lottoBall -> this.numbers.stream()
                .anyMatch(lottoBall::hasNumber);
    }
}
