package lotto.model;

import lotto.model.vo.LottoBall;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomLottoNumberSelector implements LottoNumberSelector {

    private static final String SPLIT_REGEX = ",";

    private final LinkedList<ManualLottoNumbers> manualLottoNumberGroups;

    public CustomLottoNumberSelector(final List<String> manualLottoNumbersInput) {
        this.manualLottoNumberGroups = new LinkedList<>(manualLottoNumbersInput.stream()
                .map(input -> input.split(SPLIT_REGEX))
                .map(ManualLottoNumbers::new)
                .collect(Collectors.toUnmodifiableList()));
    }

    @Override
    public LottoGroup select(final List<LottoBall> lottoBalls) {
        return manualGenerate(lottoBalls, manualLottoNumberGroups.remove());
    }

    private LottoGroup manualGenerate(final List<LottoBall> lottoBalls, final ManualLottoNumbers numbers) {
        return new LottoGroup(lottoBalls.stream()
                .filter(numbers.getLottoBallPredicate())
                .collect(Collectors.toUnmodifiableList())
        );
    }
}
