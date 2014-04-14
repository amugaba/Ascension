import controller.GameController;
import model.GameModel;
import view.GameView;


public class Ascension {

	public static void main(String[] args) 
	{
		final GameView view = new GameView();
		GameModel model = new GameModel();
		final GameController controller = new GameController(view,model);
		
		controller.start();
	}

}
