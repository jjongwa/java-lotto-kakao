package lotto.model;

import lotto.model.vo.LottoBall;

import java.util.List;

@FunctionalInterface
public interface LottoNumberSelector {

    LottoGroup generate(final List<LottoBall> lottoBalls);
}
