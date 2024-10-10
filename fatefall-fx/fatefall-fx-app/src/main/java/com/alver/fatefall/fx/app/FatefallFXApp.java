package com.alver.fatefall.fx.app;

import com.alver.fatefall.fx.app.view.entity.card.CardView;
import com.alver.fatefall.fx.app.view.entity.workspace.WorkspaceView;
import com.alver.fatefall.fx.core.model.CardFX;
import com.alver.fatefall.fx.core.model.WorkspaceFX;
import com.alver.fsfx.FileSystemEntry;
import com.alver.fsfx.FileSystemFX;
import com.alver.fsfx.util.ComplexBinding;
import com.alver.fsfx.util.StringToJsonConverter;
import com.alver.fsfx.view.ModelCache;
import com.alver.fsfx.view.ModelMap;
import com.alver.jfxtra.lib.io.SystemIO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.util.List;

import static com.alver.fsfx.view.DefaultMappings.DEFAULT_STRING_CONVERTER;

@SpringBootApplication
@ComponentScan("com.alver.fatefall")
@PropertySource("classpath:/fatefall-fx-app.properties")
public class FatefallFXApp {

	private static final Logger log = LoggerFactory.getLogger(FatefallFXApp.class);

	public static void main(String... args) {
		com.sun.javafx.util.Logging.getCSSLogger().disableLogging();
		SystemIO.overrideSystemDefaults();
		Application.launch(FatefallFXApplication.class, args);
	}

	@Bean
	public ObjectMapper getObjectMapper(List<Module> modules) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModules(modules);
		return objectMapper;
	}

	@Bean
	public LoadingCache<String, Image> getImageCache() {
		return CacheBuilder.newBuilder().build(new CacheLoader<>() {
			public Image load(String key) { // no checked exception
				return new Image(key, true);
			}
		});
	}

	@Bean
	public ObservableList<WorkspaceFX> getWorkspaces() {
		return FXCollections.observableArrayList();
	}

	@Bean
	public FileSystemFX getFileSystem() throws IOException {
		return new FileSystemFX();
	}

	@Bean
	public ModelMap getModelMap(FileSystemFX fileSystem) {
		ModelMap modelMap = new ModelMap();
		modelMap.addMapping("card", CardFX.class, this::toCardFX, CardView.FXML);
		modelMap.addMapping("collection", WorkspaceFX.class, this::toWorkspaceFX, WorkspaceView.FXML);
		return modelMap;
	}

	@Bean
	public ModelCache getModelCache(ModelMap modelMap) {
		return new ModelCache(modelMap);
	}


	public CardFX toCardFX(FileSystemEntry entry) {
		CardFX model = new CardFX();
		StringProperty string = new SimpleStringProperty(model, "string");
		string.set(DEFAULT_STRING_CONVERTER.to(entry.getContent()));
		ComplexBinding.bindBidirectional(
				entry.contentProperty(),
				string,
				DEFAULT_STRING_CONVERTER);

		StringToJsonConverter STRING_TO_JSON_CONVERTER = new StringToJsonConverter();
		ObjectProperty<JsonNode> json = new SimpleObjectProperty<>(model, "json");
		json.set(STRING_TO_JSON_CONVERTER.to(string.get()));
		ComplexBinding.bindBidirectional(
				string,
				json,
				STRING_TO_JSON_CONVERTER);

		bidirectionallyBindJson(json, model.nameProperty());

		return model;
	}


	public WorkspaceFX toWorkspaceFX(FileSystemEntry entry) {
		WorkspaceFX model = new WorkspaceFX();

		ObservableValue<FileSystemEntry> map = entry.childrenProperty()
				.map(children -> children.get("collection.json"));
		map.subscribe(childEntry -> {
			if (childEntry == null) {
				model.setName(null);
				return;
			}
			StringProperty string = new SimpleStringProperty(model, "string");
			string.set(DEFAULT_STRING_CONVERTER.to(childEntry.getContent()));
			ComplexBinding.bindBidirectional(
					childEntry.contentProperty(),
					string,
					DEFAULT_STRING_CONVERTER);

			StringToJsonConverter STRING_TO_JSON_CONVERTER = new StringToJsonConverter();
			ObjectProperty<JsonNode> json = new SimpleObjectProperty<>(model, "json");
			json.set(STRING_TO_JSON_CONVERTER.to(string.get()));
			ComplexBinding.bindBidirectional(
					string,
					json,
					STRING_TO_JSON_CONVERTER);


			bidirectionallyBindJson(json, model.nameProperty());

		});
		return model;
	}

	private static void bidirectionallyBindJson(Property<JsonNode> json, Property<String> prop) {
		json.subscribe(j -> prop.setValue(j == null ? "" : j.findPath(prop.getName()).textValue()));
		prop.addListener((_, _, p) -> {
			JsonNode node = json.getValue().deepCopy();
			if (node instanceof ObjectNode object) {
				object.set(prop.getName(), new TextNode(p));
				json.setValue(node);
			} else {
				log.warn("Invalid JSON: {}", json);
			}
		});
	}
}