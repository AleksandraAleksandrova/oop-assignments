import java.util.ArrayList;
import java.util.Collections;

public class Anomally extends AnaliseAudio{
    private int window;

    Anomally(int window){
        super("Anomally", 0);
        this.window = window;
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

    @Override
    public void process(ArrayList<Float> audio){
        float anomallies = 0;
        for(int i = 0; i<=(audio.size() - window); i++){
            ArrayList<Float> temp = new ArrayList<>();

            for(int j = i; j<window+i; j++){
                temp.add(audio.get(j));
            }

            Max m = new Max();
            m.process(temp);
            float maxNum = m.output;
            temp.remove(maxNum);

            if(maxNum >= 2*median(temp)){
                anomallies +=1;
            }
        }
        output = anomallies/audio.size();
    }
}