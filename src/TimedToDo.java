import java.time.LocalDateTime;
import java.util.Objects;

public class TimedToDo extends ToDo {

    // Deadline of the ToDo
    private LocalDateTime endet;

    // Constructor
    TimedToDo(String title, String description, boolean completed, LocalDateTime deadline) {
        super(title, description, completed);
        this.endet = deadline;
    }

    @Override
    public String toString() {

        // Returns ToDo as string
        return "ToDo{" +
                "title='" + this.getTitle() + '\'' +
                ", beschreibung='" + this.getDescription() + '\'' +
                ", erledigt=" + isCompleted() +
                ", endet: " + this.getDeadline() +
                '}';
    }


    @Override
    public boolean equals(Object o) {

        // Check if objects are equal
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TimedToDo timedToDo = (TimedToDo) o;
        return Objects.equals(endet, timedToDo.endet);
    }

    @Override
    public int hashCode() {
        // Generate hash code
        return Objects.hash(super.hashCode(), endet);
    }

    // Returns deadline
    public LocalDateTime getDeadline() {
        return endet;
    }

    // Sets new deadline
    public void setDeadline(LocalDateTime deadline) {
        this.endet = deadline;
    }
}