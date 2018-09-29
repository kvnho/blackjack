import java.util.Scanner;

public class Blackjack {

    private Deck mainDeck;
    private Deck dealerHand;
    private Deck playerHand;
    private boolean gameOver;

    public static void main(String[] args) {
        Blackjack blackjack = new Blackjack();
        blackjack.start();
    }


    public void start() {
        deal();
        checkWin();
        while (!gameOver) {
            switch (gameMenu()) {
                case 1:
                    hit();
                    break;
                case 2:
                    stand();
                    break;
            }
        }

        if(gameOver) {
            printNewLines();
            switch (mainMenu()) {
                case 1:
                    start();
                    break;
                case 2:
                    break;
            }
        }
    }

    public int mainMenu() {
        Scanner sc = new Scanner(System.in);
        printMainMenu();
        return sc.nextInt();
    }

    public int gameMenu() {
        Scanner sc = new Scanner(System.in);
        printGameMenu();
        return sc.nextInt();
    }

    public void deal() {
        mainDeck = new Deck();
        dealerHand = new Deck();
        playerHand = new Deck();
        gameOver = false;

        mainDeck.newDeck();
        playerHand.draw(mainDeck);
        dealerHand.draw(mainDeck);
        playerHand.draw(mainDeck);
        dealerHand.draw(mainDeck);

        printNewLines();
        printPlayerHand();
        printDealerHiddenHand();
    }

    public void hit() {
        printNewLines();

        playerHand.draw(mainDeck);
        printPlayerHand();

        printNewLines();

        if(playerHand.handValue() == 21) {
            printPlayerWins();
            printNewLines();
            gameOver = true;
        }
        else if(playerHand.handValue() > 21) {
            printPlayerBust();
            printDealerWins();
            printNewLines();
            gameOver = true;
        }
    }

    public void stand() {
        printNewLines();
        printDealerHand();

        if(dealerHand.handValue() < playerHand.handValue()) {
            while (dealerHand.handValue() < 17 || dealerHand.handValue() < playerHand.handValue()) {
                printNewLines();
                printDealerHits();

                dealerHand.draw(mainDeck);

                printDealerHand();
            }
        }
        if(dealerHand.handValue() > 21) {
            printNewLines();
            printDealerBust();
            printPlayerWins();

            gameOver = true;
        }
        else if(dealerHand.handValue() == playerHand.handValue()){
            printDraw();
        }
        else{
            printNewLines();
            printDealerWins();

            gameOver = true;
        }

    }

    public void checkWin() {
        if(playerHand.handValue() == 21 && dealerHand.handValue() == 21) {
            printDraw();
            gameOver = true;
        }
        else if(playerHand.handValue() == 21) {
            printPlayerWins();
            gameOver = true;
        }

        printNewLines();
    }

    public void printMainMenu() {
        System.out.println("================= BLACKJACK MAIN MENU =================");
        System.out.println("[1] Deal New Game");
        System.out.println("[2] Quit");
    }

    public void printGameMenu() {
        System.out.println("================= BLACKJACK PLAY MENU =================");
        System.out.println("[1] Hit");
        System.out.println("[2] Stand");
    }

    public void printPlayerHand() {
        System.out.println("PLAYER'S HAND: " + playerHand.toString());
    }

    public void printDealerHand() {
        System.out.println("DEALERS'S HAND: " + dealerHand.toString());
    }

    public void printDealerHiddenHand() {
        System.out.print("DEALER'S HAND: [" + dealerHand.getCard() + "]" + "[HIDDEN]");
    }

    public void printDealerHits() {
        System.out.println("DEALER HITS");
    }

    public void printPlayerWins() {
        System.out.println("!!!!!!!!!!!!!! PLAYER WINS !!!!!!!!!!!!!!");
    }

    public void printDealerWins() {
        System.out.println("!!!!!!!!!!!!!! DEALER WINS !!!!!!!!!!!!!!");
    }

    public void printPlayerBust() {
        System.out.println("!!!!!!!!!!!!!! PLAYER BUST !!!!!!!!!!!!!!");
    }

    public void printDealerBust() {
        System.out.println("!!!!!!!!!!!!!! DEALER BUST !!!!!!!!!!!!!!");
    }

    public void printDraw(){
        System.out.println("!!!!!!!!!!!!!!!!! DRAW !!!!!!!!!!!!!!!!!");
    }

    public void printNewLines() {
        System.out.println("\n\n");
    }

}
