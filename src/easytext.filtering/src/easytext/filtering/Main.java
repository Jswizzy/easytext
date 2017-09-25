package easytext.filtering;

import easytext.analysis.api.Analyzer;
import easytext.analysis.api.Fast;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        ServiceLoader<Analyzer> analyzers =
                ServiceLoader.load(Analyzer.class);

        analyzers.stream()
                .filter(provider -> isFast(provider.type()))
                .map(ServiceLoader.Provider::get)
                .forEach(analyzer -> System.out.println(analyzer.getName()));
    }

    private static boolean isFast(Class<? extends Analyzer> aClass) {
        return aClass.isAnnotationPresent(Fast.class)
                && aClass.getAnnotation(Fast.class).value();
    }
}
