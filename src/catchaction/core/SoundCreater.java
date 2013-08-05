/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catchaction.core;

import com.sun.speech.freetts.audio.SingleFileAudioPlayer;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat.Type;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.Voice;
import util.CProperties;

/**
 *
 * @author kanishka
 */
public class SoundCreater extends SoundSource {

    String voiceName = "kevin16";
    VoiceManager voiceManager;
    Voice voice;
    public String readText = "This is the default text";
    public String SaveName = "filename2";
    private String savepath = null;

    public SoundCreater() {
        try {
            savepath = CProperties.getInstance().getProperty("teampfileurl");
        } catch (IOException ex) {
            System.out.println("Properties file is missing");
            ex.printStackTrace();
        }
    }

    static void listAllVoices() {
        System.out.println();
        System.out.println("All voices available:");
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice[] voices = voiceManager.getVoices();
        for (int i = 0; i < voices.length; i++) {
            System.out.println("    " + voices[i].getName() + " (" + voices[i].getDomain() + " domain)");
        }
    }

    private void setup() {
        // listAllVoices();
        System.out.println();
        //System.out.println("Using voice: " + voiceName);
        voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(voiceName);
        voice.setStyle("casual");  //"business", "casual", "robotic", "breathy"
        //voice.setRate(100);
        voice.setVolume(2.0f);
        //System.out.println(voice.getVolume());
        if (voice == null) {
            System.err.println(
                    "Cannot find a voice named " + voiceName + ".  Please specify a different voice.");
            System.exit(1);
        }
        voice.allocate();
    }

    public void createWave() {
        try {

            //System.setProperty("com.sun.speech.freetts.voice.defaultAudioPlayer", "com.sun.speech.freetts.audio.SingleFileAudioPlayer");
            setup();
            // SingleFileAudioPlayer aud = (SingleFileAudioPlayer) voice.getDefaultAudioPlayer();
            String path = savepath + SaveName;
            //  if (!new File(path).exists()) {
            //System.out.println("Creating audio......");
            SingleFileAudioPlayer play = new SingleFileAudioPlayer(path, Type.WAVE);
            voice.setAudioPlayer(play);
            this.AudioPath = path + ".wav";
            //System.out.println(AudioPath);
            voice.speak(readText);
            play.close();
            //     }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void speakLive(String read) {
        // no need of positional information
        
        if (voiceManager == null) {
            setup();
        }
        voice.setVolume(1.0f);
        //System.out.println("adjust volume");
        com.sun.speech.freetts.audio.JavaStreamingAudioPlayer play = new com.sun.speech.freetts.audio.JavaStreamingAudioPlayer();
        voice.setAudioPlayer(play);
        voice.speak(read);
        play.close();
        voice.setVolume(2.0f);
    }
}
