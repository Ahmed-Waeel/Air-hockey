
 
package Final;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.sound.sampled.Clip;

/**
 *
 * @author Ibrahim Abdou
 */
public class sounds {
    

        static sounds pl =new sounds();
        static Clip clip;
        
        private sounds()
        {
        
        }
        
        public static sounds getInstance(){
        return pl;
        }
    
     public static void playMusic(String location) {

        try {
            File musicPath = new File(location);

            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                 clip = AudioSystem.getClip();
                clip.open(audioInput);
            } else {
                System.out.println("Cant find file");
            }
        } catch (Exception e) {
            System.out.println(e);

        }

    }
    
}
