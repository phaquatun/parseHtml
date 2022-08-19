package com.mycompany.parsehtml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

public class ParseHtmlLineByLine {

    private Map<String, ConditonsTag> conditionTag = new HashMap<>();
    private String keyOfCondTag, afLine;
    private boolean starTag, endTag;

    public ParseHtmlLineByLine addNode(String... key) {
        for (int i = 0; i < key.length; i++) {
            conditionTag.put(key[i], new ConditonsTag());
        }
        return this;
    }

    public ParseHtmlLineByLine tag(String... valueTag) {
        var condTag = conditionTag.get(keyOfCondTag);
        boolean checkCloseParse = condTag.isCloseParse();
        if (!checkCloseParse) {
            var listNextTag = condTag.getListNextTag();

            for (int i = 0; i < valueTag.length; i++) {
                listNextTag.add(valueTag[i]);
            }
            condTag.setCloseParse(true);
        }

        return this;
    }

    public ParseHtmlLineByLine getNode(String key) {
        this.keyOfCondTag = key;

        return this;
    }

    public ParseHtmlLineByLine handleLine(String lineByLine) {
        var condTag = conditionTag.get(keyOfCondTag);
        int anountPass = condTag.getAnountPass();

        if (lineByLine == null | lineByLine.isEmpty() | !lineByLine.contains(condTag.getListNextTag().get(anountPass))) {
            String val = condTag.getValOnLineTag() == null ? "x" : condTag.getValOnLineTag();
            condTag.valInLineTag(val);
        }
        if (lineByLine.contains(condTag.getListNextTag().get(anountPass))) {
            ++anountPass;
            condTag.setAnountPass(anountPass);
        }

        boolean checkPass = condTag.getAnountPass() == (condTag.getListNextTag().size());
        if (checkPass) {
            condTag.setAnountPass(0)
                    .valInLineTag(lineByLine);
//            return lineByLine; // ok 
        }

        return this;
    }

    public String getLine() {
        var condTag = conditionTag.get(keyOfCondTag).getValOnLineTag();
        return condTag;
    }

    public String handleResult(ResultHtml html) {
        var condTag = conditionTag.get(keyOfCondTag);
        if (condTag.getValOnLineTag() != null) {
            return html.handle(condTag.getValOnLineTag());
        }
        return null;
    }

    public ParseHtmlLineByLine parseTagStart() {
        var condTag = conditionTag.get(keyOfCondTag);
        var list = condTag.getListNextTag();
        int local = list.size() - 1;
        return parseTag(condTag, list.get(local));
    }

    public ParseHtmlLineByLine parseTagStart(String valParse) {
        var condTag = conditionTag.get(keyOfCondTag);
        return parseTag(condTag, valParse);
    }

    public ParseHtmlLineByLine endParse(String valParse) {
        var condTag = conditionTag.get(keyOfCondTag);
        String val = condTag.getValOnLineTag();
        if (val.contains(valParse)) {
            condTag.valInLineTag(val.split(valParse)[0]);
        }
        return this;
    }

    ParseHtmlLineByLine parseTag(ConditonsTag condTag, String valParse) {
        String val = condTag.getValOnLineTag();
        if (val.contains(valParse)) {
            condTag.valInLineTag(val.split(valParse)[1]);
        }
        return this;
    }

    public ParseHtmlLineByLine handleStartTag(String line, String valStarTag, HandleInTag tag) {
        if (line.contains(valStarTag)) {
            starTag = true;
        }
        if (starTag) {
            tag.handle();
        }
        return this;
    }

    public ParseHtmlLineByLine handleEndTag(String line, String valEndTag, HandleInTag tag) {

        if (line.contains(valEndTag) & starTag) {
            tag.handle();
            starTag = false;
        }
        return this;
    }

}
