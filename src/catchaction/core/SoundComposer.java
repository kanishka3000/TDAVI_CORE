/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catchaction.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.java.games.joal.AL;
import net.java.games.joal.ALC;
import net.java.games.joal.ALCcontext;
import net.java.games.joal.ALCdevice;
import net.java.games.joal.ALException;
import net.java.games.joal.ALFactory;
import net.java.games.joal.util.ALut;
import util.CProperties;

/**
 *
 * @author kanishka
 */
public class SoundComposer {

    ArrayList<SoundSource> SoundSources = new ArrayList<SoundSource>();
    String errorpath = null;
    static ALC alc;
    static AL al;
    float Listenerx = 0;
    float Listenery = 0;
    float Listenerz = 0;
    float ListenerVelx = 0;
    float ListenerVely = 0;
    float ListenerVelz = 0;
    float ListenerOrix = 0;
    float ListenerOriy = 0;
    float ListenerOriz = 0;
    int buffer[];
    int[] sources;
    int CurrentlyPlayingId = -1;
    int playone = 0;
    public static boolean isPositional=true;
    ///
    static int PlayOnlyOne = 1;
    static int PlayMultiple = 0;
    public static final int RANDOM_POSITION_INTIALIZED = 1;
    public static final int RAMDOM_POSITION_NOTINITIALIZED = 0;
    public int Random_Position = RAMDOM_POSITION_NOTINITIALIZED;

    public SoundComposer() {
        try {
            errorpath = CProperties.getInstance().getProperty("teampfileurl") + "default\\WindowsError2.wav";
        } catch (Exception e) {
        }
        //ALut.alutInit();
//        try {
//
//            ALut.alutInit();
//
//
//        } catch (Exception e) {
//            System.out.println("Already AL initiated");
//        }
//        al = ALFactory.getAL();
        initOpenAL();
    }

    void initOpenAL() throws ALException {
        alc = ALFactory.getALC();
        al = ALFactory.getAL();

        ALCdevice device;
        ALCcontext context;
        String deviceSpecifier;

        // Get handle to default device.
        device = alc.alcOpenDevice("DirectSound Default");
        if (device == null) {
            throw new ALException("Error opening default OpenAL device");
        }

        // Get the device specifier.
        deviceSpecifier = alc.alcGetString(device, ALC.ALC_DEVICE_SPECIFIER);
        if (deviceSpecifier == null) {
            throw new ALException("Error getting specifier for default OpenAL device");
        }

        System.out.println("Using device " + deviceSpecifier);
//Using device Generic Hardware
        // Create audio context.
        context = alc.alcCreateContext(device, null);
        if (context == null) {
            throw new ALException("Error creating OpenAL context");
        }

        // Set active context.
        alc.alcMakeContextCurrent(context);

        // Check for an error.
        if (alc.alcGetError(device) != ALC.ALC_NO_ERROR) {
            throw new ALException("Error making OpenAL context current");
        }
    }

    public void addToSoundSources(SoundSource sound) {

        SoundSources.add(sound);
        SoundCreater a = (SoundCreater) sound;
        a.createWave();
    }

    public void cleanAudio() {
        for (SoundSource s : SoundSources) {
            System.out.println("rm " + s.AudioPath);
            new File(s.AudioPath).delete();
        }
    }

    public void setListenerValues() {
        al.alListenerfv(AL.AL_POSITION, new float[]{Listenerx, Listenery, Listenerz}, 0);
        al.alListenerfv(AL.AL_VELOCITY, new float[]{ListenerVelx, ListenerVely, ListenerVelz}, 0);
        al.alListenerfv(AL.AL_ORIENTATION, new float[]{0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f}, 0);
    }

    public void loadSoundSources() {
        // System.out.println("channnels" + AL.AL_CHANNELS + "|" + AL.AL_CHANNEL_MASK);
        int[] format = new int[1];

        int[] size = new int[1];
        ByteBuffer[] data = new ByteBuffer[1];
        int[] freq = new int[1];
        int[] loop = new int[1];
        int noofsources = SoundSources.size();
        buffer = new int[noofsources];
        sources = new int[noofsources];
        al.alGenBuffers(noofsources, buffer, 0);
        al.alGenSources(noofsources, sources, 0);
        //buffers and the sources are generated at the same time;
        if (al.alGetError() != AL.AL_NO_ERROR) {
            System.out.println("Error at generating sources");
        }
        //System.out.println("size" + noofsources);
        int i = 0;
        for (SoundSource sound : SoundSources) {
            try {
                ALut.alutLoadWAVFile(new FileInputStream(sound.AudioPath), format, data, size, freq, loop);
                al.alBufferData(buffer[i], format[0], data[0], size[0], freq[0]);
                sound.SourceID = i;
                //System.out.println(sound.SourceID + "==" + buffer[i]);
                //
                sound.SourceID = sources[i];
                al.alSourcei(sources[i], AL.AL_BUFFER, buffer[i]);
                al.alSourcef(sources[i], AL.AL_PITCH, 1.0f);
                al.alSourcef(sources[i], AL.AL_GAIN, 1.0f);
                if(isPositional){
                al.alSourcefv(sources[i], AL.AL_POSITION, new float[]{sound.x, 0.0f, -(sound.y)}, 0);}else{
                al.alSourcefv(sources[i], AL.AL_POSITION, new float[]{0.0f, 0.0f, 0.0f}, 0);
                }
                al.alSourcefv(sources[i], AL.AL_VELOCITY, new float[]{sound.vx, sound.vy, sound.vz}, 0);
                al.alSourcei(sources[i], AL.AL_LOOPING, sound.loop);
                //

                i++;
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        setListenerValues();
        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(
                new Thread(
                new Runnable() {

                    public void run() {
                        killALData();
                    }
                }));
    }
    int bufferk[] = null;
    int[] sourcesk = null;

    public void playRandomPositioned(float x, float y) {
        if (this.Random_Position == RAMDOM_POSITION_NOTINITIALIZED) {
            intializeRandomPositioned();
        }


        //System.out.println(x + "=_=" + y);
        al.alSourcefv(sourcesk[0], AL.AL_POSITION, new float[]{x, y, 0}, 0);
        //al.alDeleteSources(1, sourcesk, 0);
        int[] state = new int[1];
        al.alGetSourcei(sourcesk[0], AL.AL_SOURCE_STATE, state, 0);
        if (!(state[0] == AL.AL_PLAYING)) {
            if (this.CurrentlyPlayingId != -1 && this.playone == SoundComposer.PlayOnlyOne) {
                al.alSourceStop(CurrentlyPlayingId);
            }
            al.alSourcePlay(sourcesk[0]);

        } else {
            System.out.println("already playing");
        }
    }

    public void intializeRandomPositioned() {
        //System.out.println("Initilizing Random positioned sounds.....");
        Random_Position = RANDOM_POSITION_INTIALIZED;
        int[] format = new int[1];
        int[] size = new int[1];
        ByteBuffer[] data = new ByteBuffer[1];
        int[] freq = new int[1];
        int[] loop = new int[1];
        int noofsources = 1;

        bufferk = new int[noofsources];
        sourcesk = new int[noofsources];
        al.alGenBuffers(noofsources, bufferk, 0);
        al.alGenSources(noofsources, sourcesk, 0);
        if (al.alGetError() != AL.AL_NO_ERROR) {
            System.out.println("Error at generating sources");
        }
        ALut.alutLoadWAVFile(errorpath, format, data, size, freq, loop);
        al.alBufferData(bufferk[0], format[0], data[0], size[0], freq[0]);
        al.alSourcei(sourcesk[0], AL.AL_BUFFER, bufferk[0]);
        al.alSourcef(sourcesk[0], AL.AL_PITCH, 1.0f);
        al.alSourcef(sourcesk[0], AL.AL_GAIN, 1.0f);

        al.alSourcefv(sourcesk[0], AL.AL_VELOCITY, new float[]{0, 0, 0}, 0);
        al.alSourcei(sourcesk[0], AL.AL_LOOPING, AL.AL_FALSE);



        //al.alDeleteBuffers(1, bufferk, 0);

    }

    static void exitOpenAL() {
        ALCcontext curContext;
        ALCdevice curDevice;

        // Get the current context.
        curContext = alc.alcGetCurrentContext();

        // Get the device used by that context.
        curDevice = alc.alcGetContextsDevice(curContext);

        // Reset the current context to NULL.
        alc.alcMakeContextCurrent(null);

        // Release the context and the device.
        alc.alcDestroyContext(curContext);
        alc.alcCloseDevice(curDevice);
    }

    public void killALData() {
        System.out.println("Leaving resources");
        cleanAudio();
        al.alDeleteBuffers(1, buffer, 0);
        al.alDeleteSources(1, sources, 0);
        if (bufferk != null) {
            al.alDeleteBuffers(1, bufferk, 0);
            al.alDeleteSources(1, sourcesk, 0);
        }
        //ALut.alutExit();
        exitOpenAL();
    }

    public void play(int sourceid) {
        //System.out.println(sourceid);
        int[] state = new int[1];
        al.alGetSourcei(sourceid, AL.AL_SOURCE_STATE, state, 0);
        if (!(state[0] == AL.AL_PLAYING)) {
            if (this.CurrentlyPlayingId != -1 && this.playone == SoundComposer.PlayOnlyOne) {
                al.alSourceStop(CurrentlyPlayingId);
            }

            al.alSourcePlay(sourceid);
            this.CurrentlyPlayingId = sourceid;
        } else {
            System.out.println("already playing");
        }
    }

    public void stop(int sourceid) {
        System.out.println(sourceid);
        int[] state = new int[1];
        al.alGetSourcei(sourceid, AL.AL_SOURCE_STATE, state, 0);
        if ((state[0] == AL.AL_PLAYING)) {
            al.alSourceStop(sourceid);
        } else {
            System.out.println("already stopped");
        }
    }
}
