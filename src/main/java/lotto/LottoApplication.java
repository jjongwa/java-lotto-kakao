package lotto;

import lotto.model.*;
import lotto.model.vo.PurchaseCount;
import lotto.model.vo.PurchaseMoney;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoApplication {

    public static void main(String[] args) {
        final PurchaseCounts purchaseCounts = makePurchaseCounts();

        final List<String> manualLottoNumbersInput = InputView.readManualLottoNumbers(purchaseCounts.getManualPurchaseCount());
        final LottoGame lottoGame = new LottoGame(
                purchaseCounts,
                new LottoMachine(new RandomLottoNumberSelector(), new CustomLottoNumberSelector(manualLottoNumbersInput))
        );
        OutputView.printPurchaseStatus(purchaseCounts, lottoGame);

        final WinningGroup winningGroup = makeWinningGroup();

        final WinningStatistics winningStatistics = lottoGame.makeResult(winningGroup);
        OutputView.printGameResult(winningStatistics, winningStatistics.calculateRevenueRate());
    }

    private static PurchaseCounts makePurchaseCounts() {
        final int purchaseMoneyInput = InputView.readPurchaseMoney();
        final int manualPurchaseCountInput = InputView.readManualPurchaseCount();
        return new PurchaseCounts(new PurchaseMoney(purchaseMoneyInput), new PurchaseCount(manualPurchaseCountInput));
    }

    private static WinningGroup makeWinningGroup() {
        final String winningGroupInput = InputView.readWinningGroup();
        final int bonusBallInput = InputView.readBonusBall();
        return new WinningGroup(winningGroupInput, bonusBallInput);
    }
}
