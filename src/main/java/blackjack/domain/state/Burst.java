package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Burst implements PlayerState{
    @Override
    public PlayerState drawTwoCards(Cards cards) {
        throw new IllegalArgumentException("옳지 않은 동작");
    }
}
