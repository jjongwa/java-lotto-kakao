package lotto.model;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoGame {

    private static final String SPLIT_REGEX = ",";
    private static final int ZERO = 0;

    private final List<LottoGroup> lottoGroups;

    public LottoGame(final PurchaseCounts purchaseCounts, final List<String> manualLottoNumbersInput, final LottoMachine lottoMachine) {
        if (purchaseCounts.getManualPurchaseCount() == ZERO) {
            this.lottoGroups = makeRandomLottoGroups(purchaseCounts, lottoMachine)
                    .collect(Collectors.toUnmodifiableList());
            return;
        }
        this.lottoGroups = Stream.concat(
                makeManualLottoGroups(manualLottoNumbersInput, lottoMachine),
                makeRandomLottoGroups(purchaseCounts, lottoMachine)
        ).collect(Collectors.toUnmodifiableList());

    }

    private static Stream<LottoGroup> makeManualLottoGroups(final List<String> manualLottoNumbersInput, final LottoMachine lottoMachine) {
        return manualLottoNumbersInput.stream()
                .map(input -> input.split(SPLIT_REGEX))
                .map(convertToNumbers())
                .map(lottoMachine::manualGenerate);
    }

    private static Function<String[], List<Integer>> convertToNumbers() {
        return numbers -> Arrays.stream(numbers)
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toUnmodifiableList());
    }

    private static Stream<LottoGroup> makeRandomLottoGroups(final PurchaseCounts purchaseCounts, final LottoMachine lottoMachine) {
        return IntStream.range(ZERO, purchaseCounts.getRandomPurchaseCount())
                .mapToObj(it -> lottoMachine.randomGenerate());
    }

    public List<LottoGroup> getLottoGroups() {
        return lottoGroups;
    }

    public WinningStatistics makeResult(final WinningGroup winningGroup) {
        return new WinningStatistics(lottoGroups.stream()
                .map(winningGroup::compareAndMakeResult)
                .collect(Collectors.toUnmodifiableList())
        );
    }
}
