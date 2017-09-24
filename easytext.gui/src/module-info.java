module easytext.gui {
    exports easytext.gui to javafx.graphics;

    requires easytext.analysis.api;
    requires javafx.graphics;
    requires javafx.controls;

    uses easytext.analysis.api.Analyzer;
}