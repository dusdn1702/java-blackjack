package blackjack.domain.state;

import blackjack.domain.card.Cards;

public interface PlayerState {
    PlayerState drawTwoCards(Cards cards);
//    Score score(Cards cards);
}
