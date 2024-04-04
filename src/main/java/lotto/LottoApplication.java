package lotto;

import lotto.model.*;
import lotto.model.vo.PurchaseCount;
import lotto.model.vo.PurchaseMoney;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        final PurchaseCount purchaseCount = makePurchaseCount();
        OutputView.printPurchaseCount(purchaseCount.getCount());

        final LottoGame lottoGame = new LottoGame(purchaseCount, new LottoMachine(new RandomLottoNumberSelector()));
        OutputView.printLottoGroups2(lottoGame.getLottoGroups());

        final WinningGroup winningGroup = makeWinningGroup();

        final WinningStatistics winningStatistics = lottoGame.makeResult(winningGroup);
        OutputView.printGameResult2(winningStatistics, winningStatistics.calculateRevenueRate());
    }

    private static PurchaseCount makePurchaseCount() {
        final int purchaseMoneyInput = InputView.readPurchaseMoney();
        return new PurchaseMoney(purchaseMoneyInput).toPurchaseCount();
    }

    private static WinningGroup makeWinningGroup() {
        final String winningGroupInput = InputView.readWinningGroup();
        final int bonusBallInput = InputView.readBonusBall();
        return new WinningGroup(winningGroupInput, bonusBallInput);
    }
}
