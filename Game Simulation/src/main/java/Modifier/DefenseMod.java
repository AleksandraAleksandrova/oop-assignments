package Modifier;
import Soldier.*;

public class DefenseMod implements Modifier {
    private String tag;
    private int value;
    private String description;
    private String type;

    public DefenseMod(String tag, int value) {
        this.tag = tag;
        this.value = value;
        description = "Extra defense against " + tag + " for +" + value;
        type = "d";
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
    public void change(Soldier s) {}

    public int battle(Soldier s, Soldier e){
        for(String searchTag : e.getTags()){
            if(searchTag == tag){
                return value;
            }
        }
        return 0;
    }
}