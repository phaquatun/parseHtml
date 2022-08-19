package TestCase;

import com.mycompany.parsehtml.ParseHtmlLineByLine;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestCase {

    private String name, dame, kill, link, time;
    private FormatEx fe;
    private boolean startTagHtml;

    public static void main(String[] args) throws IOException {
        TestCase test = new TestCase();
        test.runParseHtml();
    }

    public void runParseHtml() throws IOException {
        long timeStart = System.currentTimeMillis();

        ParseHtmlLineByLine parseHtml = new ParseHtmlLineByLine().addNode("name", "kill", "link", "time");

        String pathWotEu = "E:\\M7Require\\resWotEu.txt";
        String pathWotCom = "C:\\Users\\Admin\\Desktop\\WotCom.txt";

        Files.readAllLines(Paths.get(pathWotCom), StandardCharsets.UTF_8).forEach((line) -> {
//            System.out.println(line);
            time = parseHtml.getNode("time").tag("<p data-translate data-translate-params-date=\"'")
                    .handleLine(line)
                    .parseTagStart().endParse("'\"").getLine().trim();

            name = parseHtml.getNode("name").tag("<img src=\"//replayswows.com/dist//images/ships_type", "</span>").handleLine(line)
                    .getLine().replace("</span>", "").trim();

            dame = parseHtml.getNode("dame").tag("<div class=\"ribbon_content\">", "</div>").handleLine(line).endParse("</div>").getLine().replace(" ", "");

//            parseHtml.handleStartTag(line, "<li class=\"clearfix\">", () -> {
//                name = parseHtml.getKeyOfConditionTag("name").tag("<li><b>Tank:</b> <a class=\"link--white\" href=").handleLine(line)
//                        .parseTagStart("\">").endParse("</a></li>").getLine().trim();
//
//                dame = parseHtml.getKeyOfConditionTag("kill").tag("<li><i class=\"i-16_frags\"></i>").handleLine(line)
//                        .parseTagStart().endParse("<").getLine().trim();
//
//                kill = parseHtml.getKeyOfConditionTag("dame").tag("<li><i class=\"i-16_dmg\"></i>").handleLine(line)
//                        .parseTagStart().endParse("<").getLine().trim();
//
//                link = parseHtml.getKeyOfConditionTag("link").tag("<div class=\"r-act\">", "<a target=\"_self\" href=\"").handleLine(line)
//                        .parseTagStart().endParse("\"").getLine().trim();
//            })
//                    .handleEndTag(line, "<a target=\"_self\" href=\"", () -> {
//                        var fe = new FormatEx(name, dame, kill, link);
//                        System.out.println(fe.toString());
//                    });
        });

        System.out.println(new FormatEx(name, dame, kill, link, time).toString());
        System.out.println(">>> end " + (System.currentTimeMillis() - timeStart));
    }
}

class FormatEx {

    String name, dame, kill, link, time;

    public FormatEx(String name, String dame, String kill, String link, String time) {
        this.name = name;
        this.dame = dame;
        this.kill = kill;
        this.link = link;
        this.time = time;
    }

    @Override
    public String toString() {
        return "FormatEx{" + "name=" + name + ", dame=" + dame + ", kill=" + kill + ", link=" + link + ", time=" + time + '}';
    }

}
