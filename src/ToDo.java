import java.util.Objects;

public class ToDo {

    // Title of the ToDo
    private String title;

    // Description of the ToDo
    private String description;

    // Completion status
    private boolean completed;

    // Constructor
    ToDo(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    @Override
    public String toString() {

        // Returns ToDo as string
        return "ToDo{" +
                "title='" + title + '\'' +
                ", beschreibung='" + description + '\'' +
                ", erledigt=" + completed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        // Check if objects are equal
        if (o == null || getClass() != o.getClass()) return false;

        ToDo toDo = (ToDo) o;
        return completed == toDo.completed && Objects.equals(title, toDo.title) && Objects.equals(description, toDo.description);
    }

    @Override
    public int hashCode() {

        // Generate hash code
        return Objects.hash(title, description, completed);
    }

    // Returns title
    public String getTitle() {
        return this.title;
    }

    // Sets new title
    public void setTitle(String title) {
        this.title = title;
    }

    // Returns description
    public String getDescription() {
        return description;
    }

    // Sets new description
    public void setDescription(String description) {
        this.description = description;
    }

    // Returns completion status
    public boolean isCompleted() {
        return completed;
    }

    // Sets completion status
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
