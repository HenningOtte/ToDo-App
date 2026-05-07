import javax.swing.*;
import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        ToDoManager toDoManager = new ToDoManager("todo_save.txt");
        ToDoWindow toDoWindow = new ToDoWindow(toDoManager);
    }

    /**
     * To Do Logik
     *
     * To Do Klasse x
     * To Do Klasse mit Begrenzung x
     *
     * To Do Manager
     * Schnittstellen (add/remove/get/toggleCompletion)
     */
}
