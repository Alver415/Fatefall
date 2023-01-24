package com.alver.fatefall.app.services.fileselector;

import com.jpro.webapi.JProApplication;
import com.jpro.webapi.WebAPI;

import java.io.File;
import java.util.function.Consumer;

public class JProFileSelector extends FileSelectorImpl {

	protected JProApplication application;

	public JProFileSelector(JProApplication application, Consumer<File> onFileSubmitted) {
		super(onFileSubmitted);
		this.application = application;

		WebAPI.FileUploader fileHandler = WebAPI.makeFileUploadNodeStatic(label);
		fileHandler.setSelectFileOnClick(true);
		fileHandler.setSelectFileOnDrop(true);
		fileHandler.fileDragOverProperty().addListener((o,oldV,newV) -> {
			if(newV) {
				label.getStyleClass().add("file-drag");
			} else {
				label.getStyleClass().remove("file-drag");
			}
		});

		//When user selects a file, start uploading it.
		fileHandler.setOnFileSelected(file -> {
			fileHandler.uploadFile();
		});

		// Monitor the progress and update the UI.
		fileHandler.progressProperty().addListener((obs, oldV, newV) -> {
			String percentages = (int) (fileHandler.getProgress() * 100) + "%";
			label.setText(fileHandler.selectedFileProperty().getValue() + " - " + percentages);
		});

		// On completion
		fileHandler.uploadedFileProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				selectedFileProperty.set(newValue);
			}
		});
	}

	@Override
	public void show() {
		application.getWebAPI().openStageAsPopup(stage);
	}

	@Override
	public void close(){
		stage.close();
	}
}
