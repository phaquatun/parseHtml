package com.mycompany.parsehtml;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ConditonsTag {

    private String valOnLineTag, endTag;
    private List<String> listNextTag = new ArrayList<>();
    private int anountPass;
    private boolean closeParse;

    public ConditonsTag valInLineTag(String valOnLineTag) {
        this.valOnLineTag = valOnLineTag;
        return this;
    }

    public ConditonsTag nextTag(String nextTag, int local) {
        for (int i = 0; i < local; i++) {
            listNextTag.add(nextTag);
        }
        return this;
    }

    public ConditonsTag nextTag(String nextTag) {
        listNextTag.add(nextTag);
        return this;
    }

    public ConditonsTag parseNextTag(String valuStart, String valueEnd) {

        return this;
    }

    public ConditonsTag end(String endTag) {
        this.endTag = endTag;
        return this;
    }

    public ConditonsTag setAnountPass(int anountPass) {
        this.anountPass = anountPass;
        return this;
    }

    public ConditonsTag setCloseParse(boolean closeParse) {
        this.closeParse = closeParse;
        return this;
    }
    
    

    @Override
    public String toString() {
        return "ConditonsTag{" + "valOnLineTag=" + valOnLineTag + ", endTag=" + endTag + ", listNextTag=" + listNextTag + ", anountPass=" + anountPass + '}';
    }

}
