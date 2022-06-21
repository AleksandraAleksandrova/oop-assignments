import java.util.ArrayList;

public class Max extends AnaliseAudio{
    Max() {
        super("Max", 0);
    }

    public void process(ArrayList<Float> audio){
        float max = 0;
        for(float i : audio){
            if(i > max){
                max = i;
            }
        }
        output = max;
    }

}