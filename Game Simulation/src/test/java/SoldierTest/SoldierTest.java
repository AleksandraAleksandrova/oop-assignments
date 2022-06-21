package SoldierTest;
import Modifier.*;
import Soldier.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class SoldierTest {
    Soldier s;
    Soldier e;
    @BeforeEach
    void setUp(){
        // soldier
        ArrayList<Modifier> modificators =  new ArrayList<>();
        ArrayList<String> tags = new ArrayList<>();
        s = new Soldier("Kevin", "melee", 20,100,2, 3,4,tags, modificators );
        // enemy
        ArrayList<Modifier> enemyModificators =  new ArrayList<>();
        ArrayList<String> enemyTags = new ArrayList<>();
        enemyTags.add("redhead");
        enemyTags.add("wizard");
        enemyTags.add("tall");
        e = new Soldier("Victor", "melee", 30,100,5,3,4, enemyTags, enemyModificators);
    }
    @AfterEach
    void tearDown(){
        s = null;
        e = null;
    }

    @Test
    void constructorTest() {;
        Soldier s = new Soldier("Kevin", "melee", 0,0,0,0);
        assertEquals(0, s.getPoints());
        assertEquals("Kevin", s.getName());
    }

    @Test
    void addModifierTest() {
        ArrayList<Modifier> modificators =  new ArrayList<>();
        assertEquals(modificators, s.getMods());
    }
    @Test
    void attributeSetTest() {
        AttributeMod am = new AttributeMod("points", 80);
        s.addModifier(am);
        assertEquals(80, s.getPoints());
    }

    @Test
    void attackModTest() {
        AttackMod atmBrunette = new AttackMod("brunette", 7);
        assertEquals(0, atmBrunette.battle(s,e));
        AttackMod atmWizard = new AttackMod("wizard", 7);
        assertEquals(7, atmWizard.battle(s,e));
    }

    @Test
    void defenseModTest() {
        DefenseMod dmBrunette = new DefenseMod("brunette", 11);
        assertEquals(0, dmBrunette.battle(s,e));
        DefenseMod dmTall = new DefenseMod("tall", 11);
        assertEquals(11, dmTall.battle(s,e));
    }

    @Test
    void damageFromTest() {
        DefenseMod dmBrunette = new DefenseMod("brunette", 11);
        DefenseMod dmTall = new DefenseMod("tall", 11);
        s.addModifier(dmBrunette);
        s.addModifier(dmTall);
        assertEquals(46, s.getDamageFrom(e, 60, "melee"));
        assertEquals(1, s.getDamageFrom(e, 4, "melee"));
    }

    @Test
    void damageAgainstTest() {
        AttackMod atmBrunette = new AttackMod("brunette", 7);
        AttackMod atmWizard = new AttackMod("wizard", 7);
        s.addModifier(atmBrunette);
        assertEquals(2, s.getDamageAgainst(e));
        s.addModifier(atmWizard);
        assertEquals(9, s.getDamageAgainst(e));
    }

    @Test
    void receiveAttackTest() {
        s.receiveAttack(e, 5, "melee");
        // value 5 - melee defense 3 = damage 2
        // points 20 - damage 2 = 18
        assertEquals(18, s.getPoints());
        s.receiveAttack(e, 25, "melee");
        // value 25 - melee defense 3 = damage 22
        // points 18 - damage 22 = -4 < 0
        assertEquals(-4, s.getPoints());
    }

    @Test
    void chooseTargetTest() {
        ArrayList<Modifier> enemyModificators2 =  new ArrayList<>();
        ArrayList<String> enemyTags2 = new ArrayList<>();
        enemyTags2.add("brunette");
        Soldier e2 = new Soldier("Maleficent", "ranged", 25, 50, 8, 5,7, enemyTags2, enemyModificators2);

        ArrayList<Soldier> enemies = new ArrayList<>();
        enemies.add(e);
        enemies.add(e2);

        AttackMod am = new AttackMod("brunette", 8);
        s.addModifier(am); // za da ima poveche damage sreshtu Maleficent, t.e.

        assertEquals(s.chooseTarget(enemies), e2);
    }

    @Test
    void toStringTest() {
        System.out.println(s.toString());
        String str = "Kevin\n" +
                "HP: 20/100\n" +
                "ATK: 2/0\n" +
                "DEF: 3 + 4/0\n" +
                "Modifiers: \n";
        assertEquals(str, s.toString());
    }
}