import net.dv8tion.jda.api.utils.MarkdownSanitizer;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.dv8tion.jda.api.utils.MarkdownUtil.*;

public class AsvJsonTest
{
    private static final String json = "{\"array\" : [1,2,3,4,5,6,7,8,9,10]}";
    private static final int[] expetedResult = {1,2,3,4,5,6,7,8,9,10};

    @Test
    public void testParseJsonArray()
    {
        DataObject jsonObject = DataObject.fromJson(json);
        DataArray jsonArray = jsonObject.getArray("array");
        int[] result = new int[10];
        for (int i = 0; i < result.length; i++) {
            result[i] = jsonArray.getInt(i);
        }
        Assertions.assertArrayEquals(expetedResult, result);
    }
}
