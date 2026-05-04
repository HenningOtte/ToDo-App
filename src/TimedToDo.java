import java.time.LocalDateTime;

public class TimedToDo extends ToDo {
    private LocalDateTime endet;

    TimedToDo(String title, String beschreibung, boolean erledigt, LocalDateTime endet) {
        super(title, beschreibung, erledigt);
        this.endet = endet;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.endet = deadline;
    }

    public LocalDateTime getDeadline() {
        return endet;
    }
}