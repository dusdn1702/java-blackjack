package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Stay implements PlayerState {
    @Override
    public PlayerState drawTwoCards(Cards cards) {
        throw new IllegalArgumentException("옳지 않은 진행입니다.");
    }

}
