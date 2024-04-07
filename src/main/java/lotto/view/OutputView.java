package lotto.view;

import lotto.model.LottoGame;
import lotto.model.PurchaseCounts;
import lotto.model.WinningStatistics;
import lotto.model.vo.RevenueRate;

import static lotto.model.LottoRoundResult.*;

public class OutputView {

    private static final String PURCHASE_COUNT_MESSAGE = "개를 구매했습니다.";
    private static final String JOIN_DELIMITER = ", ";
    private static final String GAME_RESULT_ANNOUNCE_MESSAGE = "당첨 통계\n---------";
    private static final String RANK_RESULT_FORMAT = "%s %d%s%n";
    private static final String REVENUE_RESULT_FORMAT = "%s%.2f";
    private static final String COUNT_MESSAGE = "개";
    private static final String FIFTH_MESSAGE = "3개 일치 (5000)- ";
    private static final String FOURTH_MESSAGE = "4개 일치 (50000)- ";
    private static final String THIRD_MESSAGE = "5개 일치 (1500000원)- ";
    private static final String SECOND_MESSAGE = "5개 일치, 보너스 볼 일치(30000000원) - ";
    private static final String TOTAL_REVENUE_MESSAGE = "총 수익률은 ";
    private static final String FIRST_MESSAGE = "6개 일치 (2000000000원)- ";
    private static final String RESULT_END_MESSAGE = "입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    private static final String PURCHASE_COUNT_RESULT = "%s%d%s%d%s";
    private static final String MANUAL_MESSAGE = "수동으로 ";
    private static final String RANDOM_MESSAGE = "장, 자동으로 ";

    private OutputView() {
    }

    public static void printPurchaseStatus(final PurchaseCounts purchaseCounts, final LottoGame lottoGame) {
        System.out.println(String.format(PURCHASE_COUNT_RESULT, MANUAL_MESSAGE, purchaseCounts.getManualPurchaseCount(), RANDOM_MESSAGE, purchaseCounts.getRandomPurchaseCount(), PURCHASE_COUNT_MESSAGE));
        lottoGame.getLottoGroups()
                .forEach(lottoGroup -> System.out.println(String.join(JOIN_DELIMITER, lottoGroup.getLottoBallNumbers().toString())));
        System.out.println();
    }

    public static void printGameResult(final WinningStatistics statistics, final RevenueRate revenueRate) {
        System.out.println(GAME_RESULT_ANNOUNCE_MESSAGE);
        System.out.printf(RANK_RESULT_FORMAT, FIFTH_MESSAGE, statistics.findRankCount(FIFTH), COUNT_MESSAGE);
        System.out.printf(RANK_RESULT_FORMAT, FOURTH_MESSAGE, statistics.findRankCount(FOURTH), COUNT_MESSAGE);
        System.out.printf(RANK_RESULT_FORMAT, THIRD_MESSAGE, statistics.findRankCount(THIRD), COUNT_MESSAGE);
        System.out.printf(RANK_RESULT_FORMAT, SECOND_MESSAGE, statistics.findRankCount(SECOND), COUNT_MESSAGE);
        System.out.printf(RANK_RESULT_FORMAT, FIRST_MESSAGE, statistics.findRankCount(FIRST), COUNT_MESSAGE);
        System.out.printf(REVENUE_RESULT_FORMAT, TOTAL_REVENUE_MESSAGE, revenueRate.getValue());
        if (revenueRate.isLoss()) {
            System.out.println(RESULT_END_MESSAGE);
        }
    }
}
