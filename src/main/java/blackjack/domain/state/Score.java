package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Score {
    private final int score;

    public Score(Cards cards) {
        this.score = cards.calculateJudgingPoint();
    }
}
