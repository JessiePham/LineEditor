package LineEditor;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TextEditor {
    public static void main(String[] args) throws IOException {
        JFrame editorFrame = new JFrame("Line Editor");

        editorFrame.setContentPane(new EditorGUI().EditorPanel);
        editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editorFrame.pack();
        editorFrame.setVisible(true);

        process_command();
    }

    public static void process_command() throws IOException {
//        Commands work with a DLinkedList object
//        Commands not yet work with a Buffer object
        Buffer textBuffer = new Buffer();

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("\nEnter text file name: ");
        Scanner scanner = new Scanner(System.in);


        String fileName = bufferedReader.readLine();
        if (fileName == "") {
            textBuffer.textFileDLinkedList = new DLinkedList();
        } else {
            textBuffer.fileName = fileName;
            textBuffer.textFileDLinkedList = new DLinkedList();
            textBuffer.textFileDLinkedList.load(fileName);
        }

//        BUG: There is a bug here
//        TODO: Think about a while loop here

        String command = new String();
        while (!command.equals("q")) {
            switch (command) {
//                TESTED
                case "h":       //Display instructions
                    textBuffer.textFileDLinkedList.help();
                    break;

//                TESTED
                case "o":       //Load text file
                    fileName = scanner.next();
                    textBuffer.fileName = fileName;
                    textBuffer.textFileDLinkedList = new DLinkedList();
                    textBuffer.textFileDLinkedList.load(fileName);
                    break;
//                TESTED
                case "w":       //Save text file
                    fileName = scanner.next();
                    textBuffer.textFileDLinkedList.save(fileName);
                    break;
//                TESTED
                case "b":       //Insert one line before specified line index
                    System.out.println("Insert index: ");
                    int lineIndex = scanner.nextInt();
                    String stringToInsert = scanner.next();
                    textBuffer.textFileDLinkedList.insertBefore(lineIndex,
                                                                stringToInsert);
                    break;
//                TESTED
                case "a":      //Insert one line after specified line index
                    lineIndex = scanner.nextInt();
                    stringToInsert = scanner.next();
                    textBuffer.textFileDLinkedList.insertAfter(lineIndex, stringToInsert);
                    break;
//                TESTED
                case "e":       //Delete one line at specified index
                    System.out.println("Enter line number to delete:\n");
                    lineIndex = scanner.nextInt();
                    textBuffer.textFileDLinkedList.delete(lineIndex);
                    break;
//                TESTED
                case "x":       //Replace one line with another line at specified index
                    System.out.println("Enter line number to replace:\n");
                    lineIndex = scanner.nextInt();
                    System.out.println("Enter string to insert:\n");
                    stringToInsert = scanner.next();
                    textBuffer.textFileDLinkedList.replace(lineIndex, stringToInsert);
                    break;
//                TESTED
//                BUG: Previous indicator still present after change
                case "s":       //Point the current node to the selected position
                    lineIndex = scanner.nextInt();
                    textBuffer.textFileDLinkedList.select(lineIndex);
                    break;
//                TESTED
                case "f":
                    String pattern = scanner.next();
                    textBuffer.textFileDLinkedList.find(pattern);
                    break;

            /*Search for the first occurrence of one specified string
            * prior to current line*/
//                BUG: String not found
                case "v":
                    String stringToFind = scanner.next();
                    textBuffer.textFileDLinkedList.findBackward(stringToFind);
                    break;

            /*Search for the first occurrence of one specified string
            * in current line and lines below current*/
//                BUG: Null pointer exception
                case "n":
                    stringToFind = scanner.next();
                    textBuffer.textFileDLinkedList.findForward(stringToFind);
                    break;
//                TESTED
                case "k":
                    System.out.println("Delete from line number:\n");
                    int fromLine = scanner.nextInt();
                    System.out.println("Delete to line number:\n");
                    int toLine = scanner.nextInt();
                    textBuffer.textFileDLinkedList.deleteLines(fromLine, toLine);
                    break;
//
                case "y":
                    System.out.println("Move initial line number:\n");
                    fromLine = scanner.nextInt();
                    System.out.println("Move to line number:\n");
                    toLine = scanner.nextInt();
                    textBuffer.textFileDLinkedList.moveLine(fromLine, toLine);
                    break;
//                TESTED
                case "i":       //Go to the first line in buffer
                    textBuffer.textFileDLinkedList.goTop();
                    break;
//                TESTED
                case "t":       //Go to the last line in buffer
                    textBuffer.textFileDLinkedList.goBottom();
                    break;
//                TESTED
                case "u":       //Go up one line from current in buffer
                    textBuffer.textFileDLinkedList.goUp();
                    break;
//                TESTED
                case "d":       //Go down one line from current in buffer
                    textBuffer.textFileDLinkedList.goDown();
                    break;
//                TESTED
                case "g":       //Display the buffer
                    textBuffer.textFileDLinkedList.display();
                    break;

                case "c":       //Clear the Console screen
                    textBuffer.textFileDLinkedList.clear();
                    break;
            }
            System.out.println("\nEnter your command: ");
            command = scanner.next();
        }
        textBuffer.textFileDLinkedList.quit();
    }
}
