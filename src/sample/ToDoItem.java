package sample;

public class ToDoItem {
    public String text;
    public boolean isDone;

    public ToDoItem(){

    }

    public ToDoItem(String text) {
        this.text = text;
        this.isDone = false;
    }

    public ToDoItem(boolean done, String todoText) {
        text = todoText;
        this.isDone = done;
    }

    @Override
    public String toString() {
        if (isDone) {
            return text + " (done)";
        } else {
            return text;
        }
        // A one-line version of the logic above:
        // return text + (isDone ? " (done)" : "");
    }
}