package com.alver.fatefall.fx.app.view.entity.card;

import com.alver.springfx.annotations.Prototype;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EditableNodesConfiguration {

	@Prototype
	public StackPane getStackPane(){
		return new StackPane();
	}
	@Prototype
	public ImageView getImageView(){
		return new ImageView();
	}
	@Prototype
	public TextArea getTextArea(){
		return new TextArea();
	}
}
