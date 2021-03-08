package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.WinnerFlag;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Gamer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.Map;
import java.util.stream.Collectors;

import static blackjack.domain.game.GameManager.INITIAL_DRAWING_COUNT;
import static blackjack.domain.participant.Dealer.MAX_OF_RECEIVE_MORE_CARD;
import static blackjack.domain.participant.Dealer.NAME_OF_DEALER;
import static blackjack.domain.participant.Gamer.COMMA_DELIMITER;

public class OutputView {
    public static final String COLON_DELIMITER = ": ";
    public static final String RESULT_DELIMITER = " - 결과: ";
    private static final String COMMA_DELIMITER_TO_PRINT = ", ";
    private static final int COUNT_OF_DEALER_OPENING_CARDS = 1;
    private static final String NOTICE_DRAWING_CARDS = "%s와 %s에게 %d장씩 나누었습니다.\n";
    private static final String NOTICE_FINAL_RESULT = "## 최종 승패";
    private static final String NOTICE_DEALER_RECEIVE = "딜러는 %d이하라 한장의 카드를 더 받았습니다.\n";
    private static final String CARD_DELIMITER = "카드: ";

    public static void noticeDrawTwoCards(Players players) {
        System.out.println();
        System.out.printf(NOTICE_DRAWING_CARDS, players.getDealerName(), players.getPlayerNames(), INITIAL_DRAWING_COUNT);
    }

    public static void noticePlayersCards(Dealer dealer, Players players) {
        System.out.println(dealer.getName() + COLON_DELIMITER + makeDealerCardNames(dealer));
        for (Player player : players.toList()) {
            noticePlayerCards(player);
        }
        System.out.println();
    }

    public static void noticePlayersPoint(Dealer dealer, Players players) {
        System.out.println();
        System.out.println(dealer.getName() + COLON_DELIMITER + makePlayerCardNames(dealer) + RESULT_DELIMITER + dealer.makeMaximumPoint());
        for (Player player : players.toList()) {
            System.out.println(player.getName() + CARD_DELIMITER + makePlayerCardNames(player)
                    + RESULT_DELIMITER + player.makeMaximumPoint());
            WinnerFlag.calculateResult(dealer, player);
        }
    }

    private static String makePlayerCardNames(Gamer player) {
        return player.toList().stream()
                .map(Card::getPatternAndNumber)
                .collect(Collectors.joining(COMMA_DELIMITER_TO_PRINT));
    }

    private static String makeDealerCardNames(Dealer dealer) {
        return dealer.toList().stream()
                .limit(COUNT_OF_DEALER_OPENING_CARDS)
                .map(Card::getPatternAndNumber)
                .collect(Collectors.joining(COMMA_DELIMITER));
    }

    public static void noticeDealerReceiveCard() {
        System.out.printf(NOTICE_DEALER_RECEIVE, MAX_OF_RECEIVE_MORE_CARD);
    }

    public static void noticePlayerCards(Player player) {
        System.out.println(player.getName() + CARD_DELIMITER + makePlayerCardNames(player));
    }

    public static void printResult(Map<WinnerFlag, Integer> result) {
        System.out.println();
        System.out.println(NOTICE_FINAL_RESULT);
        System.out.println(NAME_OF_DEALER + COLON_DELIMITER
                + result.get(WinnerFlag.LOSE) + WinnerFlag.WIN.getFlagOutput()
                + result.get(WinnerFlag.DRAW) + WinnerFlag.DRAW.getFlagOutput()
                + result.get(WinnerFlag.WIN) + WinnerFlag.LOSE.getFlagOutput());
    }

    public static void printPlayerResult(Player player) {
        System.out.println(player.getName() + COLON_DELIMITER + player.getResult().getFlagOutput());
    }
}
