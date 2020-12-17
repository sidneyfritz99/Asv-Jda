import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.utils.MarkdownSanitizer;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.SelfUserImpl;
import net.dv8tion.jda.internal.utils.config.AuthorizationConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.dv8tion.jda.api.utils.MarkdownUtil.*;

public class AsvJdaCoreTest {

    JDAImpl jda;

    final String jsonWithAttachment = "{\n" +
            "  \"reactions\": [\n" +
            "  ],\n" +
            "  \"attachments\": [{\n" +
            "\t\t\"id\": 1,\n" +
            "\t\t\"filename\": \"Red_Panda.jpg\",\n" +
            "\t\t\"size\": 3652000,\n" +
            "\t\t\"url\": \"https://upload.wikimedia.org/wikipedia/commons/c/c6/Red_Panda.JPG\",\n" +
            "\t\t\"proxy_url\": \"https://upload.wikimedia.org/wikipedia/commons/c/c6/Red_Panda.JPG\" \n" +
            "\t}],\n" +
            "  \"tts\": false,\n" +
            "  \"embeds\": [],\n" +
            "  \"timestamp\": \"2017-07-11T17:27:07.299000+00:00\",\n" +
            "  \"mention_everyone\": false,\n" +
            "  \"id\": \"334385199974967042\",\n" +
            "  \"pinned\": false,\n" +
            "  \"edited_timestamp\": null,\n" +
            "  \"author\": {\n" +
            "    \"username\": \"Mason\",\n" +
            "    \"discriminator\": \"9999\",\n" +
            "    \"id\": \"53908099506183680\",\n" +
            "    \"avatar\": \"a_bab14f271d565501444b2ca3be944b25\"\n" +
            "  },\n" +
            "  \"mention_roles\": [],\n" +
            "  \"content\": \"Supa Hot\",\n" +
            "  \"channel_id\": \"290926798999357250\",\n" +
            "  \"mentions\": [],\n" +
            "  \"type\": 0\n" +
            "}";

    final String jsonMessage = "{\n" +
            "  \"reactions\": [\n" +
            "    {\n" +
            "      \"count\": 1,\n" +
            "      \"me\": false,\n" +
            "      \"emoji\": {\n" +
            "        \"id\": null,\n" +
            "        \"name\": \"\uD83D\uDD25\"\n" +
            "      }\n" +
            "    }\n" +
            "  ],\n" +
            "  \"attachments\": [],\n" +
            "  \"tts\": false,\n" +
            "  \"embeds\": [],\n" +
            "  \"timestamp\": \"2017-07-11T17:27:07.299000+00:00\",\n" +
            "  \"mention_everyone\": false,\n" +
            "  \"id\": \"334385199974967042\",\n" +
            "  \"pinned\": false,\n" +
            "  \"edited_timestamp\": null,\n" +
            "  \"author\": {\n" +
            "    \"username\": \"Some user\",\n" +
            "    \"discriminator\": \"9999\",\n" +
            "    \"id\": \"53908099506183680\",\n" +
            "    \"avatar\": \"a_bab14f271d565501444b2ca3be944b25\"\n" +
            "  },\n" +
            "  \"mention_roles\": [],\n" +
            "  \"content\": \"Hello World\",\n" +
            "  \"channel_id\": \"290926798999357250\",\n" +
            "  \"mentions\": [],\n" +
            "  \"type\": 0\n" +
            "}";

    final String privateChannelJSON = "{\n" +
            "  \"last_message_id\": \"2232410021215021450\",\n" +
            "  \"type\": 1,\n" +
            "  \"id\": \"208765123115610528\",\n" +
            "  \"recipients\": [\n" +
            "    {\n" +
            "      \"username\": \"Some user\",\n" +
            "      \"discriminator\": \"9999\",\n" +
            "      \"id\": \"82198898841029460\",\n" +
            "      \"avatar\": \"33ecab261d4681afa4d85a04691c4a01\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";


    @BeforeEach
    public void setup()
    {
        jda = new JDAImpl(new AuthorizationConfig("no token"));
        // Set self user so that JDA thinks a session is started
        jda.setSelfUser(new SelfUserImpl(0, jda));
    }

    @Test
    public void testFileTransfer() {
        DataObject response = DataObject.fromJson(jsonWithAttachment);

        MessageChannel channel = jda.getEntityBuilder().createPrivateChannel(DataObject.fromJson(privateChannelJSON));
        Message msg = jda.getEntityBuilder().createMessage(response, channel, false);

        Assertions.assertEquals("Red_Panda.jpg", msg.getAttachments().get(0).getFileName());
        Assertions.assertEquals("https://upload.wikimedia.org/wikipedia/commons/c/c6/Red_Panda.JPG", msg.getAttachments().get(0).getUrl());
        Assertions.assertEquals(3652000, msg.getAttachments().get(0).getSize());
        Assertions.assertEquals("jpg", msg.getAttachments().get(0).getFileExtension());

    }

    @Test
    public void parseMessageJSON()
    {
        DataObject response = DataObject.fromJson(jsonMessage);

        MessageChannel channel = jda.getEntityBuilder().createPrivateChannel(DataObject.fromJson(privateChannelJSON));
        Message msg = jda.getEntityBuilder().createMessage(response, channel, false);

        Assertions.assertEquals("Hello World", msg.getContentDisplay());
        Assertions.assertEquals("Some user", msg.getAuthor().getName());
        Assertions.assertEquals(1, msg.getReactions().get(0).getCount());
    }

    @Test
    public void parseMessageType() {
        DataObject response = DataObject.fromJson(jsonMessage);
        MessageChannel privateChannel = jda.getEntityBuilder().createPrivateChannel(DataObject.fromJson(privateChannelJSON));

        Message msg = jda.getEntityBuilder().createMessage(response, privateChannel, false);

        Assertions.assertEquals(0, msg.getType().getId());
    }

}
