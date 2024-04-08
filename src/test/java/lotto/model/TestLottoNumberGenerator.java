package lotto.model;

import lotto.model.vo.LottoBall;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TestLottoNumberGenerator implements LottoNumberSelector {

    private final LinkedList<LottoGroup> lottoGroups;

    @SafeVarargs
    public TestLottoNumberGenerator(final List<Integer>... groupNumbers) {
        this.lottoGroups = new LinkedList<>(Arrays.stream(groupNumbers)
                .map(numbers -> new LottoGroup(numbers.stream()
                        .map(LottoBall::new)
                        .collect(Collectors.toUnmodifiableList()))
                ).collect(Collectors.toUnmodifiableList())
        );
    }

    @Override
    public LottoGroup select(final List<LottoBall> lottoBalls) {
        return lottoGroups.remove();
    }
}
