public class Card {

    private int attack;
    private int block;
    private int cleanse;
    private int heal;
    private int madness;

    public Card(int attack, int block, int cleanse, int heal, int madness) {
        this.attack = attack;
        this.block = block;
        this.cleanse = cleanse;
        this.heal = heal;
        this.madness = madness;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getCleanse() {
        return cleanse;
    }

    public void setCleanse(int cleanse) {
        this.cleanse = cleanse;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getMadness() {
        return madness;
    }

    public void setMadness(int madness) {
        this.madness = madness;
    }

    public String toString(){
        String kaart = "";
        if (attack > 0){
            kaart = "Attack";
        }
        else if (block > 0){
            kaart = "Block";
        }
        else if (cleanse > 0){
            kaart = "Cleanse";
        }
        else if (heal > 0){
            kaart = "Heal";
        }
        else if (madness > 0){
            kaart = "Madness";
        }
        return kaart;
    }

}
