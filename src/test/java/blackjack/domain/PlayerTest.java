package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;
    @BeforeEach
    void setUp() {
        player = new Player("pobi");
    }

    @Test
    @DisplayName("이름에 공백 입력 경우 예외 처리")
    void playerNameSplitException() {
        String input = "pobi, jason";
        assertThatThrownBy(() -> {
            for (String name : input.split(",")) {
                new Player(name);
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkReceiveCard() {
        Card card = new Card(CardPattern.CLOVER, CardNumber.TEN);
        player.receiveCard(card);
        assertEquals(player.calculateMinimumPoint(), 10);
    }


    @Test
    @DisplayName("플레이어가 카드를 받을 수 있는지 확인")
    void playerPossibleReceiveCard() {
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.KING));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.KING));
        assertTrue(player.canReceiveCard());
    }

    @Test
    @DisplayName("플레이어가 카드를 받을 수 없는지 확인")
    void playerImpossibleReceiveCard() {
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.KING));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.KING));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.TWO));
        assertFalse(player.canReceiveCard());
    }

}
