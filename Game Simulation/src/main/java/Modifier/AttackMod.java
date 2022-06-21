package Modifier;
import Soldier.*;

public class AttackMod implements Modifier {
    private String tag;
    private int value;
    private String description;
    private String type;

    public AttackMod(String tag, int value) {
        this.tag = tag;
        this.value = value;
        description = "Extra attack against " + tag + " for +" + value;
        type = "ak";
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public void change(Soldier s) {
        //System.out.println("You shouldn't be using this func from here!");
    }

    public int battle(Soldier s, Soldier e){
        for(String searchTag : e.getTags()){
            if(searchTag == tag){
                return value;
            }
        }
        return 0;
    }
}