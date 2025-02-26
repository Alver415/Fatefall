import com.alver.fatefall.fx.app.view.entity.card.CardEditorView;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.springfx.SpringFXLoader;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TestApplication extends Application {

	public static class Launcher{
		public static void main(String... args){
			Application.launch(TestApplication.class, args);
		}
	}
	@Override
	public void start(Stage stage) throws IOException {
		Parent root = createSceneRoot();

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("Test Application");
		stage.centerOnScreen();
		stage.show();
	}

	private Parent createSceneRoot() throws IOException {
		SpringFXLoader loader = new SpringFXLoader(CardEditorView.FXML);
		Node load = loader.load();
		CardEditorView cardEditorView = loader.getController();
		return new BorderPane(load);
	}
}
