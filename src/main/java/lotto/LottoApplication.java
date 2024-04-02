package lotto;

import lotto.dto.GameResultDTO;
import lotto.dto.LottoGroupDTOs;
import lotto.model.LottoGame;
import lotto.model.RandomLottoMachine;
import lotto.model.WinningGroup;
import lotto.model.WinningStatistics;
import lotto.model.vo.PurchaseCount;
import lotto.model.vo.PurchaseMoney;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        final PurchaseCount purchaseCount = makePurchaseCount();
        final LottoGame lottoGame = initializeLottoGame(purchaseCount);
        final WinningGroup winningGroup = makeWinningGroup();
        executeResult(lottoGame, winningGroup);
    }

    private static PurchaseCount makePurchaseCount() {
        final int purchaseMoneyInput = InputView.readPurchaseMoney();
        final PurchaseCount purchaseCount = new PurchaseMoney(purchaseMoneyInput).toCount();
        OutputView.printPurchaseCount(purchaseCount.getCount());
        return purchaseCount;
    }

    private static LottoGame initializeLottoGame(final PurchaseCount purchaseCount) {
        final LottoGame lottoGame = new LottoGame(purchaseCount, new RandomLottoMachine());
        OutputView.printLottoGroups(new LottoGroupDTOs(lottoGame.getLottoGroups()));
        return lottoGame;
    }

    private static WinningGroup makeWinningGroup() {
        final String winningGroupInput = InputView.readWinningGroup();
        final int bonusBallInput = InputView.readBonusBall();
        return new WinningGroup(winningGroupInput, bonusBallInput);
    }

    private static void executeResult(final LottoGame lottoGame, final WinningGroup winningGroup) {
        final WinningStatistics winningStatistics = lottoGame.makeResult(winningGroup);
        final GameResultDTO gameResultDTO = new GameResultDTO(winningStatistics.getStatistics(), winningStatistics.calculateRevenueRate().getValue());
        OutputView.printGameResult(gameResultDTO);
    }
}
