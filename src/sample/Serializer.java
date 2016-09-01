package sample;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Serializer {

    public void jsonSave(String userName, JsonListContainer toDoItems) {
    final String DATA_FILE_NAME = userName + ".dat";
        JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
        String jsonString = jsonSerializer.serialize(toDoItems);

        try {
            FileWriter fout = new FileWriter(DATA_FILE_NAME);
            fout.write(jsonString);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JsonListContainer jsonRestore(File jsonFile) {
        String jsonTD = "";
        try{
            Scanner jsonScanner = new Scanner(jsonFile);
            while (jsonScanner.hasNext()){
                jsonTD = jsonScanner.nextLine();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        JsonParser ToDoItemParser = new JsonParser();
        JsonListContainer item = ToDoItemParser.parse(jsonTD, JsonListContainer.class);

        return item;
    }
}

//    public static void main(String[] args) {
//        System.out.println("Running serialization tester ...");
//
//        new Serializer().test();
//    }

//    public void test() {
//        try {
//            System.out.println("test()");
//
//            ToDoItem testTD = new ToDoItem("Test Serialization", false);
//            System.out.println("test TD = " + testTD);
//            saveTD(testTD);
//
//            ToDoItem retrievedTD = restoreTD();
//            System.out.println("retrieved TD = " + retrievedTD);
//
//            String jsonTD = jsonSave(testTD);
//            System.out.println("JSON ToDo = " + jsonTD);
//
//            ToDoItem restoredFromJSON = jsonRestore(jsonTD);
//            System.out.println("Restored from JSON = " + restoredFromJSON);
//
//        } catch (IOException ioEx) {
//            ioEx.printStackTrace();
//        } catch (ClassNotFoundException cnfe) {
//            cnfe.printStackTrace();
//        }
//    }

