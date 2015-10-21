/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaprolog;
import java.util.Map;
import org.jpl7.JPL.*;
import org.jpl7.Query;
import org.jpl7.Term;


/**
 *
 * @author Jean
 */
public class JavaProlog {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String conexion = "consult('tarea.pl')";
        Query q1 = new Query(conexion);
        System.out.println(q1.hasSolution() ? "conexion" : "error");
        String str = "union([a,c,e],[b,d,f], X)";
        Query q2 = new Query(str);
        String n = "";
        while(q2.hasMoreSolutions())
        {
            Map<String, Term> sol = q2.nextSolution();
            System.out.println("X = " + sol.get("X") );
        }
    }
    
}
