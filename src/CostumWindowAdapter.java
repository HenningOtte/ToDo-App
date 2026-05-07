import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CostumWindowAdapter extends WindowAdapter {
    // Handles ToDo data
    ToDoManager toDoManager;

    // Constructor
    CostumWindowAdapter(ToDoManager toDoManager) {
        this.toDoManager = toDoManager;
    }

    @Override
    public void windowClosing(WindowEvent e) {

        // Save ToDos before closing window
        toDoManager.saveToDos();

        super.windowClosed(e);
    }
}
