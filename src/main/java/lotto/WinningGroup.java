package lotto;

public class WinningGroup {

    private static final String DUPLICATED_BONUS_BALL_MESSAGE = "로또 그룹에 속한 볼과 보너스 볼은 중복될 수 없습니다.";
    private static final int ONE = 1;

    private final LottoGroup lottoGroup;
    private final LottoBall bonusBall;


    public WinningGroup(final LottoGroup lottoGroup, final LottoBall bonusBall) {
        validateDuplicate(lottoGroup, bonusBall);
        this.lottoGroup = lottoGroup;
        this.bonusBall = bonusBall;
    }

    private void validateDuplicate(final LottoGroup lottoGroup, final LottoBall bonusBall) {
        if (lottoGroup.containsBall(bonusBall)) {
            throw new IllegalArgumentException(DUPLICATED_BONUS_BALL_MESSAGE);
        }
    }

    public LottoRoundResult calculateRoundResult(final LottoGroup otherLottoGroup) {
        final int matchCount = otherLottoGroup.countMatch(lottoGroup);
        final boolean bonusMatch = otherLottoGroup.containsBall(bonusBall);
        return LottoRoundResult.makeRoundResult(totalMatchCount(matchCount, bonusMatch), bonusMatch);
    }

    private int totalMatchCount(final int matchCount, final boolean bonusMatch) {
        if (bonusMatch) {
            return matchCount + ONE;
        }
        return matchCount;
    }
}
