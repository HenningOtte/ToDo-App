import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ToDoManager {
    // Liste ToDos
    ArrayList<ToDo> toDos = new ArrayList<>();

    public void add(ToDo toDo) {
        toDos.add(toDo);
    }
    // remove

    public void remove(ToDo toDo) {
        toDos.remove(toDo);
    }

    public ArrayList<TimedToDo> getTimedToDos() {
        ArrayList<TimedToDo> timedToDos = new ArrayList<>();

        for (int i = 0; i < this.toDos.size(); i++) {
            ToDo toDo = toDos.get(i);
            if (toDo instanceof TimedToDo) {
                timedToDos.add((TimedToDo) (toDo));
            }
        }
        return timedToDos;
    }

    public ArrayList<ToDo> getNormalToDos() {
        ArrayList<ToDo> normalToDos = new ArrayList<>();

        for (int i = 0; i < this.toDos.size(); i++) {
            ToDo toDo = toDos.get(i);
            if (!(toDo instanceof TimedToDo)) {
                normalToDos.add(toDo);
            }
        }
        return normalToDos;
    }

    public void removeExpiredToDos() {
        ArrayList<TimedToDo> timedToDos = this.getTimedToDos();

        for (int i = 0; i < timedToDos.size(); i++) {
            TimedToDo timedToDo = timedToDos.get(i);

            if (timedToDo.getDeadline().isBefore(LocalDateTime.now())) {
                this.remove(timedToDo);
            }
        }
    };

    public void saveToDos() {
        File file = new File("ToDos.txt");
        try {
            if (!(file.exists())) file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);

            for (int i = 0; i < this.toDos.size(); i++) {
                fileWriter.write(this.toDos.get(i).toString());
            }

            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
