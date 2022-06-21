package Soldier;
import Modifier.*;
import java.util.ArrayList;

public class Soldier {
    private String name;
    private String attackType;
    private int points;
    private int maxPoints;
    private int attackPower;
    private int meleeDefense;
    private int rangedDefense;
    private ArrayList<String> tags;
    private ArrayList<Modifier> modificators;

    public Soldier(String name, String attackType, int points, int attackPower, int meleeDefense, int rangedDefense) {
        this.name = name;
        this.attackType = attackType;
        this.points = points;
        this.attackPower = attackPower;
        this.meleeDefense = meleeDefense;
        this.rangedDefense = rangedDefense;
    }

    public Soldier(String name, String attackType, int points, int maxPoints, int attackPower, int meleeDefense, int rangedDefense, ArrayList<String> tags, ArrayList<Modifier> modificators) {
        this.name = name;
        this.attackType = attackType;
        this.points = points;
        this.maxPoints = maxPoints;
        this.attackPower = attackPower;
        this.meleeDefense = meleeDefense;
        this.rangedDefense = rangedDefense;
        this.tags = tags;
        this.modificators = modificators;
    }

    //getters
    public int getPoints() {
        return points;
    } // za testvane
    public String getName() {return name;} // za testvane
    public ArrayList<Modifier> getMods(){
        return modificators;
    } // za testvane
    public ArrayList<String> getTags() {
        return tags;
    }

    //setters
    public void setPoints(int points) {
        this.points = points;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void setMeleeDefense(int meleeDefense) {
        this.meleeDefense = meleeDefense;
    }

    public void setRangedDefense(int rangedDefense) {
        this.rangedDefense = rangedDefense;
    }

    public void addModifier(Modifier m){
        modificators.add(m);
        m.change(this);
    }

    public int getDamageAgainst(Soldier enemy){
        int damage = this.attackPower;
        for(Modifier m : modificators){
            if(m.getType() != "ak"){
                continue;
            }else {
                damage += m.battle(this, enemy);
            }
        }
        return damage;
    }

    public int getDamageFrom(Soldier enemy, int value, String type){
        int damage = value;
        if(type == "melee"){
            damage -= meleeDefense;
        }
        if(type == "ranged"){
            damage -= rangedDefense;
        }
        for(Modifier m : modificators){
            if(m.getType() != "d"){
                continue;
            }else{
                damage -= m.battle(this, enemy);
            }
        }
        if(damage <= 0) return 1;
        return damage;
    }

    public void receiveAttack(Soldier enemy, int value, String type){
        points -= getDamageFrom(enemy, value, type);
        if(points <= 0 ){
            System.out.println("GAME OVER.");
        }else{
            System.out.println("soldier points are " + points);
        }
    }

    public Soldier chooseTarget(ArrayList<Soldier> enemies){
        int maxDamage = 0;
        int currentDamage;
        for(Soldier enemy : enemies){
            currentDamage = getDamageAgainst(enemy);
            if(maxDamage < currentDamage){
                maxDamage = currentDamage;
            }
        }
        for(Soldier enemy : enemies){
            if(maxDamage == getDamageAgainst(enemy)){
                return enemy;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        int attackSum = 0;
        for(Modifier m :modificators){
            if(m.getType() != "at"){
                continue;
            }else{
                attackSum += m.getValue();
            }
        }

        int defenseSum = 0;
        for(Modifier m :modificators){
            if(m.getType() != "d"){
                continue;
            }else{
                defenseSum += m.getValue();
            }
        }
        String str = name + "\nHP: " + points + "/" + maxPoints +
                "\nATK: " + attackPower + "/" +  attackSum +
                "\nDEF: " + meleeDefense + " + " + rangedDefense  + "/" +  defenseSum +
                "\nModifiers: \n";
        for(Modifier m : modificators){
            str += m.description();
            str += "\n";
        }
        return str;
    }
}