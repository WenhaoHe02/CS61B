package gh2;

/**
 * ClassName : GuitarHero
 * Package:gh2
 * Description:
 *
 * @Author JS123748 何文昊
 * @Create 2023/10/27 15:30
 * @Version Latest
 */
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**This keyboard arrangement imitates a piano keyboard: The “white keys”
 * are on the qwerty and zxcv rows and the “black keys” on the 12345 and asdf rows of the keyboard.zxx
 */

public class GuitarHero {
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static final int KEYS_AMOUNT = KEYBOARD.length();
    public static final double CONCERT_A = 440.0;
    
    public static void main(String[] args) {
        GuitarString[] guitarStrings = new GuitarString[KEYS_AMOUNT];
        
        for (int i = 0; i < KEYS_AMOUNT; i++) {
            double frequency = CONCERT_A * Math.pow(2, (i - 24) / 12.0);
            guitarStrings[i] = new GuitarString(frequency);
        }
        
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int keyIndex = KEYBOARD.indexOf(key);
                
                if (keyIndex > 0) {
                    guitarStrings[keyIndex].pluck();
                }
            }
            
            double sample = 0.0;
            for (GuitarString s : guitarStrings) {
                sample += s.sample();
            }
            
            StdAudio.play(sample);
            
            for (GuitarString s : guitarStrings) {
                s.tic();
            }
        }
    }
}
