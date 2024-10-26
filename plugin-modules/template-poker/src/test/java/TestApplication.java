import com.alver.fatefall.fx.app.view.entity.card.CardEditorView;
import com.alver.fatefall.poker.plugin.PokerCardLoader;
import com.alver.fatefall.poker.plugin.model.PokerCard;
import com.alver.fsfx.FileSystemEntry;
import com.alver.fsfx.FileSystemFX;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

public class TestApplication extends Application {

	public static class Launcher {
		public static void main(String... args) {
			Application.launch(TestApplication.class, args);
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = createSceneRoot();

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("Test Application");
		stage.centerOnScreen();
		stage.show();
	}

	private Parent createSceneRoot() throws IOException {
		FileSystemFX fileSystem = new FileSystemFX();
		PokerCardLoader cardLoader = new PokerCardLoader();
		Path path = Path.of("example.card");
		FileSystemEntry entry = fileSystem.get(path);

		PokerCard card = cardLoader.load(entry);
		FXMLLoader loader = new FXMLLoader(CardEditorView.FXML);
		Node load = loader.load();
		CardEditorView cardEditorView = loader.getController();
		cardEditorView.setCard(card);

		return new BorderPane(load);
	}
}
