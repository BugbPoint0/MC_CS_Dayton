import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
   This component draws two car shapes.
*/
public class CarPanel extends JComponent
{  
	private Car car1;
	private int x,y, delay;
	private CarQueue carQueue;
	private int direction;
	
	CarPanel(int x1, int y1, int d, CarQueue queue)
	{
		delay = d;
        x=x1;
        y=y1;
        car1 = new Car(x, y, this);
        carQueue = queue;
	}
	public void startAnimation()
	   {
	      class AnimationRunnable implements Runnable
	      {
	         public void run()
	         {
	            try
	            {
	               for (int i = 0; i < 10; i++)
	               {
	            	   int deltaX = 10;
	            	   int deltaY = 10; 
	            	   int tempDeltaX = deltaX;
	            	   int tempDeltaY = deltaY;
	         
	            	   direction = carQueue.deleteQueue();
	            	   int tempY = y;
	            	   int tempX = x;
	            	   deltaX = deltaY = 10;
	            	   
	            	   switch(direction) {
	            	   case 0: 
	            		   tempY -= deltaY;
	            		   break;
	            	   case 1:
	            		   tempY += deltaY;
	            		   break;
	            	   case 2:
	            		   tempX += deltaX;
	            		   break;
	            	   case 3:
	            		   tempX -= deltaX;
	            		   break;
	            	   }
	            	   
	            	   if (tempX < 0 || tempX > 300) {
	            		   deltaX *= -1;
	            	   } else if (tempY < 0 || tempY > 400) {
	            		   deltaY *= -1;
	            	   }
	            	   
	            	   
	            	   if (deltaX != tempDeltaX || deltaY != tempDeltaY) {
	            		   switch(direction) {
		            	   case 0: 
		            		   tempY -= deltaY;
		            		   break;
		            	   case 1:
		            		   tempY += deltaY;
		            		   break;
		            	   case 2:
		            		   tempX += deltaX;
		            		   break;
		            	   case 3:
		            		   tempX -= deltaX;
		            		   break;
		            	   }
	            	   }
	            	   
	            	   y = tempY;
	            	   x = tempX;
	            	   
	            	   repaint();
	            	   Thread.sleep(delay*1000);
	            	   
	               }
	            }
	            catch (InterruptedException exception)
	            {
	            	
	            }
	            finally
	            {
	            	
	            }
	         }
	      }
	      
	      Runnable r = new AnimationRunnable();
	      Thread t = new Thread(r);
	      t.start();
	   }
	
   public void paintComponent(Graphics g)
   {  
      Graphics2D g2 = (Graphics2D) g;

      car1.draw(g2,x,y);    
   }
}
