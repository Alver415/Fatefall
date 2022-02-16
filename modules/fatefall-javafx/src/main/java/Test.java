import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    static List<String> list;
    static ObservableList<String> observable;

    public static void main(String... args) {
        list = new ArrayList<>();
        observable = FXCollections.observableList(list);
        observable.addListener((ListChangeListener<? super String>) c -> {
            System.out.println("CHANGE!: " + c);
        });

        print();

        list.add("1");
        print();

        observable.add("2");
        print();

    }
    private static void print() {
        System.out.println("      list=" + list);
        System.out.println("observable=" + observable);
    }
}
