package org.example.AIIntegration;
import java.util.HashMap;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class OpenAIUtils {
    private static final String OPENAI_API_KEY = "Your OpenAI Key";
    private static final String OPENAI_ENDPOINT = "https://api.openai.com/v1/chat/completions";

    public String getCaptchaValueFromOpenAI(String imageURL) throws Exception {
        String captchaValue="";
        String Prompt="Give only the captcha value from the image";
        try
        {
            captchaValue=CallOpenAI(Prompt,imageURL);
        }
        catch(Exception e){
        }
        finally {
            return captchaValue;
        }

    }



    public String CallOpenAI(String Prompt,String imageURLFromSRC)
    {
//        // Create the JSON request body
// Creating the JSON payload using JSONSimple objects
        JSONObject payload = new JSONObject();

        // Adding "model" key-value
        payload.put("model", "gpt-4o-mini");

        // Creating the "messages" array
        JSONArray messages = new JSONArray();

        // Creating the first message object
        JSONObject message = new JSONObject();
        message.put("role", "user");

        // Creating the "content" array
        JSONArray content = new JSONArray();

        // Adding the first content object (text)
        JSONObject textContent = new JSONObject();
        textContent.put("type", "text");
        textContent.put("text", Prompt);
        content.add(textContent);

        // Adding the second content object (image_url)
        JSONObject imageContent = new JSONObject();
        imageContent.put("type", "image_url");

        // Creating the image_url object
        JSONObject imageUrl = new JSONObject();
        imageUrl.put("url", imageURLFromSRC);
        imageContent.put("image_url", imageUrl);

        content.add(imageContent);

        // Adding the content array to the message object
        message.put("content", content);

        // Adding the message object to the messages array
        messages.add(message);

        // Adding the messages array to the payload
        payload.put("messages", messages);

        // Adding "max_tokens" key-value
        payload.put("max_tokens", 2000);
//        String json="\"{\\r\\n  \\\"model\\\": \\\"gpt-4o-mini\\\",\\r\\n  \\\"messages\\\": [\\r\\n    {\\r\\n      \\\"role\\\": \\\"user\\\",\\r\\n      \\\"content\\\": [\\r\\n        {\\r\\n          \\\"type\\\": \\\"text\\\",\\r\\n          \\\"text\\\": \\\""+Prompt+"\\\"\\r\\n        },\\r\\n        {\\r\\n          \\\"type\\\": \\\"image_url\\\",\\r\\n          \\\"image_url\\\": {\\r\\n            \\\"url\\\": \\\""+imageURL+"\\\"\\r\\n          }\\r\\n        }\\r\\n      ]\\r\\n    }\\r\\n  ],\\r\\n  \\\"max_tokens\\\": 2000\\r\\n}\"";
                Response response=RestAssured.given().
                header("Authorization","Bearer "+OPENAI_API_KEY).
                header("Content-Type","application/json").
                body(payload.toJSONString())
            .post(OPENAI_ENDPOINT);
        if (response.getStatusCode() == 200) {
            System.out.println(response.getBody().jsonPath().getString("choices[0].message.content"));
        } else {
            System.out.println("Error: " + response.getStatusCode() + " - " + response.getBody().asString());
        }
        return response.getBody().jsonPath().getString("choices[0].message.content");
    }

}
