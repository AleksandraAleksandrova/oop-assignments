public abstract class AnaliseAudio implements IProcessable {
    protected String type;
    protected float output;

    AnaliseAudio(String type, float output){
        this.type = type;
        this.output = output;
    }

    public String retString(){
        return "< " + type + " > = < " + output + " >";
    }
}