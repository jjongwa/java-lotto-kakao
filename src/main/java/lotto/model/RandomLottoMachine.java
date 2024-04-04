package lotto.model;

import lotto.model.vo.LottoBall;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoMachine implements LottoMachine {

    private static final int MIN_LOTTO_NUM = 1;
    private static final int MAX_LOTTO_NUM = 45;
    private static final int ZERO = 0;
    private static final int LOTTO_BALLS_COUNT = 6;

    private static final List<LottoBall> LOTTO_NUMBERS = IntStream.rangeClosed(MIN_LOTTO_NUM, MAX_LOTTO_NUM)
            .boxed()
            .map(LottoBall::new)
            .collect(Collectors.toList());

    public LottoGroup generate() {
        Collections.shuffle(LOTTO_NUMBERS);
        return new LottoGroup(LOTTO_NUMBERS.subList(ZERO, LOTTO_BALLS_COUNT));
    }
}
