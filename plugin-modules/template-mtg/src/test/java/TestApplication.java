import com.alver.fatefall.fx.app.view.entity.card.CardEditorView;
import com.alver.fatefall.mtg.plugin.MagicCard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class TestApplication extends Application {

	private static final Logger log = LoggerFactory.getLogger(TestApplication.class);

	public static class Launcher {
		public static void main(String... args) {
			Application.launch(TestApplication.class, args);
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		log.info("Start");
		Parent root = createSceneRoot();

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("Test Application");
		stage.centerOnScreen();
		stage.show();
	}

	private Parent createSceneRoot() throws IOException {
		FXMLLoader loader = new FXMLLoader(CardEditorView.FXML);
		Node load = loader.load();
		CardEditorView cardEditorView = loader.getController();
		cardEditorView.setCard(new MagicCard<>());

		return new BorderPane(load);
	}
}
