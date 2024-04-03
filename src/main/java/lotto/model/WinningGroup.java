package lotto.model;

import lotto.model.vo.LottoBall;

import java.util.Arrays;
import java.util.stream.Collectors;

public class WinningGroup {

    private static final String DUPLICATED_BONUS_BALL_MESSAGE = "로또 그룹에 속한 볼과 보너스 볼은 중복될 수 없습니다.";
    private static final String SPLIT_REGEX = ", ";

    private final LottoGroup lottoGroup;
    private final LottoBall bonusBall;


    private WinningGroup(final LottoGroup lottoGroup, final LottoBall bonusBall) {
        validateDuplicate(lottoGroup, bonusBall);
        this.lottoGroup = lottoGroup;
        this.bonusBall = bonusBall;
    }

    public WinningGroup(final String winningNumbers, final int bonusNumber) {
        this(new LottoGroup(Arrays.stream(winningNumbers.split(SPLIT_REGEX))
                        .map(Integer::parseInt)
                        .collect(Collectors.toUnmodifiableList())),
                new LottoBall(bonusNumber));
    }

    private void validateDuplicate(final LottoGroup lottoGroup, final LottoBall bonusBall) {
        if (lottoGroup.containsBall(bonusBall)) {
            throw new IllegalArgumentException(DUPLICATED_BONUS_BALL_MESSAGE);
        }
    }

    public LottoRoundResult calculateRoundResult(final LottoGroup otherLottoGroup) {
        final int matchCount = otherLottoGroup.countMatch(lottoGroup);
        final boolean bonusMatch = otherLottoGroup.containsBall(bonusBall);
        return LottoRoundResult.makeRoundResult(matchCount, bonusMatch);
    }
}
