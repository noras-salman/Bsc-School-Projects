/**
 *
 * @author Noras Salman
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package parallel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import hypermedia.video.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
public class FaceGeneration_Serial{
 static OpenCV cv = new OpenCV();
 static Rectangle[] squares = new Rectangle[0];
 static int faces=0;
 static BufferedImage bi;
public static boolean[] hasbeengenerated=new boolean[faces];
  FaceGeneration_Serial(String input,String output) throws IOException{
  cv.loadImage(input);
 bi=ImageIO.read(new File(input));
//----Serial Execution---/////////////
  faces=getFaces();
 for (int i=0;i<faces;i++)
 {generate(i,bi,output);System.out.println("Generating face Photo"+(i+1));}
  }
  public static void LoadImage(String Input) throws IOException{
   cv.loadImage(Input);
  }
  public int getFaces(){
      cv.cascade( OpenCV.CASCADE_FRONTALFACE_ALT );
      squares = cv.detect( 1.2f, 2, OpenCV.HAAR_DO_CANNY_PRUNING, 20, 20 );
      System.out.println("Faces="+squares.length);
      return squares.length;
  }
      public static void generate(int i,BufferedImage bi,String output) throws IOException{
 File f=new File(output+"//TestOutFace("+(i+1)+").jpg");
 ImageIO.createImageOutputStream(f);
bi=bi.getSubimage(squares[i].x, squares[i].y, squares[i].width, squares[i].height);
ImageIO.write(bi, "jpg", f);
  }
  public static void main(String args[]) throws IOException{
   new FaceGeneration_Serial("INPUT//36faces_3072.jpg","OUTPUT");
 // new FaceGeneration_Serial("INPUT//21facese1024.jpg","OUTPUT");
   // new FaceGeneration_Serial("INPUT//4faces2560_1440.jpg","OUTPUT");
      // new FaceGeneration_Serial("INPUT//2faces_1024.jpg","OUTPUT");
  }
}
