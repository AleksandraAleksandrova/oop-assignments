import java.util.ArrayList;

public abstract class TransformAudio implements IProcessable{
    protected String type;
    protected ArrayList<Float> output;

    TransformAudio(String type, ArrayList<Float> output){
        this.type = type;
        this.output = output;
    }

    public String retString(){
        return "< " + type + " > = < " + output + " >";
    }
}