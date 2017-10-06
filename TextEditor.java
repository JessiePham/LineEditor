package LineEditor;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TextEditor {
    public TextEditor(){
        super();
    }

    public static void main(String[] args) throws IOException {
        JFrame editorFrame = new JFrame("Line Editor");

        editorFrame.setContentPane(new EditorGUI().EditorPanel);
        editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editorFrame.pack();
        editorFrame.setVisible(true);

//        TextEditor textEditor = new TextEditor();
        process_command();
    }

    public static void process_command() throws IOException {
        DLinkedList dLinkedList = new DLinkedList();
        Buffer buffer1 = new Buffer(dLinkedList);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nEnter your input: ");
        String command = bufferedReader.readLine();

        Scanner buffer = new Scanner(System.in);

//        TODO: Think about a while loop here
        while (command != "q") {
            switch (command) {
                case "h":       //Display instructions
                    buffer1.dLinkedList.help();
                    break;

                case "o":       //Load text file
                    buffer1.load();
                    break;

                case "w":       //Save text file
                    buffer.save();
                    break;

                case "b":       //Insert one line before specified line index
                    int lineIndex = buffer.nextInt();
                    String stringToInsert = buffer.next();
                    buffer.insertBefore(lineIndex, stringToInsert);
                    break;

                case "a":      //Insert one line after specified line index
                    lineIndex = buffer.nextInt();
                    stringToInsert = buffer.next();
                    buffer.insertAfter(lineIndex, stringToInsert);
                    break;

                case "e":       //Delete one line at specified index
                    lineIndex = buffer.nextInt();
                    buffer.delete(lineIndex);
                    break;

                case "x":       //Replace one line with another line at specified index
                    lineIndex = buffer.nextInt();
                    stringToInsert = buffer.next();
                    buffer.replace(lineIndex, stringToInsert);
                    break;

                case "s":       //

            /*Search for the first occurrence of one specified string
            * prior to current line*/
                case "v":
                    String stringToFind = buffer.next();
                    buffer.findBackward(stringToFind);
                    break;

            /*Search for the first occurrence of one specified string
            * in current line and lines below current*/
                case "n":
                    stringToFind = buffer.next();
                    buffer.findForward(stringToFind);
                    break;

                case "k":
                    int fromIndex = buffer.nextInt();
                    int toIndex = buffer.nextInt();
                    buffer.deleteLines(fromIndex, toIndex);
                    break;

                case "y":
                    buffer.moveLine();
                    break;

                case "i":       //Go to the first line in buffer
                    buffer.goTop();
                    break;

                case "t":       //Go to the last line in buffer
                    buffer.goBottom();
                    break;

                case "u":       //Go up one line from current in buffer
                    buffer.goUp();
                    break;

                case "d":       //Go down one line from current in buffer
                    buffer.goDown();
                    break;

                case "g":       //Display the buffer
                    buffer.display();
                    break;
            }
            System.out.println("\nEnter your input: ");
            command = bufferedReader.readLine();
        }
    }
}
