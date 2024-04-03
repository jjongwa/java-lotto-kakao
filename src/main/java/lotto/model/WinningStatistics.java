package lotto.model;

import lotto.model.vo.PurchaseMoney;
import lotto.model.vo.RevenueRate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningStatistics {

    private static final String INVALID_LOTTO_ROUND_RESULT_COUNT_MESSAGE = "당첨 통계는 최소 1개 이상의 로또 결과가 존재해야 합니다.";
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int ROUND_SCALE = 2;
    private static final int LOTTO_PRICE = 1000;

    private final Map<LottoRoundResult, Integer> statistics;

    public WinningStatistics(final List<LottoRoundResult> lottoRoundResults) {
        validateEmptyResult(lottoRoundResults);
        this.statistics = generateStatistics(lottoRoundResults);
    }

    private void validateEmptyResult(final List<LottoRoundResult> lottoRoundResults) {
        if (lottoRoundResults.isEmpty()) {
            throw new IllegalArgumentException(INVALID_LOTTO_ROUND_RESULT_COUNT_MESSAGE);
        }
    }

    private Map<LottoRoundResult, Integer> generateStatistics(final List<LottoRoundResult> lottoRoundResults) {
        final EnumMap<LottoRoundResult, Integer> newStatistics = new EnumMap<>(LottoRoundResult.class);
        for (final LottoRoundResult result : LottoRoundResult.values()) {
            newStatistics.put(result, ZERO);
        }
        lottoRoundResults.forEach(lottoRoundResult ->
                newStatistics.put(lottoRoundResult, newStatistics.getOrDefault(lottoRoundResult, ZERO) + ONE)
        );
        return newStatistics;
    }

    public RevenueRate calculateRevenueRate() {
        final BigDecimal totalPrice = calculateTotalPrice();
        final PurchaseMoney purchaseMoney = findPurchaseMoney();
        return new RevenueRate(totalPrice.divide(purchaseMoney.toBigDecimal(), ROUND_SCALE, RoundingMode.HALF_UP).doubleValue());
    }

    private BigDecimal calculateTotalPrice() {
        return statistics.entrySet().stream()
                .map(entry -> new BigDecimal(entry.getKey().getPrice()).multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private PurchaseMoney findPurchaseMoney() {
        return new PurchaseMoney(statistics.values().stream()
                .mapToInt(Integer::intValue)
                .sum() * LOTTO_PRICE);
    }

    public int findRankCount(final LottoRoundResult lottoRoundResult) {
        return statistics.get(lottoRoundResult);
    }
}
