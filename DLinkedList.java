package LineEditor;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class DLinkedList extends Component {
    private ElementDPtr tail;
    private String value;
    private ElementDPtr current;
    private ElementDPtr head;
    private int length = 0;

    public DLinkedList() {
        head = new ElementDPtr("", null, null);
        tail = new ElementDPtr("", head, null);
        head.setNext(tail);
        length = 0;
    }

    public DLinkedList(String v) {
        value = v;
        head = null;
        head.setNext(tail);
        length = 0;
    }

    public void help() {
        System.out.println("• • Insert before (b) String line\n" +
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

    /*Method load creates a buffer object that stores the content
    * of a text file for later edits */
    public void load(String fileName) {
//        Scanner input = new Scanner(System.in);
//        String fileName = input.next();     //Prompts for name of text file
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                insertAtEnd(line);
            }
            ElementDPtr temp = head;
            for (int i = 0; i < length - 1; i++) {
                temp = temp.getNext();
            }
            select(1);
//          TODO: Append text to text area
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            ElementDPtr temp = head;
            for (int i = 1; i < length; i++) {
                writer.write(temp.getValue() + "\n");
                temp = temp.getNext();
            }
            writer.close();
            System.out.println("File successfully saved to " + fileName);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertAtBegin(String v) {
        ElementDPtr e = new ElementDPtr(v, null, head);
        head = e;
        length++;
    }

    public void insertAtEnd(String v) {
        if (length == 0) {
            insertAtBegin(v);
        } else {
            ElementDPtr e = new ElementDPtr(v, tail.getPrev(), tail);
//            System.out.println(v + " tail " + tail.getPrev().getValue());
            if (tail.getPrev().getValue().equals("")) {
                e.setPrev(head);
            }

            e.getPrev().setNext(e);
//            System.out.println(v + " it " + length);
            tail.setPrev(e);
            length += 1;
        }
//        System.out.println(tail.getPrev().getValue() + " insertE " + length);
    }

    void insertBefore(
            int location,
            String textToInsert) {

        if (length == 0) {
            head = new ElementDPtr(textToInsert);
            tail = head;
        } else {
            ElementDPtr temp = head;
            for (int i = 1; i < location; i++) {
                temp = temp.getNext();
            }
            ElementDPtr e = new ElementDPtr(textToInsert);
//            newNode.setNext(temp.getNext());
//            newNode.setPrev(temp);
//            newNode.getNext().setPrev(newNode);
//            temp.setNext(newNode);

            temp.getPrev().setNext(e);
            e.setPrev(temp.getPrev());
            e.setNext(temp);
            temp.setPrev(e);
        }
        length += 1;
        display();
    }

    void insertAfter(
            int location,
            String parameter) {
        if (length == 0) {
            head = new ElementDPtr(parameter);
            tail = head;
        } else {
            ElementDPtr temp = head;
            for (int i = 1; i < location; i++) {
                temp = temp.getNext();
            }
            ElementDPtr e = new ElementDPtr(parameter);
            e.setNext(temp.getNext());
            temp.getNext().setPrev(e);
            e.setPrev(temp);
            temp.setNext(e);
        }
        length += 1;
        display();
    }

    void delete(int location) {
        if (location == 0) {
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
        }
        if (length == 0) {
            System.out.println("There's nothing to delete");
        } else {
            ElementDPtr temp = head;
            for (int i = 1; i < location; i++) {
                temp = temp.getNext();
            }
            temp.getPrev().setNext(temp.getNext());
            temp.getNext().setPrev(temp.getPrev());

        }
        length -= 1;
        display();
    }

    void replace(
            int location,
            String parameter) {
        if (location == 0) {
            insertBefore(0, parameter);
            delete(1);
        } else {
            ElementDPtr temp = head;
            for (int i = 1; i < location; i++) {
                temp = temp.getNext();
            }
            ElementDPtr e = new ElementDPtr(parameter);
            e.setNext(temp.getNext());
            e.setPrev(temp.getPrev());
            temp.getNext().setPrev(e);
            temp.getPrev().setNext(e);
            // delete();
        }
        display();
    }

    void select(int lineIndex) {
        ElementDPtr temp = head;
        for (int tempIndex = 1; tempIndex < lineIndex; tempIndex++) {
            temp = temp.getNext();
        }
        current = temp;
        current.setValue(current.getValue() + "<");
        display();
    }

    void find(String pattern) {
        ElementDPtr temp = head;

        int indexOfPattern = getPosition(pattern);
        if (indexOfPattern == -1) {
            System.out.println("String " + pattern + " NOT FOUND.");
        } else {
            select(indexOfPattern);
        }
    }

    void findBackward(String pattern) {
        ElementDPtr temp = head;
        while ((temp.getValue() != pattern) || temp != current) {
            temp = temp.getNext();
        }
        if (temp.getValue() == pattern) {
            current = temp;
        } else {
            System.out.println("STRING " + pattern + " NOT FOUND.");
        }

    }

    void findForward(String pattern) {
        ElementDPtr temp = current;
        while (temp.getValue() != pattern || temp != tail) {
            temp = temp.getNext();
        }
        if (temp.getValue() == pattern) {
            select(getPosition(pattern));
        } else {
            System.out.println("STRING " + pattern + " NOT FOUND.");
        }

    }

    //    Questionable method
    void goTop() {
        select(1);
    }

    //    Questionable method
    void goBottom() {
        select(length);
    }

    void goUp() {
        select(getPosition(current.getPrev().getValue()));
    }

    void goDown() {
        select(getPosition(current.getNext().getValue()));
    }

    public void display() {
        if (head == null) {
            System.out.println("nothing");
        } else {
            ElementDPtr temp = head;
            while (temp != null) {
                System.out.println(temp.getValue());
                temp = temp.getNext();
            }
        }
    }

    void deleteLines(
            int start,
            int stop) {
        if ((start < 1) || (stop > length) || (start > length) || (stop < 1)) {
            System.out.println("INDICES OUT OF RANGE.");
        } else {
            for (int i = start; i <= stop; i++) {
                delete(start);
            }
        }
    }

    void quit() {
        System.exit(0);
    }

    //    TODO: Write
    void moveLine(
            int fromLine,
            int toLine) {
        ElementDPtr copyOfSourceLine = new ElementDPtr();
    }

    //    TODO: Write
    void renameFile() {

    }

    //    BUG: Does not clear the console
    void clear() {
        System.out.flush();
    }

    public int getPosition(String v) {
        // go looking for the data
        ElementDPtr temp = head;
        int pos = 1;
        while (temp != null) {
            if (temp.getValue().equals(v)) {
                // return the position if found
                return pos;
            }
            pos += 1;
            temp = temp.getNext();
        }
        return -1;
    }
}
