import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class ToDoWindow extends JFrame {

    private ToDoManager toDoManager;

    public ToDoWindow(ToDoManager toDoManager) {
        this.toDoManager = toDoManager;

        setTitle("ToDo-App");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(this.createGUIBtns(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createGUIBtns() {
        JPanel jPanel = new JPanel(new FlowLayout());

        JButton add = new JButton("Add");
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

                    if (intHours <= 0) {
                        ToDo toDo = new ToDo(title, description, false);
                        toDoManager.add(toDo);
                    } else {
                        TimedToDo timedToDo = new TimedToDo(title, description, false, LocalDateTime.now().plusHours(intHours));
                        toDoManager.add(timedToDo);
                    }

                } catch (Exception exception) {
                    ToDo toDo = new ToDo(title, description, false);
                    toDoManager.add(toDo);
                }

                System.out.println("Add:");
                toDoManager.printToDos();
            }
        });

        JButton delAll = new JButton("Del All");
        delAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoManager.getToDos().clear();

                System.out.println("Clear:");
                toDoManager.printToDos();
            }
        });

        JButton dellDone = new JButton("Del Done");
        dellDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = toDoManager.getToDos().size() - 1; i >= 0; i--) {
                    if (toDoManager.getToDos().get(i).isErledigt()) {
                        toDoManager.remove(toDoManager.getToDos().get(i));
                    }
                }

                System.out.println("Remove Erl:");
                toDoManager.printToDos();
            }
        });

        jPanel.add(add);
        jPanel.add(delAll);
        jPanel.add((dellDone));

        return jPanel;
    }

    private String getInput(String prompt) {
        String input = "";

        while (true) {
            input = JOptionPane.showInputDialog(prompt);
            if (input == null) return "";
            if (!input.isEmpty()) return input;
        }
    }

    private JPanel createToDoBlock(ToDo toDo) {
        JPanel jPanel = new JPanel(new BorderLayout());

        jPanel.add(new Label(toDo.getTitle()), BorderLayout.NORTH);
        jPanel.add(new Label(toDo.getBeschreibung()), BorderLayout.CENTER);

        JCheckBox jCheckBox = new JCheckBox();
        jCheckBox.setSelected(toDo.isErledigt());
        jCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDo.setErledigt(jCheckBox.isSelected());
            }
        });

        jPanel.add(jCheckBox, BorderLayout.EAST);

        if (toDo instanceof TimedToDo) {
            jPanel.add(new Label(((TimedToDo) toDo).getDeadline().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))), BorderLayout.SOUTH);
        }

        return jPanel;
    }
}
