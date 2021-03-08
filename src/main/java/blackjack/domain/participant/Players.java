package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.game.WinnerFlag;

import java.util.*;

public class Players {
    private final List<Player> players;
    private final Gamer dealer;

    public Players(List<String> value, Dealer dealer) {
        this.players = createPlayers(value);
        this.dealer = dealer;
    }

    private List<Player> createPlayers(List<String> value) {
        List<Player> splitPlayers = new ArrayList<>();
        for (String name : value) {
            splitPlayers.add(new Player(name));
        }
        return splitPlayers;
    }

    public void giveCards(Deck deck) {
        dealer.receiveCard(deck.dealCard());
        for (Gamer gamer : players) {
            gamer.receiveCard(deck.dealCard());
        }
    }

    public Map<WinnerFlag, Integer> calculateTotalWinnings() {
        Map<WinnerFlag, Integer> winnerCount = new EnumMap<>(WinnerFlag.class);
        for (Player player : players) {
            winnerCount.put(player.getResult(), winnerCount.getOrDefault(player.getResult(), 0) + 1);
        }
        return winnerCount;
    }

    public List<Player> toList() {
        return players;
    }

    public String getDealerName() {
        return dealer.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players) && Objects.equals(dealer, players1.dealer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players, dealer);
    }
}
