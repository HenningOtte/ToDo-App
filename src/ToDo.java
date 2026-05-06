import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ToDo {
    private String title;
    private String beschreibung;
    private boolean erledigt;

    ToDo(String title, String beschreibung, boolean erledigt) {
        this.title = title;
        this.beschreibung = beschreibung;
        this.erledigt = erledigt;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "title='" + title + '\'' +
                ", beschreibung='" + beschreibung + '\'' +
                ", erledigt=" + erledigt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return erledigt == toDo.erledigt && Objects.equals(title, toDo.title) && Objects.equals(beschreibung, toDo.beschreibung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, beschreibung, erledigt);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public boolean isErledigt() {
        return erledigt;
    }

    public void setErledigt(boolean erledigt) {
        this.erledigt = erledigt;
    }

    public JPanel getJCard() {
        JPanel toDo = new JPanel(new BorderLayout());
        toDo.setPreferredSize(new Dimension(Integer.MAX_VALUE, 80));
        toDo.add(new Label(this.title), BorderLayout.NORTH);
        toDo.add(new Label(this.beschreibung), BorderLayout.CENTER);
        toDo.add(new Checkbox(), BorderLayout.EAST);

        return toDo;
    }
}
