module easytext.analysis.kincaid {
    requires easytext.analysis.api;

    provides easytext.analysis.api.Analyzer with easytext.analysis.kincaid.FleschKincaid;
}