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
}
