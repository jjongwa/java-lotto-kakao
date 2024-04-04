package lotto.model;

import lotto.model.vo.PurchaseCount;
import lotto.model.vo.PurchaseMoney;

public class PurchaseCounts {

    private final PurchaseCount randomPurchaseCount;
    private final PurchaseCount manualPurchaseCount;

    public PurchaseCounts(final PurchaseMoney purchaseMoney, final PurchaseCount manualPurchaseCount) {
        this.randomPurchaseCount = purchaseMoney.subtractByManualCount(manualPurchaseCount);
        this.manualPurchaseCount = manualPurchaseCount;
    }

    public int getRandomPurchaseCount() {
        return randomPurchaseCount.getCount();
    }

    public int getManualPurchaseCount() {
        return manualPurchaseCount.getCount();
    }
}
