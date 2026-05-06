import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class ToDoWindow extends JFrame {

    public ToDoWindow() {
        setTitle("ToDo-App");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TimedToDo timedToDo = new TimedToDo("TimedToDo", "Hallo Welt!", true, LocalDateTime.now().plusMinutes(3));
        add(this.createToDoBlock(timedToDo), BorderLayout.CENTER);

        setVisible(true);
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
