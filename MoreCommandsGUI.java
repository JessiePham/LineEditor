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

    public MoreCommandsGUI(){
//        Opens More Commands panel
        JFrame moreCommandsFrame = new JFrame("Extra Commands");
        moreCommandsFrame.setContentPane(new MoreCommandsGUI().CommandsPanel);
        moreCommandsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        moreCommandsFrame.pack();
    }
}
