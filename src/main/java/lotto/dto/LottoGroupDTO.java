package lotto.dto;

import lotto.model.LottoGroup;

import java.util.List;
import java.util.stream.Collectors;

public class LottoGroupDTO {

    private final List<Integer> numbers;

    public LottoGroupDTO(final LottoGroup lottoGroup) {
        this.numbers = lottoGroup.getLottoBallNumbers()
                .stream()
                .sorted()
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
