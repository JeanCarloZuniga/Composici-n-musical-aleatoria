/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaprolog;
import javax.swing.JOptionPane;
//Api `prolog
import java.util.Map;
import org.jpl7.JPL.*;
import org.jpl7.Query;
import org.jpl7.Term;
//Api musical
import jm.music.data.Note;
import jm.util.Play;
import jm.JMC;


/**
 *
 * @author Brandon Alvarez
 * @author Jean Carlo Zuniga
 */
public class JavaProlog {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Notas musicales a utilizar
        Note si = new Note();                 //B
        Note la = new Note();                 //A
        Note sol = new Note();                //G
        Note solSostenido = new Note();       //G#
        Note fa = new Note();                 //F
        Note faSostenido = new Note();        //F#
        Note mi = new Note();                 //E
        Note miSostenido = new Note();        //E#
        Note re = new Note();                 //D
        Note reSostenido = new Note();        //D#
        Note doo = new Note();                //C
        Note doSostenido = new Note();        //C#


        //Rammstein du hast - Probando API
 
        Note miSostenido2 = new Note();
        Note reSostenido2 = new Note();
        Note la2 = new Note();
        Note doSostenido2 = new Note();
        Note si2 = new Note();
        Note mi2 = new Note();


        miSostenido2.setPitch(JMC.ES6);
        miSostenido2.setLength(0.0000000000000000000000000000001);
        reSostenido2.setPitch(JMC.DS6);
        reSostenido2.setLength(0.0000000000000000000000000000001);
        la2.setPitch(JMC.A4);
        la2.setLength(0.0000000000000000000000000000001);
        doSostenido2.setPitch(JMC.CS6);
        doSostenido2.setLength(0.0000000000000000000000000000001);
        si2.setPitch(JMC.B5);
        si2.setLength(0.0000000000000000000000000000001);
        mi2.setPitch(JMC.E5);
        mi2.setLength(0.0000000000000000000000000000001);
        
        Play.midi(miSostenido2);
        Play.midi(reSostenido2);
        Play.midi(la2);
        Play.midi(doSostenido2);
        Play.midi(si2);
        Play.midi(mi2);
        Play.midi(la2);
        Play.midi(doSostenido2);
        Play.midi(reSostenido2);
        Play.midi(si2);    
        
         //Fin du hast
        
        
        //La siguiente linea el comando para conectar con prolog
        String conexion = "consult('tarea.pl')";
        Query conexion_query = new Query(conexion);
        System.out.println(conexion_query.hasSolution() ? "Conexion establecida" : "Error de conexion");
        
        //Ejemplo de union
        String str = "union([a,c,e],[b,d,f], X)";
        Query q2 = new Query(str);
        String n = "";
        while(q2.hasMoreSolutions())
        {
            Map<String, Term> solucion = q2.nextSolution();
            System.out.println("X = " + solucion.get("X") );
        }
    }
    
}
