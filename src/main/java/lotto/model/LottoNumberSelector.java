package lotto.model;

import lotto.model.vo.LottoBall;

import java.util.List;

public interface LottoNumberSelector {

    LottoGroup randomSelect(final List<LottoBall> lottoBalls);

    LottoGroup customSelect(final List<LottoBall> lottoBalls, final List<Integer>numbers);
}
