//public class DeckOfCard_UC2 {
//}


import java.util.Random;

class DeckOfCard_UC2 {
    private String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private String[] deck = new String[52];

    public DeckOfCard_UC2() {
        initializeDeck();
    }

    private void initializeDeck() {
        int index = 0;
        for (String suit : suits) {
            for (String rank : ranks) {
                deck[index++] = rank + " of " + suit;
            }
        }
    }

    public void shuffleDeck() {
        Random random = new Random();
        for (int i = 0; i < deck.length; i++) {
            int randomIndex = random.nextInt(deck.length);
            String temp = deck[i];
            deck[i] = deck[randomIndex];
            deck[randomIndex] = temp;
        }
    }

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
        DeckOfCard_UC2 DeckOfCard_UC2 = new DeckOfCard_UC2();
        DeckOfCard_UC2.shuffleDeck();

        int players = 4;
        int cardsPerPlayer = 9;

        String[][] distributedCards = DeckOfCard_UC2.distributeCards(players, cardsPerPlayer);

        PlayerQueue playerQueue = new PlayerQueue();
        for (int i = 0; i < players; i++) {
            Player player = new Player("Player " + (i + 1));
            player.setDeck(distributedCards[i]);
            player.sortDeck();
            playerQueue.enqueue(player);
        }

        playerQueue.printPlayersAndCards();
    }
}

class Player {
    private String name;
    private String[] deck;
    private CardQueue cardQueue = new CardQueue();

    public Player(String name) {
        this.name = name;
    }

    public void setDeck(String[] deck) {
        this.deck = deck;
        for (String card : deck) {
            cardQueue.enqueue(card);
        }
    }

    public void sortDeck() {
        for (int i = 0; i < deck.length - 1; i++) {
            for (int j = 0; j < deck.length - i - 1; j++) {
                if (getRankIndex(deck[j]) > getRankIndex(deck[j + 1])) {
                    String temp = deck[j];
                    deck[j] = deck[j + 1];
                    deck[j + 1] = temp;
                }
            }
        }

        cardQueue.clear();
        for (String card : deck) {
            cardQueue.enqueue(card);
        }
    }

    private int getRankIndex(String card) {
        String rank = card.split(" of ")[0];
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        for (int i = 0; i < ranks.length; i++) {
            if (ranks[i].equals(rank)) {
                return i;
            }
        }
        return -1;
    }

    public String getName() {
        return name;
    }

    public void printDeck() {
        cardQueue.printQueue();
    }
}

class CardQueue {
    private Node front;
    private Node rear;

    private class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
        }
    }

    public void enqueue(String card) {
        Node newNode = new Node(card);
        if (rear != null) {
            rear.next = newNode;
        }
        rear = newNode;
        if (front == null) {
            front = rear;
        }
    }

    public void printQueue() {
        Node current = front;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public void clear() {
        front = null;
        rear = null;
    }
}

class PlayerQueue {
    private Node front;
    private Node rear;

    private class Node {
        Player player;
        Node next;

        Node(Player player) {
            this.player = player;
        }
    }

    public void enqueue(Player player) {
        Node newNode = new Node(player);
        if (rear != null) {
            rear.next = newNode;
        }
        rear = newNode;
        if (front == null) {
            front = rear;
        }
    }

    public void printPlayersAndCards() {
        Node current = front;
        while (current != null) {
            System.out.println(current.player.getName() + "'s cards:");
            current.player.printDeck();
            System.out.println();
            current = current.next;
        }
    }
}
