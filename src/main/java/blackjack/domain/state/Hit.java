package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.GameManager;
import blackjack.domain.participant.Player;

public class Hit implements PlayerState{
    @Override
    public PlayerState drawTwoCards(Cards cards) {
        return new Hit();
    }

    public PlayerState drawNewCard(Cards cards, Card card, Player player) {
        cards.addCard(card);
        if(cards.calculateJudgingPoint() > 21) {
            return new Burst();
        }
        if(player.) {
            return new Stay();
        }
        return this;
    }
}
