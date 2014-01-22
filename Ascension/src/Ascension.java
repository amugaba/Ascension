import controller.GameController;
import model.GameModel;
import model.IGameModel;
import view.GameView;
import view.IGameView;


public class Ascension {

	public static void main(String[] args) 
	{
		final IGameView view = new GameView();
		IGameModel model = new GameModel();
		final GameController controller = new GameController(view,model);
		
		controller.start();
	}

}
