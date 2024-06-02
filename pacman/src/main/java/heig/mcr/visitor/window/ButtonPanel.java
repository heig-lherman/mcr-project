package heig.mcr.visitor.window;

import javax.swing.*;
import java.util.Map;

public class ButtonPanel extends JPanel {

    public ButtonPanel(
            Map<String, Runnable> actions,
            JFrame parent
    ) {
        super();
        actions.forEach((key, value) -> {
            JButton button = new JButton(key);
            button.addActionListener(l -> {
                value.run();
                parent.requestFocusInWindow();
            });

            add(button);
        });
    }
}
