package lotto.dto;

import lotto.model.LottoGroup;

import java.util.List;
import java.util.stream.Collectors;

public class LottoGroupDTOs {

    private final List<LottoGroupDTO> dtos;

    public LottoGroupDTOs(final List<LottoGroup> lottoGroups) {
        this.dtos = lottoGroups.stream()
                .map(LottoGroupDTO::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<LottoGroupDTO> getDtos() {
        return dtos;
    }
}
