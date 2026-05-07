import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class ToDoWindow extends JFrame {

    // Handles all ToDo data
    private ToDoManager toDoManager;

    // Main panel containing all ToDos
    private JPanel toDoArea;

    public ToDoWindow(ToDoManager toDoManager) {
        this.toDoManager = toDoManager;

        // Window settings
        setTitle("ToDo-App");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add ToDo list area
        this.toDoArea = createToDoArea();
        add(this.toDoArea, BorderLayout.CENTER);

        // Add control buttons
        add(createGUIBtns(), BorderLayout.SOUTH);

        // Resize window to fit content
        pack();

        // Save ToDos when window closes
        addWindowListener(new CostumWindowAdapter(toDoManager));
        setVisible(true);
    }

    public JPanel createToDoArea() {
        JPanel jPanel = new JPanel();

        // Vertical layout for ToDos
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        // Add all ToDo blocks
        for (ToDo toDo : toDoManager.getToDos()) {
            jPanel.add(createToDoBlock(toDo));
        }

        return jPanel;
    }

    private JPanel createGUIBtns() {
        JPanel jPanel = new JPanel(new FlowLayout());

        JButton add = new JButton("Add");

        // Add new ToDo
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = getInput("What title should the ToDo have?");
                if (title.isEmpty()) return;

                String description = getInput("What description should the ToDo have?");
                if (description.isEmpty()) return;

                String hours = getInput("In how many hours should the ToDo expire?");
                if (hours.isEmpty()) return;

                try {
                    int intHours = Integer.parseInt(hours);

                    // Create normal or timed ToDo
                    if (intHours <= 0) {
                        ToDo toDo = new ToDo(title, description, false);
                        toDoManager.add(toDo);
                    } else {
                        TimedToDo timedToDo = new TimedToDo(title, description, false, LocalDateTime.now().plusHours(intHours));
                        toDoManager.add(timedToDo);
                    }

                } catch (Exception exception) {
                    // Fallback if input is invalid
                    ToDo toDo = new ToDo(title, description, false);
                    toDoManager.add(toDo);
                }

                // Refresh UI
                update();
            }
        });

        JButton delAll = new JButton("Del All");
        // Delete all ToDos
        delAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoManager.getToDos().clear();
                update();
            }
        });

        JButton dellDone = new JButton("Del Done");
        // Delete all completed ToDos
        dellDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = toDoManager.getToDos().size() - 1; i >= 0; i--) {
                    if (toDoManager.getToDos().get(i).isCompleted()) {
                        toDoManager.remove(toDoManager.getToDos().get(i));
                    }
                }

                update();
            }
        });

        // Add buttons to panel
        jPanel.add(add);
        jPanel.add(delAll);
        jPanel.add((dellDone));

        return jPanel;
    }

    private void update() {
        // Remove old ToDo panel
        remove(toDoArea);

        // Save current state
        toDoManager.saveToDos();

        // Create updated ToDo panel
        toDoArea = createToDoArea();

        // Add updated panel
        add(toDoArea, BorderLayout.CENTER);

        // Resize window
        pack();
    }

    private String getInput(String prompt) {
        String input = "";

        while (true) {
            // Show input dialog
            input = JOptionPane.showInputDialog(prompt);

            // Return empty string if canceled
            if (input == null) return "";

            // Return valid input
            if (!input.isEmpty()) return input;
        }
    }

    private JPanel createToDoBlock(ToDo toDo) {
        JPanel jPanel = new JPanel(new BorderLayout());

        // Add title and description
        jPanel.add(new Label(toDo.getTitle()), BorderLayout.NORTH);
        jPanel.add(new Label(toDo.getDescription()), BorderLayout.CENTER);

        JCheckBox jCheckBox = new JCheckBox();

        // Set current completion state
        jCheckBox.setSelected(toDo.isCompleted());

        // Update completion state
        jCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDo.setCompleted(jCheckBox.isSelected());
            }
        });

        jPanel.add(jCheckBox, BorderLayout.EAST);

        // Check if ToDo is a TimedToDo
        if (toDo instanceof TimedToDo) {
            jPanel.add(new Label(((TimedToDo) toDo).getDeadline().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))), BorderLayout.SOUTH);
        }

        return jPanel;
    }
}
