package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Hit implements PlayerState {
	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public PlayerState keepContinue(boolean input) {
		if (!input) {
			return new Stay();
		}
		return this;
	}

	@Override
	public PlayerState checkStateWithNewCard(Cards cards) {
		if (cards.calculateIncludeAce() > 21) {
			return new Burst();
		}
		return this;
	}
}
