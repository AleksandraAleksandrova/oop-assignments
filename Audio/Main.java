import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
        ArrayList<Float> audio = new ArrayList<>();
        audio.add(1.0f);
        audio.add(2.0f);
        audio.add(10.0f);
        audio.add(5.0f);
        audio.add(6.0f);
        audio.add(8.0f);
        
        Average a = new Average();
        a.process(audio);
        System.out.println(a.retString());// 5.33...
        
        Max m = new Max();
        m.process(audio);
        System.out.println(m.retString()); // 10
        
        Anomally anom = new Anomally(3);
        anom.process(audio);
        Anomally anom2 = new Anomally(4);
        anom2.process(audio);
        System.out.println("Anomally w3: " + anom.retString()); // 1/3
        System.out.println("ANomally w4: " + anom2.retString()); // 1/3
        
        ArrayList<Float> mutedAudio = new ArrayList<>();
        Mute m2 = new Mute(mutedAudio);
        m2.process(audio);
        System.out.println(m2.retString()); // 6 pati 0
        

        ArrayList<Float> audio2 = new ArrayList<>();
        audio2.add(-5.0f);
        audio2.add(2.0f);
        audio2.add(3.0f);
        audio2.add(-4.0f);
        audio2.add(10.0f);
        audio2.add(12.0f);
        ArrayList<Float> normAudio = new ArrayList<>();
        Normalize norm = new Normalize(normAudio);
        norm.process(audio2);
        System.out.println(norm.retString()); //  [-1, -0.17648, -0.05884, -0.88236, 0.7647, 1]
        
        
        ArrayList<Float> denAudio = new ArrayList<>();
        Denoise den = new Denoise(denAudio, 4, 2);
        den.process(audio);
        System.out.println("Denoise w4 t2 " + den.retString());
        // 1 2 5 5 6 8 
    }
}