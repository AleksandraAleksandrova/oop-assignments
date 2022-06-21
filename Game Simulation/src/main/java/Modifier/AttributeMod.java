package Modifier;
import Soldier.*;

public class AttributeMod implements Modifier {
    private String attribute;
    private int value;
    private String description;
    private String type;

    public AttributeMod(String attribute, int value) {
        this.attribute = attribute;
        this.value = value;
        description = "This attr modifier changes " + attribute + " to " + value;
        type = "at";
    }

    @Override
    public int getValue() {
        return value;
    }
    public String getType() {
        return type;
    }
    @Override
    public String description() {
        return description;
    }

    @Override
    public void change(Soldier s) {
        if(attribute == "points") {
            s.setPoints(value);
        }
        else if(attribute == "maxPoints") {
            s.setMaxPoints(value);
        }
        else if(attribute == "attackPower") {
            s.setAttackPower(value);
        }
        else if(attribute == "meleeDefense"){
            s.setMeleeDefense(value);
        }
        else if(attribute == "rangedDefense") {
            s.setRangedDefense(value);
        }
    }

    @Override
    public int battle(Soldier s, Soldier e) {
        return 0;
    }
}