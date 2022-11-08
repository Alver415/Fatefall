import com.alver.fxmlsaver.FXMLSaver;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FXMLSaverTest {
	@BeforeAll
	public static void setup() {
		Platform.startup(() -> {
		});
	}

	@Test
	public void test() throws Exception {

		Path source = Path.of("src/test/resources/source.fxml");
		Path target = Path.of("src/test/resources/target.fxml");

		Node loaded = FXMLLoader.load(source.toUri().toURL());
		FXMLSaver.save(target.toFile(), loaded);
	}
}
