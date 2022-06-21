package Main;
import Modifier.*;
import Soldier.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Modifier> modificators =  new ArrayList<>();
        ArrayList<String> tags = new ArrayList<>();
        Soldier s = new Soldier("Kevin", "melee", 0,0,0, 3,0,tags, modificators );

        // tall redhead wizard enemy
        ArrayList<Modifier> enemyModificators =  new ArrayList<>();
        ArrayList<String> enemyTags = new ArrayList<>();
        enemyTags.add("redhead");
        enemyTags.add("wizard");
        enemyTags.add("tall");
        Soldier e = new Soldier("Bob", "melee", 0,0,0,0,0, enemyTags, enemyModificators);

        // test attribute mod
        AttributeMod am = new AttributeMod("points", 10);
        System.out.println("points before " + s.getPoints()); // 0
        s.addModifier(am);
        System.out.println("points after " + s.getPoints()); // 10

        //test attack mod
        AttackMod atmBrunette = new AttackMod("brunette", 9);
        System.out.println("plus attack points against brunette: " + atmBrunette.battle(s,e)); // 0
        AttackMod atmWizard = new AttackMod("wizard", 7);
        System.out.println("plus attack points against wizard: " + atmWizard.battle(s,e)); // 7

        // test defense mod
        DefenseMod dmBrunette = new DefenseMod("brunette", 9);
        System.out.println("plus defense points against brunette: " + dmBrunette.battle(s,e)); // 0
        DefenseMod dmTall = new DefenseMod("tall", 11);
        System.out.println("plus defense points against tall: " + dmTall.battle(s,e)); // 11

        // damage from
        s.addModifier(dmBrunette);
        s.addModifier(dmTall);
        System.out.println( "Damage from enemy is " + s.getDamageFrom(e, 60, "melee")); // 46
        // 60 - 3 (melee defense) - 11 (dmTall) = 46
        System.out.println("(negative) Damage from enemy is " + s.getDamageFrom(e, 4, "melee")); // 1;
        // 4 - 3 - 11 < 0 => 1

        // damage against
        s.addModifier(atmBrunette);
        System.out.println("Damage against enemy (not valid mod) is " + s.getDamageAgainst(e)); // 0
        s.addModifier(atmWizard);
        System.out.println("Damage against enemy (valid tall tag) is " + s.getDamageAgainst(e)); // 7

        // choose target
        ArrayList<Modifier> enemyModificators2 =  new ArrayList<>();
        ArrayList<String> enemyTags2 = new ArrayList<>();
        enemyTags2.add("brunette");
        Soldier e2 = new Soldier("Maleficent", "ranged", 25, 50, 8, 5,7, enemyTags2, enemyModificators2);

        ArrayList<Soldier> enemies = new ArrayList<>();
        enemies.add(e);
        enemies.add(e2);
        // damage against Bob is 7 because he is wizard (atmWizard)
        // damage against Maleficent is 9 because she is brunette (atmBrunette)
        Soldier chosenEnemy = s.chooseTarget(enemies);
        System.out.println(chosenEnemy.equals(e2)); // checking is the func returns the true enemy

        //toString
        System.out.println(s.toString());

        // receive attack
        s.receiveAttack(e, 4, "melee"); // damage 1 so "soldier points are 9"
        s.receiveAttack(e, 40, "melee"); // damage 26 so "game over"
    }
}
