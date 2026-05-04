import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ToDoManager {
    // Liste ToDos
    ArrayList<ToDo> toDos = new ArrayList<>();
    private String path;

    public ToDoManager(String path) {
        this.path = path;
    }

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
        File file = new File(this.path);
        try {
            if (!(file.exists())) file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);

            for (int i = 0; i < this.toDos.size(); i++) {
                ToDo toDo = toDos.get(i);
                String saveString = toDo.getTitle() + "_" + toDo.getBeschreibung() + "_" + toDo.isErledigt();

                if (toDo instanceof TimedToDo) {
                    TimedToDo timedToDo = (TimedToDo) toDo;
                    saveString += timedToDo.getDeadline();
                }

                fileWriter.write(saveString);
            }

            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loadToDos() {
        File file = new File(this.path);
        String fileContent = "";
        if (file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                int temp = fileReader.read();
                while (temp != -1) {
                    fileContent += (char) temp;
                    temp = fileReader.read();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        if (!fileContent.isEmpty()) {
            String[] tempArray = fileContent.split("/n");
            for (int i = 0; i < tempArray.length; i++) {
                System.out.println(tempArray[i]);
            }
        }
    }

}
