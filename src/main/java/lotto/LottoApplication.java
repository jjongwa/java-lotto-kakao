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
        OutputView.printPurchaseCount(purchaseCount.getCount());

        final LottoGame lottoGame = new LottoGame(purchaseCount, new RandomLottoMachine());
        OutputView.printLottoGroups(new LottoGroupDTOs(lottoGame.getLottoGroups()));

        final WinningGroup winningGroup = makeWinningGroup();

        final GameResultDTO gameResultDTO = executeResult(lottoGame, winningGroup);
        OutputView.printGameResult(gameResultDTO);
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

    private static GameResultDTO executeResult(final LottoGame lottoGame, final WinningGroup winningGroup) {
        final WinningStatistics winningStatistics = lottoGame.makeResult(winningGroup);
        return new GameResultDTO(winningStatistics.getStatistics(), winningStatistics.calculateRevenueRate().getValue());
    }
}
