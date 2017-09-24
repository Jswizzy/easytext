module easytext.analysis.coleman {
    requires easytext.analysis.api;

    provides easytext.analysis.api.Analyzer with easytext.analysis.coleman.Coleman;
}