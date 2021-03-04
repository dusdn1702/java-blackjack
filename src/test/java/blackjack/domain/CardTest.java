package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {
    @Test
    @DisplayName("에이스 존재 테스트")
    void containAce() {
        Card card = new Card(CardPattern.CLOVER, CardNumber.ACE);
        assertTrue(card.isAce());
    }
}
