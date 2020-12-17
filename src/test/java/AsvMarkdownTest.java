import net.dv8tion.jda.api.utils.MarkdownSanitizer;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.dv8tion.jda.api.utils.MarkdownUtil.*;

public class AsvMarkdownTest {
    private static final String MARKDOWN_TEXT = "ASV";

    private MarkdownSanitizer markdown;
    @BeforeEach
    public void setup()
    {
        markdown = new MarkdownSanitizer().withStrategy(MarkdownSanitizer.SanitizationStrategy.REMOVE);
    }

    @Test
    public void testCreateSimpleMarkdown()
    {
        //MARKDOWN_TEXT = "ASV"
        Assertions.assertEquals("_" + MARKDOWN_TEXT + "_", italics(MARKDOWN_TEXT));
        Assertions.assertEquals("__" + MARKDOWN_TEXT + "__", underline(MARKDOWN_TEXT));
        Assertions.assertEquals("~~" + MARKDOWN_TEXT + "~~", strike(MARKDOWN_TEXT));
        Assertions.assertEquals("**" + MARKDOWN_TEXT + "**", bold(MARKDOWN_TEXT));

        Assertions.assertEquals("`" + MARKDOWN_TEXT + "`", monospace(MARKDOWN_TEXT));
        Assertions.assertEquals("```" + MARKDOWN_TEXT + "```", codeblock(MARKDOWN_TEXT));
        Assertions.assertEquals("```java\n" + MARKDOWN_TEXT + "````", codeblock("java", MARKDOWN_TEXT));
    }

    @Test
    public void NestedMarkdown()
    {
        //MARKDOWN_TEXT = "ASV"
        Assertions.assertEquals("**_" + MARKDOWN_TEXT + "_ _" + MARKDOWN_TEXT + "_**", bold(italics(MARKDOWN_TEXT) + " " + italics(MARKDOWN_TEXT)));
        Assertions.assertEquals("**~~___" + MARKDOWN_TEXT + "___~~**", bold(strike(underline(italics(MARKDOWN_TEXT)))));
        Assertions.assertEquals("||**~~___" + MARKDOWN_TEXT + "___~~**||", spoiler(bold(strike(underline(italics(MARKDOWN_TEXT))))));
    }

    @Test
    public void testStrippingOfMarkDown() {
        Assertions.assertEquals("ASV", markdown.compute("**__||ASV||__**"));
        Assertions.assertEquals("||ASV", markdown.compute("**__||ASV__**"));
        Assertions.assertEquals("||ASV|-|", markdown.compute("**__||ASV|-|__**"));
        Assertions.assertEquals("**ASV", markdown.compute("**ASV"));
        Assertions.assertEquals("**ASV", markdown.compute("ASV**"));
        Assertions.assertEquals("ASV", markdown.compute("****ASV****"));
    }
}
