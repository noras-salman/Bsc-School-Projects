/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hack;

import javax.swing.JOptionPane;

/**
 *
 * @author Noras Salman
 */
public class Error extends Exception{
    public static int i=0;
   public Error(String S) throws Exception{
       i++;
       if (i==1){
           //JOptionPane.showMessageDialog(null, S, "Hack Compiler Error", JOptionPane.ERROR_MESSAGE);
           HackGUI.setError(S+"\n");
    
           System.out.println(S);

   }
   }
   }
