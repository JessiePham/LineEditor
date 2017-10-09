package LineEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
    private JTextArea editorTextArea;
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
                editorTextArea.append("Hello World!\n");
            }
        });

        moreComsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame moreCommandsFrame = new JFrame("Extra Commands");

                moreCommandsFrame.setContentPane(
                        new MoreCommandsGUI(editorTextArea, locationTextField,
                                            parameterTextField).CommandsPanel);
                moreCommandsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                moreCommandsFrame.pack();
                moreCommandsFrame.setVisible(true);
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = parameterTextField.getText();
                try {
                    FileReader fileReader = new FileReader(fileName);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        editorTextArea.append(line+"\n");
                    }
                }
                catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = parameterTextField.getText();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    BufferedReader bufferedReader = new BufferedReader(
                            new FileReader(new File(fileName)));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        writer.write(line+"\n");
                    }
                    writer.close();
                    System.out.println("File successfully saved to " + fileName);
                }
                catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }
}
