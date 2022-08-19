package com.mycompany.parsehtml;

public class HtmlHandler {

    private static ThreadLocal<HtmlHandler> local = new ThreadLocal<>().withInitial(() -> {
        return new HtmlHandler();
    });

    public static HtmlHandler getInstance() {
        return local.get();
    }

    public ParseHtmlLineByLine htmlLinebyLine = new ParseHtmlLineByLine();
}
