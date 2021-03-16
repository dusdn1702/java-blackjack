package blackjack.controller;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {
	public static final String ERROR_MESSAGE_CALL = "옳지 않은 곳에서 호츨";
	public static final String ERROR_MESSAGE_INPUT = "옳지 않은 입력입니다.";

	public void run() {
		Dealer dealer = new Dealer();
		Players players = createPlayersWithMoney(askPlayers(), dealer);
		Deck deck = new Deck(new DeckGenerator().makeCards());

		playTurn(dealer, players, deck);

		OutputView.noticePlayersPoint(dealer, players);
		OutputView.printDealerResult(players.calculateProfits(dealer));
		OutputView.printPlayersResult(players);
	}

	private Players createPlayersWithMoney(List<String> names, Dealer dealer) {
		try {
			return makePlayers(names, dealer);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return makePlayers(names, dealer);
		}
	}

	private List<String> askPlayers() {
		try {
			return InputView.enterNames();
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return askPlayers();
		}
	}

	private void playTurn(Dealer dealer, Players players, Deck deck) {
		drawCards(dealer, players, deck);
		drawUntilPossible(dealer, players, deck);
	}

	private Players makePlayers(List<String> playersName, Dealer dealer) {
		List<Player> players = new ArrayList<>();
		for (String name : playersName) {
			players.add(new Player(name, askMoney(name)));
		}
		return new Players(players, dealer);
	}

	private int askMoney(String name) {
		try {
			return Integer.parseInt(InputView.enterBetting(name));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ERROR_MESSAGE_INPUT);
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
		while (dealer.canReceiveCard(true)) {
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
		while (!player.getPlayerState().isFinished()) {
			boolean keepPlaying = player.canReceiveCard(InputView.isContinueDraw(player));
			drawMoreCard(player, deck, keepPlaying);
		}
	}

	private void drawMoreCard(Player player, Deck deck, boolean keepPlaying) {
		if (keepPlaying) {
			player.receiveCard(deck.dealCard());
			OutputView.noticePlayerCards(player);
		}
	}
}
