import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TimedToDo timedToDo = (TimedToDo) o;
        return Objects.equals(endet, timedToDo.endet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), endet);
    }

    public LocalDateTime getDeadline() {
        return endet;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.endet = deadline;
    }
}