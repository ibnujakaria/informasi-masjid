package core.json.generator;

import database.models.jooq.User;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;

/**
 * Created by ibnujakaria on 09/05/17.
 */
public class QuestionJSONGenerator {

    public static JSONObject generateArray (Result<Record> questions) {
        JSONObject questionsObject = new JSONObject();
        JSONArray items = new JSONArray();

        for (Record question : questions) {
            JSONObject questionObject = new JSONObject();

            questionObject.put("id", question.get("id"));
            questionObject.put("title", question.get("title"));
            questionObject.put("description", question.get("description"));
            questionObject.put("answer", question.get("answer"));
            questionObject.put("is_anonim", question.get("is_anonim"));
            questionObject.put("created_at", question.get("created_at"));
            questionObject.put("updated_at", question.get("updated_at"));
            questionObject.put("user", UserJSONGenerator.generate(User.getUserById((int) question.get("user_id"))));
            questionObject.put("ustadz", UserJSONGenerator.generate(User.getUserById((int) question.get("ustadz_id"))));

            items.add(questionObject);
        }

        questionsObject.put("questions", items);
        return questionsObject;
    }
}
