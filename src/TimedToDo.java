import java.time.LocalDateTime;

public class TimedToDo extends ToDo {
    private LocalDateTime endet;

    TimedToDo(String title, String beschreibung, boolean erledigt, LocalDateTime endet) {
        super(title, beschreibung, erledigt);
        this.endet = endet;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "title='" + this.getTitle() + '\'' +
                ", beschreibung='" + this.getBeschreibung() + '\'' +
                ", erledigt=" + isErledigt() +
                ", endet: " + this.getDeadline() +
                '}';
    }

    public LocalDateTime getDeadline() {
        return endet;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.endet = deadline;
    }
}