import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewMarksUITest extends Application
{
	public void start(Stage ps)
	{
		ps.setResizable(false);
		ViewMarksUI v = new ViewMarksUI(new DataManager());
		v.display(ps);
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
