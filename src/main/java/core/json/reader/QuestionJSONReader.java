package core.json.reader;

import database.models.template.Question;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibnujakaria on 09/05/17.
 */
public class QuestionJSONReader {

    public static Question parseQuestion (JSONObject question) {
        Question result = null;

        try {
            result = new Question(
                    question.getInt("id"),
                    question.getString("title"),
                    question.getString("description"),
                    question.getString("answer"),
                    question.getInt("is_anonim") == 1,
                    question.getString("created_at"),
                    question.getString("updated_at")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<Question> parseQuestions (JSONArray questions) {
        List<Question> results = new ArrayList<Question>();

        for (int i = 0; i < questions.length(); i++) {
            try {
                results.add(parseQuestion(questions.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }
}
