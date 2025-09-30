package bsu.edu.cs222;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class UI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private final Button fetchButton = new Button("Fetch Revisions");
    private final TextField inputField = new TextField();
    private final TextArea outputArea = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        outputArea.setEditable(false);
        configure(primaryStage);
        configureFetchButton();
    }

    private void configure(Stage stage) {
        stage.setTitle("Wikipedia Revision Viewer");
        stage.setScene(new Scene(createRoot(), 600, 400));
        stage.sizeToScene();
        stage.show();
    }

    private Pane createRoot() {
        VBox root = new VBox(10);
        root.getChildren().addAll(
                new Label("Enter Wikipedia Article:"), //
                inputField, //
                fetchButton, //
                new Label("Revisions:"), //
                outputArea
        );
        return root;
    }

    private void configureFetchButton() {
        fetchButton.setOnAction(event -> fetchRevisions());
    }

    private void fetchRevisions() {
        String title = inputField.getText().trim();
        if (title.isEmpty()) {
            outputArea.setText("Please enter an article title.");
            return;
        }

        try {
            String json = WikipediaReader.read(title);

            // Handle redirects
            StringBuilder builder = new StringBuilder();
            List<Redirect> redirects = RevisionParser.parseRedirects(json);
            if (!redirects.isEmpty()) {
                for (Redirect r : redirects) {
                    builder.append(r).append("\n");
                }
            }

            // Handle revisions
            List<Revision> revisions = RevisionParser.parseRevisions(json);
            if (revisions.isEmpty()) {
                builder.append("No revisions found for: ").append(title);
            } else {
                builder.append("Recent revisions for ").append(title).append(":\n");
                builder.append(RevisionFormatter.format(revisions));
            }

            outputArea.setText(builder.toString());
        } catch (RuntimeException e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }
}
