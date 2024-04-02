package lotto.model;

import lotto.model.vo.PurchaseCount;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoGame {

    final List<LottoGroup> lottoGroups;

    public LottoGame(final PurchaseCount purchaseCount, final LottoMachine lottoMachine) {
        this.lottoGroups = Stream.generate(lottoMachine::generate)
                .limit(purchaseCount.getCount())
                .collect(Collectors.toUnmodifiableList());
    }

    public List<LottoGroup> getLottoGroups() {
        return lottoGroups;
    }

    public WinningStatistics makeResult(final WinningGroup winningGroup) {
        return new WinningStatistics(lottoGroups.stream()
                .map(winningGroup::calculateRoundResult)
                .collect(Collectors.toUnmodifiableList())
        );
    }
}
