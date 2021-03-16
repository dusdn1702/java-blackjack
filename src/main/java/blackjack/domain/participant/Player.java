package blackjack.domain.participant;

import blackjack.domain.state.PlayerState;

public class Player extends Gamer {
	private final Money money;

	public Player(String name, double money) {
		super(name);
		this.money = Money.of(money);
	}

	public int calculateProfit(PlayerState dealerState) {
		return (int)playerState.makeProfit(dealerState, money);
	}

	@Override
	public boolean canReceiveCard(boolean drawFlag) {
		playerState = playerState.keepContinue(drawFlag);
		return !playerState.isFinished();
	}

	public int getMoney() {
		return money.getMoney();
	}
}
