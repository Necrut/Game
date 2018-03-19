import java.util.ArrayList;
import java.util.Scanner;

public class Game {


    public static void main(String[] args) {
        // loome kaardid tegelaste jaoks, tugevamad kaardid bosside jaoks
        Card attack = new Card("Attack", 20, 0, 0, 0, 0);
        Card block = new Card("Block", 0, 10, 0, 0, 0);
        Card cleanse = new Card("Cleanse", 0, 0, 2, 0, 0);
        Card heal = new Card("Heal", 0, 0, 0, 15, 0);
        Card trollAttack = new Card("Attack", 30, 0, 0, 0, 2);
        Card trollBlock = new Card("Block", 0, 30, 0, 0, 0);
        Card trollMadness = new Card("Madness", 0, 0, 0, 0, 4);
        ArrayList<Card> playerCards = new ArrayList<>(); // teeme tühjad listid pakkide jaoks
        ArrayList<Card> trollCards = new ArrayList<>();
        ArrayList<Card> necroCards = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            playerCards.add(attack); // mängija pakki lisatakse 7 attack'i
            playerCards.add(block); // mängija pakki lisatakse 7 block'i
            if (i < 6) {
                playerCards.add(cleanse); // mängija pakki lisatakse 6 cleanse'i
            }
            if (i < 2) {
                trollCards.add(trollAttack); // bossi pakki lisatakse 2 trollAttack'i
            }
            if (i < 1) { // bossi pakki lisatakse 1 trollBlock ja 1 trollMadness
                trollCards.add(trollBlock);
                trollCards.add(trollMadness);
            }
        }
        Deck playerDeck = new Deck(playerCards); // listist playerCards tehakse deck
        Deck trollDeck = new Deck(trollCards); // listist trollCards tehakse deck
        Deck necroDeck = new Deck(necroCards);

        Character player = new Character(100, 0, 0, playerDeck, true); // deck playerCards saab mängijale
        Character troll = new Character(200, 0, 0, trollDeck, false); // deck trollCards saab bossile
        Character necromancer = new Character(100, 0, 0, necroDeck, false);

        player.getDrawPile().shuffle(); // segame mängija playerCards

// MAIN PLAY LOOP
        Scanner reader = new Scanner(System.in);
        boolean gameOn = true;
        boolean firstTurn = true;
        while (gameOn) {
            if (firstTurn == true) {
                System.out.println("You are battling the great scary mountain troll Gorganon! Enter \"pass\" to end your turn. Enter \"exit\" to exit.\n");
                System.out.println("----YOU----\n" + player);
                System.out.println("--GORGANON--\n" + troll);
                player.draw(5); // esimesel käigul tõmbab mängija erandlikult 5 kaarti
                firstTurn = false;
                troll.draw(1); // bossil on alati 1 kaart, ta mängib selle alati
            } else {
                if (troll.seeDrawPile().size() < 1) { // kui bossi drawPile's on alla 1 kaardi
                    for (int i = 0; i < troll.seeDiscardPile().size(); i++) { // ja lisame discardPile'st kaardid drawPile'sse
                        troll.seeDrawPile().add(troll.seeDiscardPile().get(0));
                        troll.seeDiscardPile().remove(0); // teeme discardPile'i tühjaks
                    }
                }
                if (player.seeDrawPile().size() < 3) { // kui mängija drawPile's on alla 3 kaardi
                    player.getDiscardPile().shuffle(); // segame discardPile'i
                    for (int i = 0; i < player.seeDiscardPile().size(); i++) { // ja lisame discardPile'st kaardid drawPile'sse
                        player.seeDrawPile().add(player.seeDiscardPile().get(0));
                        player.seeDiscardPile().remove(0); // teeme discardPile'i tühjaks
                    }
                }
            }
            System.out.println("Your opponent will use: " + troll.getHand());
            System.out.println("Select your course of action - enter three different card numbers."); // teade iga käigu alguses
            for (int i = 0; i < 3; i++) { // võtab mängijalt 3 sisendit (tavaliselt kaardinumbrid)
                if (player.getHealth() <= 0) {
                    gameOn = false;
                    System.out.println("You died!\nGame over.");
                    break;
                }
                if (player.getMadness() >= 10) {
                    gameOn = false;
                    System.out.println("You went mad!\nGame over.");
                    break;
                }
                if (troll.getHealth() <= 0) {
                    gameOn = false;
                    System.out.println("You defeated Gorganon! Congratulations!");
                    break;
                }
                System.out.println(player.getHand()); // trükib käe välja
                //int kaart = reader.nextInt(); // kuulame, mis number meile antakse
                String command = reader.next(); // kuulame, mis sisend
                if (command.toLowerCase().equals("exit")) { // kui saame "exit", siis lõpetame mängu
                    gameOn = false;
                    i = 3;
                    System.out.println("Thanks for playing!");
                } else {
                    if (command.toLowerCase().equals("pass")) {
                        i = 3;
                    } // kui saame "pass", siis anname käigu bossile edasi
                    else if (0 < Integer.parseInt(command) && Integer.parseInt(command) < 21 &&
                            Integer.parseInt(command) <= player.getHand().size()) { // saame kaardinumbri
                        player.play(Integer.parseInt(command), player, troll);
                        System.out.println("----YOU----\n" + player);
                        System.out.println("--GORGANON--\n" + troll);
                    }
                    else {
                        System.out.println("You don't have that many cards, try again.\n");
                        i -= 1;
                    }
                }
            }
            troll.setShield(0);
            if (gameOn == true) {
                troll.play(1, troll, player);
                player.setShield(0);
                System.out.println("----YOU----\n" + player);
                System.out.println("--GORGANON--\n" + troll);
                if (player.getHealth() <= 0) {
                    gameOn = false;
                    System.out.println("You died!\nGame over.");
                }
                if (player.getMadness() >= 10) {
                    gameOn = false;
                    System.out.println("You went mad!\nGame over.");
                }
                if (troll.getHealth() <= 0) {
                    gameOn = false;
                    System.out.println("You defeated Gorganon! Congratulations!");
                }
                player.draw(3);
                troll.draw(1);
            }
        }
        reader.close();
    }
}
// mängu lõppu pole veel sisse programmeeritud
