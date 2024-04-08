package lotto.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoGame {

    private static final int ZERO = 0;

    private final List<LottoGroup> lottoGroups;

    public LottoGame(final PurchaseCounts purchaseCounts, final LottoMachine lottoMachine) {
        if (purchaseCounts.getManualPurchaseCount() == ZERO) {
            this.lottoGroups = makeRandomLottoGroups(purchaseCounts, lottoMachine)
                    .collect(Collectors.toUnmodifiableList());
            return;
        }
        this.lottoGroups = Stream.concat(
                makeManualLottoGroups(lottoMachine, purchaseCounts.getManualPurchaseCount()),
                makeRandomLottoGroups(purchaseCounts, lottoMachine)
        ).collect(Collectors.toUnmodifiableList());

    }

    private static Stream<LottoGroup> makeManualLottoGroups(final LottoMachine lottoMachine, final int manualPurchaseCount) {
        return IntStream.range(ZERO, manualPurchaseCount)
                .mapToObj(count -> lottoMachine.manualGenerate());
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
