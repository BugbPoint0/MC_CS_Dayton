




import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Driver for the Movie Ticket Manager GUI in FX
 * @author Professor Kartchner
 *
 */
public class FXMovieDriver extends Application
{
    public FXMovieDriver()  {
    }

   public static void main (String[] args)
   {
      launch(args);
   }

	@Override
	public void start(Stage stage) throws Exception {		
		FXMainPane root = new FXMainPane();
        stage.setScene(new Scene(root, 500, 650));
		// Set stage title and show the stage.
		stage.setTitle("Movie Tickets");
		stage.show();
	}
}
