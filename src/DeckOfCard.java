import java.util.Random;

class DeckOfCards {
    private String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private String[] deck = new String[52];

    public DeckOfCards() {
        initializeDeck();
    }

    // Initialize the deck with all combinations of suits and ranks
    private void initializeDeck() {
        int index = 0;
        for (String suit : suits) {
            for (String rank : ranks) {
                deck[index++] = rank + " of " + suit;
            }
        }
    }

    // Shuffle the deck using Random
    public void shuffleDeck() {
        Random random = new Random();
        for (int i = 0; i < deck.length; i++) {
            int randomIndex = random.nextInt(deck.length);
            String temp = deck[i];
            deck[i] = deck[randomIndex];
            deck[randomIndex] = temp;
        }
    }

    // Distribute cards to players
    public String[][] distributeCards(int players, int cardsPerPlayer) {
        String[][] distributedCards = new String[players][cardsPerPlayer];
        int cardIndex = 0;

        for (int i = 0; i < players; i++) {
            for (int j = 0; j < cardsPerPlayer; j++) {
                if (cardIndex < deck.length) {
                    distributedCards[i][j] = deck[cardIndex++];
                }
            }
        }
        return distributedCards;
    }

    public void printDistributedCards(String[][] distributedCards) {
        for (int i = 0; i < distributedCards.length; i++) {
            System.out.println("Player " + (i + 1) + "'s cards:");
            for (int j = 0; j < distributedCards[i].length; j++) {
                System.out.println(distributedCards[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        DeckOfCards deckOfCards = new DeckOfCards();
        deckOfCards.shuffleDeck();

        int players = 4;
        int cardsPerPlayer = 9;

        String[][] distributedCards = deckOfCards.distributeCards(players, cardsPerPlayer);
        deckOfCards.printDistributedCards(distributedCards);
    }
}
