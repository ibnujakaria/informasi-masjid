package core.json.generator;

import org.jooq.Record;
import org.jooq.tools.json.JSONObject;

/**
 * Created by ibnujakaria on 09/05/17.
 */
public class UserJSONGenerator {

    public static JSONObject generate (Record user) {
        JSONObject userObject = new JSONObject();

        if (user != null) {
            userObject.put("id", user.get("id"));
            userObject.put("name", user.get("name"));
            userObject.put("email", user.get("email"));
            userObject.put("role", user.get("role"));
            userObject.put("is_ustadz", user.get("is_ustadz"));
            userObject.put("created_at", user.get("created_at"));
            userObject.put("updated_at", user.get("updated_at"));
        }

        return userObject;
    }
}
