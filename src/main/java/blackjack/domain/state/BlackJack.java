package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class BlackJack implements PlayerState {
    @Override
    public PlayerState drawTwoCards(Cards cards) {
        return new BlackJack();
    }
}
