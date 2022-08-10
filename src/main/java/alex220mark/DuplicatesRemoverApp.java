package alex220mark;

import java.io.File;
import java.util.List;
import java.util.Map;

import alex220mark.models.Item;
import alex220mark.utils.CsvReaderWriter;
import alex220mark.utils.DuplicateRemover;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DuplicatesRemoverApp extends Application {

	String selectedFilePath = "no_file_chosen_yet";
	Label statusLabel = new Label("");
	String selectedSaveFilePath = "";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		FileChooser fileChooser = new FileChooser();

		Button selectFileButton = new Button("Select File To Remove Duplicates From");
		Label fileLabel = new Label("No File Chosen");
		Button submitButton = new Button("Submit");
		Button backButton = new Button("Back");

		// intro screen setup
		Button continueButton = new Button("Continue");
		Label introLabel = new Label("CORRECT FORMAT FOR CSV FILE");
		Label formatExplanationLabel = new Label("Only 2 columns, NAME in column A and NUMBER in column B");
		// intro screen image, image is in resources folder - so it searches for
		// resources with this name
		Image correctFormatImage = new Image("Correct_Format.JPG");
		ImageView imageView = new ImageView(correctFormatImage);

		// duplicates remover screen setup
		VBox removeDuplicatesVBox = new VBox(selectFileButton, fileLabel, submitButton, statusLabel, backButton);
		removeDuplicatesVBox.setAlignment(Pos.CENTER);
		removeDuplicatesVBox.setStyle("-fx-background-color: lightblue;");
		Scene removeDuplicatesScene = new Scene(removeDuplicatesVBox, 600, 600);

		// intro screen setup
		VBox introVBox = new VBox(introLabel, formatExplanationLabel, imageView, continueButton);
		introVBox.setAlignment(Pos.CENTER);
		introVBox.setSpacing(20);
		introVBox.setStyle("-fx-background-color: lightgray;");
		Scene introScene = new Scene(introVBox, 600, 600);

		// intro screen button
		continueButton.setOnAction(e -> {
			primaryStage.setScene(removeDuplicatesScene);
		});

		// duplicates screen buttons
		selectFileButton.setOnAction(e -> {
			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			try {
				selectedFilePath = selectedFile.getAbsolutePath();
			} catch (NullPointerException exception) {
			}
			fileLabel.setText(selectedFilePath);
		});

		submitButton.setOnAction(e -> {
			// if no file chosen, error message
			if (selectedFilePath.equals("no_file_chosen_yet")) {
				statusLabel.setTextFill(Color.RED);
				statusLabel.setText("Please select a file...");
			} else {
				// if list comes back empty error message
				List<Item> readListFromFile = CsvReaderWriter.readCsvFile(selectedFilePath);
				if (readListFromFile.isEmpty()) {
					statusLabel.setTextFill(Color.RED);
					statusLabel.setText("Invalid file, pick a .csv file and check format of csv file");
				} else {
					// custom save location dialog opens
					File selectedSaveLocation = fileChooser.showSaveDialog(primaryStage);
					try {
						selectedSaveFilePath = selectedSaveLocation.getAbsolutePath();
					} catch (NullPointerException exception) {
					}
					// removes duplicates from selected file and then exports it to the save
					// location
					Map<String, Integer> finalMapFromReadListFromFile = DuplicateRemover
							.convertListToMapAndRemoveDuplicates(readListFromFile);

					CsvReaderWriter.exportCsvFile(finalMapFromReadListFromFile, selectedSaveFilePath);
					statusLabel.setTextFill(Color.BLACK);
					statusLabel.setText("DONE");
				}
			}
		});

		backButton.setOnAction(e -> {
			primaryStage.setScene(introScene);
		});

		primaryStage.setScene(introScene);
		primaryStage.show();

		primaryStage.setTitle("Duplicate Remover App");

	}

}
