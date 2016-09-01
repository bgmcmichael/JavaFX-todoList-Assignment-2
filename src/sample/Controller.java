package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable, Serializable {
    Serializer serializer = new Serializer();
    File userFile;
    String user = "";

    @FXML
    ListView todoList;

    @FXML
    TextField todoText;

    transient ObservableList<ToDoItem> todoItems = FXCollections.observableArrayList();
    public ArrayList<ToDoItem> jsonList = new ArrayList<>();
    public JsonListContainer container = new JsonListContainer();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Scanner scan = new Scanner(System.in);
        System.out.println("What is your name?");
        String userInput = scan.nextLine();
        File userFile = new File(userInput + ".dat");
        if (userFile.exists()){
            ArrayList<ToDoItem> tempList = serializer.jsonRestore(userFile).getJsonList();
            for (int counter = 0; counter < tempList.size(); counter++){
                todoItems.add(tempList.get(counter));
                jsonList.add(tempList.get(counter));
            }
        }
        user = userInput;

        todoList.setItems(todoItems);
    }

    //integer choice chooses which list is matching to which
    public void matchLists(){
        for (int counter = 0; counter < todoItems.size(); counter++) {
            jsonList.set(counter, todoItems.get(counter));
        }
        container.setJsonList(jsonList);
        serializer.jsonSave(user, container);
    }

    public void addItem() {
        System.out.println("Adding item ...");
        ToDoItem item = new ToDoItem(todoText.getText());
        todoItems.add(item);
        jsonList.add(item);
        System.out.println(item.toString());
        todoText.setText("");
        container.setJsonList(jsonList);
        serializer.jsonSave(user, container);
    }

    public void removeItem() {
        ToDoItem todoItem = (ToDoItem)todoList.getSelectionModel().getSelectedItem();
        System.out.println("Removing " + todoItem.text + " ...");
        todoItems.remove(todoItem);
        jsonList.remove(todoItem);
        container.setJsonList(jsonList);
        serializer.jsonSave(user, container);
    }

    public void toggleItem() {
        System.out.println("Toggling item ...");
        ToDoItem todoItem = (ToDoItem)todoList.getSelectionModel().getSelectedItem();
        if (todoItem != null) {
            todoItem.isDone = !todoItem.isDone;
            todoList.setItems(null);
            todoList.setItems(todoItems);
            matchLists();
        }
    }

    public void toggleAllItems() {
        System.out.println("Toggling all items ...");
        for (ToDoItem currentItem: todoItems) {
            currentItem.isDone = !currentItem.isDone;
        }
        todoList.setItems(null);
        todoList.setItems(todoItems);
        matchLists();
    }

    public void markAllItemsDone() {
        System.out.println("Toggling all items ...");
        for (ToDoItem currentItem: todoItems) {
            currentItem.isDone = true;
        }
        todoList.setItems(null);
        todoList.setItems(todoItems);
        matchLists();
    }

    public void markAllItemsNotDone() {
        System.out.println("Toggling all items ...");
        for (ToDoItem currentItem: todoItems) {
            currentItem.isDone = false;
        }
        todoList.setItems(null);
        todoList.setItems(todoItems);
        matchLists();
    }

}
