package lotto;

public enum LottoRoundResult {

    BOOM,
    FIFTH,
    FOURTH,
    THIRD,
    SECOND,
    FIRST;

    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;

    public static LottoRoundResult makeRoundResult(final int count, final boolean bonusMatch) {
        if (count == THREE) {
            return LottoRoundResult.FIFTH;
        }
        if (count == FOUR) {
            return LottoRoundResult.FOURTH;
        }
        if (count == FIVE) {
            return LottoRoundResult.THIRD;
        }
        if (count == SIX && bonusMatch) {
            return LottoRoundResult.SECOND;
        }
        if (count == SIX) {
            return LottoRoundResult.FIRST;
        }
        return LottoRoundResult.BOOM;
    }
}
