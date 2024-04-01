package lotto;

public class WinningGroup {

    private static final String DUPLICATED_BONUS_BALL_MESSAGE = "로또 그룹에 속한 볼과 보너스 볼은 중복될 수 없습니다.";
    private static final int ONE = 1;

    private final LottoBalls lottoBalls;
    private final LottoBall bonusBall;


    public WinningGroup(final LottoBalls lottoBalls, final LottoBall bonusBall) {
        validateDuplicate(lottoBalls, bonusBall);
        this.lottoBalls = lottoBalls;
        this.bonusBall = bonusBall;
    }

    private void validateDuplicate(final LottoBalls lottoBalls, final LottoBall bonusBall) {
        if (lottoBalls.containsBall(bonusBall)) {
            throw new IllegalArgumentException(DUPLICATED_BONUS_BALL_MESSAGE);
        }
    }

    public LottoRoundResult calculateRoundResult(final LottoBalls otherLottoBalls) {
        final int matchCount = otherLottoBalls.countMatch(lottoBalls);
        final boolean bonusMatch = otherLottoBalls.containsBall(bonusBall);
        return LottoRoundResult.makeRoundResult(totalMatchCount(matchCount, bonusMatch), bonusMatch);
    }

    private int totalMatchCount(final int matchCount, final boolean bonusMatch) {
        if (bonusMatch) {
            return matchCount + ONE;
        }
        return matchCount;
    }
}
