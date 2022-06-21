import java.util.ArrayList;

public class Average extends AnaliseAudio{
    Average() {
        super("Average", 0);
    }

    public void process(ArrayList<Float> audio){
        float sum = 0;
        for(float i : audio) {
            sum += i;
        }
        output = sum/audio.size();
    }
}