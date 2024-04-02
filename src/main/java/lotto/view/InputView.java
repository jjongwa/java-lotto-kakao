package lotto.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_PURCHASE_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNER_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";

    private InputView() {
    }

    public static int readPurchaseMoney() {
        System.out.println(INPUT_PURCHASE_MONEY_MESSAGE);
        return SCANNER.nextInt();
    }

    public static String readWinningGroup() {
        SCANNER.nextLine();
        System.out.println(INPUT_WINNER_NUMBER_MESSAGE);
        return SCANNER.nextLine();
    }

    public static int readBonusBall() {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        return SCANNER.nextInt();
    }
}
