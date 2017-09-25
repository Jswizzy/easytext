package easytext.analysis.coleman;

import easytext.analysis.api.Analyzer;
import easytext.analysis.api.Fast;

import java.util.List;

@Fast
public class Coleman implements Analyzer {

    public static final String NAME = "Coleman-Liau";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public double analyze(List<List<String>> sentences) {
        float totalsentences = sentences.size();
        float words = sentences.stream().mapToInt(List::size).sum();
        float letters = sentences.stream().flatMapToInt(sentence -> sentence.stream().mapToInt(String::length)).sum();

        return 0.0588 * (letters / (words / 100)) - 0.296 * (totalsentences / (words / 100)) - 15.8;

    }

}
