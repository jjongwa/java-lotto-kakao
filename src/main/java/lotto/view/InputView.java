package lotto.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_PURCHASE_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNER_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final int ZERO = 0;

    private InputView() {
    }

    public static int readPurchaseMoney() {
        System.out.println(INPUT_PURCHASE_MONEY_MESSAGE);
        final int moneyInput = SCANNER.nextInt();
        SCANNER.nextLine();
        System.out.println();
        return moneyInput;
    }

    public static int readManualPurchaseCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        final int manualCountInput = SCANNER.nextInt();
        SCANNER.nextLine();
        System.out.println();
        return manualCountInput;
    }

    public static List<String> readManualLottoNumbers(final int manualCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        final List<String> inputManualLottoNumbers = IntStream.range(ZERO, manualCount)
                .mapToObj(it -> SCANNER.nextLine())
                .collect(Collectors.toUnmodifiableList());
        System.out.println();
        return inputManualLottoNumbers;
    }

    public static String readWinningGroup() {
        System.out.println(INPUT_WINNER_NUMBER_MESSAGE);
        return SCANNER.nextLine();
    }

    public static int readBonusBall() {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        return SCANNER.nextInt();
    }
}
