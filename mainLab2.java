package LineEditor;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class mainLab2 {
    CommandLine buffer = new CommandLine();

    public static void main(String[] args) throws IOException {
        JFrame editorFrame = new JFrame("Line Editor");

        editorFrame.setContentPane(new EditorGUI().EditorPanel);
        editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editorFrame.pack();
        editorFrame.setVisible(true);

        process_command();
    }

    public static void process_command() throws IOException {
        CommandLine commandLine = new CommandLine();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your input: ");
        String command = bufferedReader.readLine();

        Scanner scanner = new Scanner(System.in);

//        TODO: Think about a while loop here
        while (command != "q") {
            switch (command)
            {
                case "h":       //Display instructions
                    commandLine.help();
                    process_command();
                    break;

                case "o":       //Load text file
                    commandLine.load();
                    process_command();
                    break;

                case "w":       //Save text file
                    commandLine.save();
                    process_command();
                    break;

                case "b":       //Insert one line before specified line index
                    int lineIndex = scanner.nextInt();
                    String stringToInsert = scanner.next();
                    commandLine.insertBefore(lineIndex, stringToInsert);
                    process_command();
                    break;

                case "a":      //Insert one line after specified line index
                    lineIndex = scanner.nextInt();
                    stringToInsert = scanner.next();
                    commandLine.insertAfter(lineIndex, stringToInsert);
                    process_command();
                    break;

                case "e":       //Delete one line at specified index
                    lineIndex = scanner.nextInt();
                    commandLine.delete(lineIndex);
                    process_command();
                    break;

                case "x":       //Replace one line with another line at specified index
                    lineIndex = scanner.nextInt();
                    stringToInsert = scanner.next();
                    commandLine.replace(lineIndex, stringToInsert);
                    process_command();
                    break;

                case "s":       //

            /*Search for the first occurrence of one specified string
            * prior to current line*/
                case "v":
                    String stringToFind = scanner.next();
                    commandLine.findBackward(stringToFind);
                    process_command();
                    break;

            /*Search for the first occurrence of one specified string
            * in current line and lines below current*/
                case "n":
                    stringToFind = scanner.next();
                    commandLine.findForward(stringToFind);
                    process_command();
                    break;

                case "k":
                    int fromIndex = scanner.nextInt();
                    int toIndex = scanner.nextInt();
                    commandLine.deleteLines(fromIndex, toIndex);
                    process_command();
                    break;

                case "y":
                case "i":       //Go to the first line in buffer
                    commandLine.goTop();
                    process_command();
                    break;

                case "t":       //Go to the last line in buffer
                    commandLine.goBottom();
                    process_command();
                    break;

                case "u":       //Go up one line from current in buffer
                    commandLine.goUp();
                    process_command();
                    break;

                case "d":       //Go down one line from current in buffer
                    commandLine.goDown();
                    process_command();
                    break;

                case "g":       //Display the buffer
                    commandLine.display();
                    process_command();
                    break;
            }

            commandLine.quit();
        }
    }
}
