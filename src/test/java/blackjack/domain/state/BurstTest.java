package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BurstTest {
    @Test
    void checkBurst() {
        Cards cards = new Cards();
        Card firstCard = new Card(CardPattern.DIAMOND, CardNumber.TEN);
        Card secondCard = new Card(CardPattern.DIAMOND, CardNumber.THREE);
        cards.addCard(firstCard);
        cards.addCard(secondCard);
        Hit hit = new Hit();
        PlayerState state = hit.drawNewCard(cards, new Card(CardPattern.CLOVER, CardNumber.NINE));
        assertThat(state).isInstanceOf(Burst.class);
    }
}
