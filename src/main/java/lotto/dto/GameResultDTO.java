package lotto.dto;

import lotto.model.LottoRoundResult;

import java.util.HashMap;
import java.util.Map;

public class GameResultDTO {

    private static final int DEFAULT_VALUE = 0;

    private final Map<String, Integer> statistics;
    private final double revenueRate;

    public GameResultDTO(final Map<LottoRoundResult, Integer> statistics, final double revenueRate) {
        final HashMap<String, Integer> results = new HashMap<>();
        for (final LottoRoundResult result : LottoRoundResult.values()) {
            results.put(result.toString(), statistics.getOrDefault(result, DEFAULT_VALUE));
        }
        this.statistics = results;
        this.revenueRate = revenueRate;
    }

    public Map<String, Integer> getStatistics() {
        return statistics;
    }

    public double getRevenueRate() {
        return revenueRate;
    }
}
