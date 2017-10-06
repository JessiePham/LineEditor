package LineEditor;

public class Buffer {
    public DLinkedList textFileContent;
    public boolean dirty;
    public String fileName;

    public Buffer(DLinkedList dLinkedList) {
        textFileContent = new DLinkedList();
    }
}
