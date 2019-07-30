/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package parallel;
import java.awt.*;
import java.awt.event.*;
import hypermedia.video.OpenCV;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Image_FaceDetection extends Frame {
        OpenCV cv = null;
	Image frame	= null;
	Rectangle[] squares = new Rectangle[0];
        BufferedImage bi;
	Image_FaceDetection() {
		super( "Face Detection Sample" );
		cv = new OpenCV();
                cv.loadImage("INPUT//21facese1024.jpg");
		cv.cascade( OpenCV.CASCADE_FRONTALFACE_ALT );
		this.setBounds(50, 50, 1024, 576 );
		//this.setBackground( Color.BLACK );
		this.setVisible( true );
		this.addKeyListener(
			new KeyAdapter() {
				public void keyReleased( KeyEvent e ) {
					if ( e.getKeyCode()==KeyEvent.VK_ESCAPE ) { // ESC : release OpenCV resources
						cv.dispose();
						System.exit(0);
					}
				}
			}
		);
 try {
          bi = ImageIO.read(new File("INPUT//21facese1024.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Image_FaceDetection.class.getName()).log(Level.SEVERE, null, ex);
        }
                squares = cv.detect( 1.2f, 2, OpenCV.HAAR_DO_CANNY_PRUNING, 20, 20 );
				frame = bi;
                                repaint();
        }
	public void paint( Graphics g ) {
		g.drawImage( frame, 0, 0, null );
		g.setColor( Color.RED );
		for( Rectangle rect : squares )
			g.drawRect( rect.x, rect.y, rect.width, rect.height );
	}

	public static void main( String[] args ) {
		System.out.println( "\nOpenCV face detection sample\n" );
		new Image_FaceDetection();
	}

}
