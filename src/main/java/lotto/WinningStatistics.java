package lotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningStatistics {

    private static final String INVALID_LOTTO_ROUND_RESULT_COUNT_MESSAGE = "당첨 통계는 최소 1개 이상의 로또 결과가 존재해야 합니다.";
    private static final int ZERO = 0;
    private static final int ONE = 1;

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
        lottoRoundResults.forEach(lottoRoundResult ->
                newStatistics.put(lottoRoundResult, newStatistics.getOrDefault(lottoRoundResult, ZERO) + ONE)
        );
        return newStatistics;
    }
}
