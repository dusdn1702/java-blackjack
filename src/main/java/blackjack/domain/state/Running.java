package blackjack.domain.state;

import static blackjack.controller.BlackJackController.*;

import java.util.stream.Stream;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;

public abstract class Running implements PlayerState {
	protected Cards cards;

	public Running(Cards cards) {
		this.cards = cards;
	}

	@Override
	public int calculatePoint() {
		return cards.calculateJudgingPoint();
	}

	@Override
	public double makeProfit(Dealer dealer, Money money) {
		throw new IllegalArgumentException(ERROR_MESSAGE_CALL);
	}

	@Override
	public Stream<Card> getCardStream() {
		return cards.getCardStream();
	}
}
