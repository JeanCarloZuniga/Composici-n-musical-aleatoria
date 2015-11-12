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
    private static Part chordsPart = new Part("GUITAR", ACOUSTIC_GUITAR);
    private static Part melodyPart = new Part("GUITAR", ACOUSTIC_GUITAR);
    
    
    public static void play() {
        //Let us know things have started
        System.out.println("Creating chord progression . . .");
        
        //choose rootPitch notes around the cycle of fifths
        int rootPitch = G2; //set start note to middle C
        double tempo = 1.0;
        calculateChordProgresion(rootPitch,1);
        calculateMelodyProgression();
        //pack the part into a score
        s.addPart(chordsPart);
        s.addPart(melodyPart);
        
//display the music
View.show(s);

// write the score to a MIDIfile
Write.midi(s, "Chords.mid");
    }
    
    // <editor-fold defaultstate="collapsed" desc="Chords Progressions">
    private static void calculateChordProgresion(int rootPitch, int loops) {
        double tempo = 3.5;
        int times = 2;
        for (int i = 0; i < loops; i++) {
            Im(rootPitch, tempo, times);
            VSharp(rootPitch, tempo, times);
            IVm7(rootPitch, tempo, times);
            V7(rootPitch, tempo, times);
        }
        Im(rootPitch, 3.5, 1);
        
    }
    
    private static void Im(int rootPitch, double tempo, int times) {
        int Im = rootPitch;
        MinorChord(Im, tempo, times);
    }
    
    private static void VSharp(int rootPitch, double tempo, int times) {
        int VSharp = rootPitch - 4;
        MajorChord(VSharp, tempo, times);
    }
    
    private static void IVm7(int rootPitch, double tempo, int times) {
        int IVm7 = rootPitch + 5;
        Minor7Chord(IVm7, tempo, times);
    }
    
    private static void V7(int rootPitch, double tempo, int times) {
        int V7 = rootPitch - 5;
        _7Chord(V7, tempo, times);
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
            chordsPart.addCPhrase(chord);
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
            chordsPart.addCPhrase(chord);
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
            chordsPart.addCPhrase(chord);
            
        }
    }
    
    private static void _7Chord(int rootPitch, double tempo, int times)  {
        int[] pitchArray = new int[6];
        for (int i = 0; i < times; i++) {
            
            if(i<times/2){
                pitchArray[0] = rootPitch;
                pitchArray[1] = rootPitch + 4;
                pitchArray[2] = rootPitch + 10;
                
                pitchArray[3] = rootPitch + 12;
//            pitchArray[4] = rootPitch + 16;
//            pitchArray[5] = rootPitch + -9;

//add chord to the part
CPhrase chord = new CPhrase();
chord.addChord(pitchArray, tempo);
chordsPart.addCPhrase(chord);
            }
            else{
                pitchArray[0] = rootPitch + -9;
                pitchArray[1] = rootPitch + -6;
                pitchArray[2] = rootPitch ;
                pitchArray[3] = rootPitch + 7;
                pitchArray[4] = rootPitch + 10;
                pitchArray[5] = rootPitch + 16;
                CPhrase chord = new CPhrase();
                chord.addChord(pitchArray, tempo);
                chordsPart.addCPhrase(chord);
                
            }
        }
    }
    
    // </editor-fold>
    
    private static void calculateMelodyProgression() {
        Phrase phr = new Phrase("Twinkle twinkle", 0.0);
        double octavo = 0.4375;
        int[] pitchArray = {G2,A2,AS2,C3,    G2,A2,AS2,D3,
            DS3,G3,AS3,G3,DS4,AS3, DS3,G3,AS3,G3,G4,DS4,AS3,
            C3,G3,DS4,AS3,G4,DS4, C3,G3,DS4,AS3,C5,G4,DS4,
            D3,FS3,C4,D4,C4,FS3,  A3, C4, A3,FS4,C4,FS4} ;
        double[] rhythmArray = {octavo, octavo, octavo, octavo*5,
            octavo, octavo, octavo, octavo*5,
            
            octavo, octavo, octavo, octavo, octavo, octavo*3,
            octavo,octavo,octavo,octavo,octavo,octavo,octavo*2,
            
            octavo, octavo, octavo, octavo, octavo, octavo*3,
            octavo, octavo, octavo, octavo, octavo, octavo*3,
            
            octavo, octavo, octavo, octavo, octavo, octavo*3,
            octavo, octavo, octavo, octavo, octavo, octavo*3};
        phr.addNoteList(pitchArray, rhythmArray);
        melodyPart.add(phr);
    }
}
