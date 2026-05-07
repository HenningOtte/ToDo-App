import javax.swing.*;
import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        // Create ToDo manager with save file
        ToDoManager toDoManager = new ToDoManager("todo_save.txt");

        // Load saved ToDos
        toDoManager.loadToDos();

        // Remove expired timed ToDos
        toDoManager.removeExpiredToDos();

        // Create and show GUI window
        ToDoWindow toDoWindow = new ToDoWindow(toDoManager);
    }
}
