package easytext.gui;

import easytext.analysis.api.Analyzer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {

    private static ComboBox<String> algorithm;
    private static TextArea input;
    private static Text output;
    private final Map<String, Analyzer> analyzers = new HashMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("EasyText");
        Button btn = new Button();
        btn.setText("Calculate");
        btn.setOnAction(event ->
                output.setText(analyze(input.getText(), algorithm.getValue()))
        );

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(3));
        vbox.setSpacing(3);
        Text title = new Text("Choose an algorithm:");
        algorithm = new ComboBox<>();

        Iterable<Analyzer> analyzerServices = ServiceLoader.load(Analyzer.class);

        for(Analyzer analyzer: analyzerServices) {
            analyzers.put(analyzer.getName(), analyzer);
            algorithm.getItems().add(analyzer.getName());
            System.out.println(analyzer.getName());
        }

        vbox.getChildren().add(title);
        vbox.getChildren().add(algorithm);
        vbox.getChildren().add(btn);

        input = new TextArea();
        output = new Text();
        BorderPane pane = new BorderPane();
        pane.setRight(vbox);
        pane.setCenter(input);
        pane.setBottom(output);
        primaryStage.setScene(new Scene(pane, 300, 250));
        primaryStage.show();
    }

    private String analyze(String input, String algorithm) {
        List<List<String>> sentences = toSentences(input);

        return algorithm + ": " + analyzers.get(algorithm).analyze(sentences);
    }


    public static List<List<String>> toSentences(String text) {
        String removedBreaks = text.replaceAll("\\r?\\n", " ");
        ArrayList<List<String>> sentences = new ArrayList<>();
        for(String rawSentence: removedBreaks.split("[\\.\\?\\!]")) {
            List<String> words = toWords(rawSentence);
            if(words.size() > 0) {
                sentences.add(words);
            }
        }

        return sentences;
    }

    public static List<String> toWords(String sentence) {
        String[] rawWords = sentence.split("\\s+");
        List<String> words = new ArrayList<>();
        for(String rawWord: rawWords) {
            String word = rawWord.replaceAll("\\W", "");
            if(word.length() > 0) {
                words.add(word);
            }
        }

        return words;
    }
}