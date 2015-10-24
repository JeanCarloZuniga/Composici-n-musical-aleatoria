/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaprolog;

import jm.JMC;
import jm.util.*;
import jm.music.data.*;
import jm.util.*;

/**
 * This class uses the jMusic CPhrase (Chord Phrase) The class generates a chord
 * progression around the cycle of 5ths It uses static methods in the one file.
 *
 * @author Andrew Brown
 */
public final class Chords implements JMC {

    private static Score s = new Score("CPhrase class example");
    private static Part p = new Part("GUITAR", ACOUSTIC_GUITAR);

    public static void play() {
        //Let us know things have started
        System.out.println("Creating chord progression . . .");

        //choose rootPitch notes around the cycle of fifths
        int rootPitch = g2; //set start note to middle C
        double tempo = 1.0;
        calculateChordProgresion(rootPitch,4);
        //pack the part into a score
        s.addPart(p);

        //display the music
        View.show(s);

        // write the score to a MIDIfile
        Write.midi(s, "Chords.mid");
    }

    // <editor-fold defaultstate="collapsed" desc="Chords Progressions"> 
    private static void calculateChordProgresion(int rootPitch, int loops) {
        double tempo = 1.0;
        int times = 4;
        for (int i = 0; i < loops; i++) {
            Im(rootPitch, tempo, times);
            VSharp(rootPitch, tempo, times);
            IVm7(rootPitch, tempo, times);
            V7(rootPitch, tempo, times);
        }
        Im(rootPitch, 2.0, 1);

    }

    private static void Im(int rootPitch, double tempo, int times) {
        int Im = rootPitch;
        MinorChord(Im, tempo, times);
    }

    private static void VSharp(int rootPitch, double tempo, int times) {
        int VSharp = rootPitch - 4;
        MajorChord(VSharp, tempo, 4);
    }

    private static void IVm7(int rootPitch, double tempo, int times) {
        int IVm7 = rootPitch + 5;
        Minor7Chord(IVm7, tempo, 4);
    }

    private static void V7(int rootPitch, double tempo, int times) {
        int V7 = rootPitch - 5;
        Major7Chord(V7, tempo, 4);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="Chords">
    private static void MinorChord(int rootPitch, double tempo, int times) {
        int[] pitchArray = new int[6];
        for (int i = 0; i < times; i++) {

            pitchArray[0] = rootPitch;
            pitchArray[1] = rootPitch + 7;
            pitchArray[2] = rootPitch + 12;
            pitchArray[3] = rootPitch + 15;
            pitchArray[4] = rootPitch + 19;
            pitchArray[5] = rootPitch + 24;

            //add chord to the part
            CPhrase chord = new CPhrase();
            chord.addChord(pitchArray, tempo);
            p.addCPhrase(chord);
        }
    }

    private static void MajorChord(int rootPitch, double tempo, int times) {
        int[] pitchArray = new int[6];
        for (int i = 0; i < times; i++) {

            pitchArray[0] = rootPitch;
            pitchArray[1] = rootPitch + 7;
            pitchArray[1] = rootPitch + 12;

            pitchArray[2] = rootPitch + 16;
            pitchArray[3] = rootPitch + 19;
            pitchArray[4] = rootPitch + 24;

            //add chord to the part
            CPhrase chord = new CPhrase();
            chord.addChord(pitchArray, tempo);
            p.addCPhrase(chord);
        }
    }

    private static void Minor7Chord(int rootPitch, double tempo, int times) {
        int[] pitchArray = new int[6];
        for (int i = 0; i < times; i++) {

            pitchArray[0] = rootPitch;
            pitchArray[1] = rootPitch + 7;
            pitchArray[1] = rootPitch + 10;

            pitchArray[2] = rootPitch + 15;
            pitchArray[3] = rootPitch + 19;
            pitchArray[4] = rootPitch + 24;

            //add chord to the part
            CPhrase chord = new CPhrase();
            chord.addChord(pitchArray, tempo);
            p.addCPhrase(chord);

        }
    }

    private static void Major7Chord(int rootPitch, double tempo, int times) {
        int[] pitchArray = new int[6];
        for (int i = 0; i < times; i++) {

            pitchArray[0] = rootPitch;
            pitchArray[1] = rootPitch + 7;
            pitchArray[1] = rootPitch + 10;

            pitchArray[2] = rootPitch + 16;
            pitchArray[3] = rootPitch + 22;
            pitchArray[4] = rootPitch + 24;

            //add chord to the part
            CPhrase chord = new CPhrase();
            chord.addChord(pitchArray, tempo);
            p.addCPhrase(chord);
        }
    }

    // </editor-fold>
}
