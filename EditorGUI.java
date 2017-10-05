package LineEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditorGUI {

    public JPanel EditorPanel;
    private JButton findButton;
    private JButton moreComsBtn;
    private JButton loadButton;
    private JButton saveButton;
    private JButton displayButton;
    private JButton insBeforeBtn;
    private JButton insAfterBtn;
    private JButton deleteButton;
    private JButton replaceButton;
    private JButton selectButton;
    private JTextArea editorArea;
    private JPanel topPanel;
    private JTextField locationTextField;
    private JTextField parameterTextField;
    private JLabel paraTF;
    private JLabel locTF;

    public EditorGUI() {
        EditorPanel.setPreferredSize(new Dimension(750, 600));

        insAfterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorArea.append("Hello World!\n");
            }
        });

    moreComsBtn.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent e) {
               MoreCommandsGUI mcg = new MoreCommandsGUI();
            }
        });
    }
}
