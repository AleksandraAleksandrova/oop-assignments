import java.util.ArrayList;
import java.util.Collections;

public class Denoise extends TransformAudio{
    private int window;
    private int times;

    Denoise(ArrayList<Float> output, int window, int times){
        super("denoise", output);
        this.window = window;
        this.times = times;
    }

    private float median(ArrayList<Float> temp){
        float median = 0;
        Collections.sort(temp);
        int size = temp.size();
        if(size%2 == 1){
            // nechetno
            median = temp.get(size/2);
        }else{
            // chetno
            median = (temp.get(size/2) + temp.get((size/2)-1))/2;
        }
        return median;
    }

    public void process(ArrayList<Float> audio) {
        for(int i = 0; i< audio.size(); i++){
            output.add(audio.get(i));
        }
        for(int i = 0; i<=(output.size() - window); i++){
            ArrayList<Float> temp = new ArrayList<>();
            for(int j = i; j<window+i; j++){
                temp.add(output.get(j));
            }
            Max m = new Max();
            m.process(temp);
            float maxNum = m.output;
            int maxNumIndex = output.indexOf(maxNum);
            temp.remove(maxNum);
            
            if(maxNum >= times*median(temp)){
                m.process(temp);
                output.set(maxNumIndex, m.output);
            }
        }
    }
}
