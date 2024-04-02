package lotto.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomLottoMachine implements LottoMachine {

    private final LinkedList<LottoGroup> lottoGroups;

    @SafeVarargs
    public CustomLottoMachine(final List<Integer>... groupNumbers) {
        this.lottoGroups = new LinkedList<>(Arrays.stream(groupNumbers)
                .map(LottoGroup::new)
                .collect(Collectors.toUnmodifiableList()));
    }

    @Override
    public LottoGroup generate() {
        return lottoGroups.remove();
    }
}
