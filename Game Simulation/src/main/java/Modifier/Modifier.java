package Modifier;
import Soldier.*;

public interface Modifier {
    public void change(Soldier s); // za attribute
    public int battle(Soldier s, Soldier e); // za attack and defense
    public String description(); // za printiraneto
    public String getType(); // za proverki
    public int getValue(); // za printiraneto
}
