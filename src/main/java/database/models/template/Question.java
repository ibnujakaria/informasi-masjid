package database.models.template;

/**
 * Created by ibnujakaria on 09/05/17.
 */
public class Question {
    private int id;
    private String title;
    private String description;
    private String answer;
    private boolean is_anonim;
    private String created_at;
    private String updated_at;

    public Question(int id, String title, String description, String answer, boolean is_anonim, String created_at, String updated_at) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.answer = answer;
        this.is_anonim = is_anonim;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isIs_anonim() {
        return is_anonim;
    }

    public void setIs_anonim(boolean is_anonim) {
        this.is_anonim = is_anonim;
    }

    @Override
    public String toString() {
        String result = "";
        result += "Title: " + title + "\r\n";
        result += "Answer: " + answer + "\r\n";
        return result;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
