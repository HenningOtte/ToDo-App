import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ToDoManager {
    // Stores all ToDos
    public ArrayList<ToDo> toDos = new ArrayList<>();

    // Save file path
    private String path;

    // Constructor
    public ToDoManager(String path) {
        this.path = path;
    }

    // Returns all ToDos
    public ArrayList<ToDo> getToDos() {
        return toDos;
    }

    // Adds ToDo if it does not already exist
    public void add(ToDo toDo) {
        boolean foundToDo = false;
        for (int i = 0; i < this.toDos.size(); i++) {
            if (this.toDos.get(i).equals(toDo)) {
                foundToDo = true;
                break;
            }
        }
        if (!foundToDo) this.toDos.add(toDo);
    }

    // Removes a ToDo
    public void remove(ToDo toDo) {
        toDos.remove(toDo);
    }

    // Returns all timed ToDos
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

    // Returns all normal ToDos
    public ArrayList<ToDo> getNormalToDos() {
        ArrayList<ToDo> normalToDos = new ArrayList<>();

        for (int i = 0; i < this.toDos.size(); i++) {
            ToDo toDo = toDos.get(i);
            if (!(toDo instanceof TimedToDo)) {
                normalToDos.add(toDo);
                System.out.println(toDo.toString());
            }
        }
        return normalToDos;
    }

    // Removes expired timed ToDos
    public void removeExpiredToDos() {
        ArrayList<TimedToDo> timedToDos = this.getTimedToDos();

        for (int i = 0; i < timedToDos.size(); i++) {
            TimedToDo timedToDo = timedToDos.get(i);

            if (timedToDo.getDeadline().isBefore(LocalDateTime.now())) {
                this.remove(timedToDo);
            }
        }
    };

    // Saves all ToDos to file
    public void saveToDos() {
        File file = new File(this.path);
        try {
            if (!(file.exists())) file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);

            for (int i = 0; i < this.toDos.size(); i++) {
                ToDo toDo = toDos.get(i);
                String saveString = toDo.getTitle() + "_" + toDo.getDescription() + "_" + toDo.isCompleted();

                if (toDo instanceof TimedToDo) {
                    TimedToDo timedToDo = (TimedToDo) toDo;
                    saveString += "_" + timedToDo.getDeadline().toString();
                }

                fileWriter.write(saveString + "\n");
            }

            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Loads ToDos from file
    public void loadToDos() {
        File file = new File(this.path);
        String fullString = "";

        // Stop if file does not exist
        if (!file.exists()) return;

        try {
            FileReader fileReader = new FileReader(file);
            int temp = fileReader.read();

            // Read complete file
            while (temp != -1) {
                fullString += (char) temp;
                temp = fileReader.read();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // Print load error
        if (!fullString.isEmpty()) {
            String[] tempArray = fullString.split("\n");
            for (int i = 0; i < tempArray.length; i++) {
                String[] toDoContent = tempArray[i].split("_");

                // Create timed or normal ToDo
                if (toDoContent.length > 3) {
                    TimedToDo timedToDo = createTimedToDoFromArray(toDoContent);
                    this.add(timedToDo);
                } else {
                    ToDo toDo = createToDoFromArray(toDoContent);
                    this.add(toDo);
                }
            }
        }
    }

    // Creates normal ToDo from string array
    private ToDo createToDoFromArray(String[] contentArray) {
        return new ToDo(
                contentArray[0],
                contentArray[1],
                Boolean.parseBoolean(contentArray[2])
        );
    }

    // Creates timed ToDo from string array
    private TimedToDo createTimedToDoFromArray(String[] contentArray) {
        return new TimedToDo(
                contentArray[0],
                contentArray[1],
                Boolean.parseBoolean(contentArray[2]),
                LocalDateTime.parse(contentArray[3])
        );
    }

}
