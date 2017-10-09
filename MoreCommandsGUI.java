package LineEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoreCommandsGUI {
    private JButton clearButton;
    private JButton goToTopButton;
    private JButton renameFileButton;
    private JButton goUpButton;
    private JButton findBackwardButton;
    private JButton deleteLinesButton;
    private JButton QUITButton;
    private JButton helpButton;
    private JButton moveLineButton;
    private JButton findForwardButton;
    private JButton goDownButton;
    private JButton goToEndButton;
    private JTextField ExtraParameterTextField;
    JPanel CommandsPanel;

    public MoreCommandsGUI(JTextArea textArea, JTextField parameter, JTextField location){

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("• • Insert before (b) String line\n" +
                          "• • Insert after (a) String line\n" +
                          "• • Delete (e) line\n" + "• • Replace (x) String line\n" +
                          "• • Select (s) line\n" + "• • Find (f) String\n" +
                          "• • Load (o) filename\n" + "• • Save (w) filename\n" +
                          "• • Display (g)\n" + "• • Go top (i)\n" +
                          "• • Go to end (t)\n" + "• • Go up (u)\n" +
                          "• • Go down (d)\n" + "• • Find backward (v) String line\n" +
                          "• • Find forward (n) String line\n" +
                          "• • Delete lines (k) number line\n" +
                          "• • Move line (y) lines line\n" +
                          "• • Rename file (r) filename\n" + "• • Help (h)\n" +
                          "• • Clear (c )\n" + "• • Quit (q)");
            }
        });
    }
}
