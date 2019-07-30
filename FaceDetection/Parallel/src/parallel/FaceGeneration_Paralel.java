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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import hypermedia.video.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class FaceGeneration_Paralel
{
 static OpenCV cv = new OpenCV();
 static Rectangle[] squares = new Rectangle[0];
 static int faces=0;
 static  BufferedImage bi;

  FaceGeneration_Paralel(String Input,String Output) throws IOException{
      LoadImage(Input);
bi=ImageIO.read(new File(Input));
  //----Paralel Exection----///////////////////////////////////
 faces=getFaces();
  WorkerThread[] threads = new WorkerThread[faces];
  for (int i=0; i < faces; i++) {
threads[i] = new WorkerThread(i,Output);
threads[i].start();

}
       
  }
  public static void LoadImage(String path) throws IOException{
   cv.loadImage(path);
  }
  public int getFaces(){
      cv.cascade( OpenCV.CASCADE_FRONTALFACE_ALT );
      squares = cv.detect( 1.2f, 2, OpenCV.HAAR_DO_CANNY_PRUNING, 20, 20 );
      System.out.println("Faces="+squares.length);
      return squares.length;
  }
  public static  void generate(int i,BufferedImage bi,String output) throws IOException{
 File f=new File(output+"//TestOutFace("+(i+1)+").jpg");
 ImageIO.createImageOutputStream(f);
bi=bi.getSubimage(squares[i].x, squares[i].y, squares[i].width, squares[i].height);
ImageIO.write(bi, "jpg", f);
  }

  private static class WorkerThread extends Thread {
      int i;
      String output;

public WorkerThread(int i,String output) {
this.i=i;
this.output=output;
}

public void run() {
            try {
                FaceGeneration_Paralel.generate(i, bi, output);
            } catch (IOException ex) {
                Logger.getLogger(FaceGeneration_Paralel.class.getName()).log(Level.SEVERE, null, ex);
            }
                System.out.println("Generating face Photo"+(i+1));}
    }
  public static void main(String args[]) throws IOException{
   new FaceGeneration_Paralel("INPUT//36faces_3072.jpg","OUTPUT");
// new FaceGeneration_Paralel("INPUT//21facese1024.jpg","OUTPUT");
  // new FaceGeneration_Paralel("INPUT//4faces2560_1440.jpg","OUTPUT");
    //   new FaceGeneration_Paralel("INPUT//2faces_1024.jpg","OUTPUT");
 
  }
}
