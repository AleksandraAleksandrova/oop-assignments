import java.util.ArrayList;

public class Mute extends TransformAudio{
    Mute(ArrayList<Float> output){
        super("mute", output);
    }

    public void  process(ArrayList<Float> audio){
        for(int i = 0; i < audio.size(); i++){
            output.add(0.0f);
        }
    }
}