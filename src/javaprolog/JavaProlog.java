/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaprolog;
import javax.swing.JOptionPane;
//Api `prolog
import java.util.*;
import javax.swing.UIManager;
import org.jpl7.JPL.*;
import org.jpl7.Query;
import org.jpl7.Term;
//Api musical
import jm.music.data.Note;
import jm.util.Play;
import jm.JMC;
//leer ficheros
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 *
 * @author Brandon Alvarez
 * @author Jean Carlo Zuniga
 */
public class JavaProlog {
    
    //Dirección de la base de conocimiento de prolog
    static final String base_prolog = "base_prolog.txt";
    static final String archivo_prolog = "tarea.pl";
    static List progresion = new LinkedList();
    static List escalas = new LinkedList();
    
    /*
    EFE:
    REQ: Que el resultado en prolog se haya asignado a una variable de nombre X
    MOD:
    */
    public static void a_lista(String lista)
    {
        String constructora = "";
        String partes="";
        for(int i=0; i<lista.length(); i++)
        {
            if( (lista.charAt(i) != '\'') && (lista.charAt(i) != 'X')  && (lista.charAt(i) != '=')  && (lista.charAt(i) != '{')  && (lista.charAt(i) != '}') && (lista.charAt(i) != ',') && (lista.charAt(i) != '(') && (lista.charAt(i) != ')'))
            {
                constructora = constructora + lista.charAt(i);
            }
        }
        System.out.println("Constructora: " + constructora + "\n Particionada:\n");
        for(int i=0; i<constructora.length(); i++)
        {
            if(constructora.charAt(i) != ' ')
            {
                partes = partes + constructora.charAt(i);
            }
            else
            {
                progresion.add(partes);
                partes = "";
            }
        }
        
        //A continuación se imprime la lista para efectos de prueba.
        progresion.stream().forEach((progresion1) -> {
            System.out.println(progresion1);
        });
    }
    
    /*
    EFE:
    REQ:
    MOD:
    */
    public static void cargar_base_prolog()throws FileNotFoundException, IOException
    {
        String cadena;
        //Carga las progresion existentes
        String funcion_dinamica = "dynamic escala/1.";
        Query funcion_dinamica_query = new Query(funcion_dinamica);
        System.out.println(funcion_dinamica_query.hasSolution() ? "Funcion dinámica cargada" : "Error de funcion dinámica");
        FileReader lector_archivo = new FileReader(base_prolog);
        try (BufferedReader buffer_lector = new BufferedReader(lector_archivo)) {
            while((cadena = buffer_lector.readLine()) != null) 
            {
                //Hace el assert en prolog
                System.out.println("tengo: " + cadena);
                Query asert_query = new Query(cadena);
                System.out.println(asert_query.hasSolution() ? "Assert correcto" : "Assert incorrecto");
            }
        }
    }
    
    /*
    EFE:
    REQ:
    MOD:
    */
    public static void guardar_base_prolog(String asert) throws Exception
    {
        try (RandomAccessFile file_escritor = new RandomAccessFile(new File(base_prolog), "rw")) {
            file_escritor.seek(file_escritor.length());
            file_escritor.writeBytes(System.getProperty("line.separator"));
            file_escritor.write(asert.getBytes());
            file_escritor.close();
        }
        //Carga en la base en assert 
        Query asert_query = new Query(asert);
        System.out.println(asert_query.hasSolution() ? "Assert correcto" : "Assert incorrecto");
    }
    
    /*
    EFE:
    REQ:
    MOD:
    */
    public static void controlador() throws IOException, Exception
    {
        //Comando para conectar con prolog
        String conexion = "consult('" + archivo_prolog + "')";
        Query conexion_query = new Query(conexion);
        System.out.println(conexion_query.hasSolution() ? "Conexion establecida" : "Error de conexion");
        cargar_base_prolog();
        /*
        Prueba de unidad:
        
            *guarda en el archivo y en la base una progresion.
        
        String abc = "assert(progresion(b, (b, g, gN, aN, c, cN, e, f))).";
        guardar_base_prolog(abc);
        */
        
        /*
        Prueba de unidad:
        
            *Consulta de una progresion previamente cargada
        */
        String str = "escala(aS2, X)";
        System.out.print("ESCALA:\n");
        Query q2 = new Query(str);
        System.out.println(q2.hasSolution() ? "Consulta atendida" : "Consulta mal");

        while(q2.hasMoreSolutions())
        {
            Map<String, Term> solucion = q2.nextSolution();
            System.out.println(solucion.get("X"));
            System.out.println("A LISTA:");
            a_lista(solucion.toString());
        }  
        
    }
    
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
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

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");           
            new FrameJavaProlog().setVisible(true);
        }
        catch (Exception e) {
             //Se captura el error y no se instancia la GUI
        }
/*
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
        */
        //Chords.play();
        controlador();
    }
}
