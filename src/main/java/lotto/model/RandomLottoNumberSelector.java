package lotto.model;

import lotto.model.vo.LottoBall;

import java.util.Collections;
import java.util.List;

public class RandomLottoNumberSelector implements LottoNumberSelector {

    private static final int ZERO = 0;
    private static final int LOTTO_BALLS_COUNT = 6;

    @Override
    public LottoGroup select(final List<LottoBall> lottoBalls) {
        Collections.shuffle(lottoBalls);
        return new LottoGroup(lottoBalls.subList(ZERO, LOTTO_BALLS_COUNT));
    }
}
