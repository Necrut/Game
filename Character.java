import java.util.ArrayList;

public class Character {

    private int health;
    private int shield;
    private int madness;
    private ArrayList<Card> hand;
    private Deck drawPile;
    private Deck discardPile;
    private boolean isPlayer;

    public Character(int health, int shield, int madness, Deck drawPile, boolean isPlayer) {
        this.health = health;
        this.shield = shield;
        this.madness = madness;
        this.drawPile = drawPile;
        this.hand = new ArrayList<>();
        this.isPlayer = isPlayer;
        ArrayList discardPile = new ArrayList<Card>();
        this.discardPile = new Deck(discardPile);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getMadness() {
        return madness;
    }

    public void setMadness(int madness) {
        this.madness = madness;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setDrawPile(Deck drawPile) {
        this.drawPile = drawPile;
    }

    public ArrayList<Card> seeDrawPile() {
        return drawPile.getDeck();
    }

    public Deck getDrawPile(){
        return drawPile;
    }

    public ArrayList<Card> seeDiscardPile() {
        return discardPile.getDeck();
    }

    public Deck getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(Deck discardPile) {
        this.discardPile = discardPile;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void draw(int drawSize){
        for(int i = 0; i < drawSize; i++) {
            this.hand.add((drawPile.getDeck()).get(i));
            seeDrawPile().remove(i);
        }
    }


    public String toString(){
        StringBuilder characterUI = new StringBuilder();
        String healthBar = "";
        String shieldBar = "";
        String madnessBar = "";
        if (health >= 0){
            if (0 <= health && health < 10){
                healthBar = "[       " + health + "     ]";
            }
            else if (10 <= health && health < 100){
                healthBar = "[      " + health + "     ]";
            }
            else if (100 <= health){
                healthBar = "[     " + health + "     ]";
            }
            characterUI.append(healthBar);
            characterUI.append("\n");
        }
        if (shield >= 0) {
            if (0 <= shield && shield < 10) {
                shieldBar = "[       " + shield + "     ]";
            } else if (10 <= shield && shield < 100) {
                shieldBar = "[      " + shield + "     ]";
            } else if (100 <= shield) {
                shieldBar = "[     " + shield + "     ]";
            }
            characterUI.append(shieldBar);
            characterUI.append("\n");
        }
        if (isPlayer){
            if (0 <= madness && madness < 10) {
                madnessBar = "[       " + madness + "     ]";
            }
            else if (madness == 10) {
                madnessBar = "[      " + madness + "     ]";
            }
            characterUI.append(madnessBar);
            characterUI.append("\n");
        }
        String characterBars = characterUI.toString();
        return characterBars;
    }

    public void play(int kaardiNr, Character self, Character target){
        int handSize = hand.size();
        for (int i = 0; i < handSize; i++){
            if (kaardiNr == i+1) {
                Card kaart = hand.get(i);
                if (kaart.getAttack() > 0) {
                    if (target.getShield() > 0){
                        if (target.getShield() < kaart.getAttack()) {
                            kaart.setAttack(kaart.getAttack() - target.getShield());
                            target.setShield(0);
                        } else {
                            target.setShield(target.getShield() - kaart.getAttack());
                            kaart.setAttack(0);
                        }
                    }
                    target.setHealth(target.getHealth() - kaart.getAttack());

                } if (kaart.getBlock() > 0) {
                    self.setShield(self.getShield() + kaart.getBlock());
                } if (kaart.getHeal() > 0) {
                    self.setHealth(self.getHealth() + kaart.getHeal());
                } if (kaart.getMadness() > 0) {
                    target.setMadness(target.getMadness() + kaart.getMadness());
                }
                this.seeDiscardPile().add(hand.get(i));
                getHand().remove(i);
            }
        }
    }

}
