package lotto.view;

import lotto.dto.GameResultDTO;
import lotto.dto.LottoGroupDTOs;

import java.util.Map;

public class OutputView {

    private static final String PURCHASE_COUNT_MESSAGE = "개를 구매했습니다.";
    private static final String JOIN_DELIMITER = ", ";
    private static final String GAME_RESULT_ANNOUNCE_MESSAGE = "당첨 통계\n---------";
    private static final String FIFTH = "FIFTH";
    private static final String FOURTH = "FOURTH";
    private static final String THIRD = "THIRD";
    private static final String SECOND = "SECOND";
    private static final String FIRST = "FIRST";
    private static final String COUNT_MESSAGE = "개";
    private static final String FIFTH_MESSAGE = "3개 일 (5000)- ";
    private static final String FOURTH_MESSAGE = "4개 일 (50000)- ";
    private static final String THIRD_MESSAGE = "5개 일치 (1500000원)- ";
    private static final String SECOND_MESSAGE = "5개 일치, 보너스 볼 일치(30000000원) - ";
    private static final String TOTAL_REVENUE_MESSAGE = "총 수익률은 ";
    private static final String FIRST_MESSAGE = "6개 일치 (2000000000원)- ";
    private static final String RESULT_END_MESSAGE = "입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";

    private OutputView() {
    }

    public static void printPurchaseCount(final int purchaseCount) {
        System.out.println(purchaseCount + PURCHASE_COUNT_MESSAGE);
    }

    public static void printLottoGroups(final LottoGroupDTOs lottoGroupDtos) {
        lottoGroupDtos.getValues()
                .forEach(lottoGroupDto -> System.out.println(String.join(JOIN_DELIMITER, lottoGroupDto.getNumbers().toString())));
        System.out.println();
    }

    public static void printGameResult(final GameResultDTO gameResultDTO) {
        final Map<String, Integer> statistics = gameResultDTO.getStatistics();
        System.out.println(GAME_RESULT_ANNOUNCE_MESSAGE);
        System.out.println(FIFTH_MESSAGE + statistics.get(FIFTH) + COUNT_MESSAGE);
        System.out.println(FOURTH_MESSAGE + statistics.get(FOURTH) + COUNT_MESSAGE);
        System.out.println(THIRD_MESSAGE + statistics.get(THIRD) + COUNT_MESSAGE);
        System.out.println(SECOND_MESSAGE + statistics.get(SECOND) + COUNT_MESSAGE);
        System.out.println(FIRST_MESSAGE + statistics.get(FIRST) + COUNT_MESSAGE);
        System.out.println(TOTAL_REVENUE_MESSAGE + gameResultDTO.getRevenueRate() + RESULT_END_MESSAGE);
    }
}
