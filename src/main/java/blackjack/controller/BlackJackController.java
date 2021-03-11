package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {
	public static final int INITIAL_DRAWING_COUNT = 2;

	public void run() {
		Dealer dealer = new Dealer();
		Players players = askPlayers(dealer);
		Deck deck = new Deck();

		deck.shuffleCards();
		drawCards(dealer, players, deck);
		drawUntilPossible(dealer, players, deck);

		OutputView.noticePlayersPoint(dealer, players);
		OutputView.printDealerResult(players.calculateTotalWinnings(dealer));
	}

	private Players askPlayers(Dealer dealer) {
		try {
			return new Players(InputView.enterNames(), dealer);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return askPlayers(dealer);
		}
	}

	private void drawCards(Dealer dealer, Players players, Deck deck) {
		OutputView.noticeDrawTwoCards(players);
		players.initialCards(deck);
		OutputView.noticePlayersCards(dealer, players);
	}

	private void drawUntilPossible(Dealer dealer, Players players, Deck deck) {
		for (Player player : players.toList()) {
			askKeepDrawing(player, deck);
		}
		while (dealer.canReceiveCard()) {
			dealer.receiveCard(deck.dealCard());
			OutputView.noticeDealerReceiveCard();
		}
	}

	private void askKeepDrawing(Player player, Deck deck) {
		try {
			playEachPlayer(player, deck);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			playEachPlayer(player, deck);
		}
	}

	private void playEachPlayer(Player player, Deck deck) {
		while (player.canReceiveCard() && player.continueDraw(InputView.isContinueDraw(player), deck)) {
			player.receiveCard(deck.dealCard());
			OutputView.noticePlayerCards(player);
		}
	}
}
