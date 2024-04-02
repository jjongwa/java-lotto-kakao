package lotto.dto;

import lotto.model.LottoGroup;

import java.util.List;
import java.util.stream.Collectors;

public class LottoGroupDTOs {

    private final List<LottoGroupDTO> values;

    public LottoGroupDTOs(final List<LottoGroup> lottoGroups) {
        this.values = lottoGroups.stream()
                .map(LottoGroupDTO::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<LottoGroupDTO> getValues() {
        return values;
    }
}
