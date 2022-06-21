import java.util.ArrayList;

public class Normalize extends TransformAudio {
    Normalize(ArrayList<Float> output){
        super("Normalize", output);
    }

    @Override
    public void process(ArrayList<Float> audio) {
        float min = audio.get(0);
        float max = audio.get(0);
        for(Float num : audio){
            if(min > num){
                min = num;
            }
            if(max < num){
                max = num;
            } 
        }
        output = audio;
        
        for(int i = 0; i<output.size(); i++){
            output.set(i,  2*((output.get(i)-min)/(max-min))-1);
        }
        
    } 
}
