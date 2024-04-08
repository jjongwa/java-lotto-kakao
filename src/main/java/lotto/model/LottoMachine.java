package lotto.model;

import lotto.model.vo.LottoBall;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {

    private static final int MIN_LOTTO_NUM = 1;
    private static final int MAX_LOTTO_NUM = 45;

    private static final List<LottoBall> LOTTO_NUMBERS = initializeLottoBalls();

    private static List<LottoBall> initializeLottoBalls() {
        return IntStream.rangeClosed(MIN_LOTTO_NUM, MAX_LOTTO_NUM)
                .boxed()
                .map(LottoBall::new)
                .collect(Collectors.toList());
    }

    private final LottoNumberSelector randomLottoNumberSelector;
    private final LottoNumberSelector customLottoNumberSelector;


    public LottoMachine(final LottoNumberSelector randomLottoNumberSelector, final LottoNumberSelector customLottoNumberSelector) {
        this.randomLottoNumberSelector = randomLottoNumberSelector;
        this.customLottoNumberSelector = customLottoNumberSelector;
    }

    public LottoGroup randomGenerate() {
        return randomLottoNumberSelector.select(LOTTO_NUMBERS);
    }

    public LottoGroup manualGenerate() {
        return customLottoNumberSelector.select(LOTTO_NUMBERS);
    }
}
