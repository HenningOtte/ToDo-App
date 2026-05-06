import javax.swing.*;
import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        ToDoWindow toDoWindow = new ToDoWindow();


        ToDoManager toDoManager = new ToDoManager("todo_save.txt");
    }

    static JFrame openFrame() {
        JFrame frame = new JFrame("ToDo-App");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
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
