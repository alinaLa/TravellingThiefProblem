import java.util.ArrayList;

public class KnapsackProblem {

    private static int weightMax;
    private static ArrayList<Item> items = new ArrayList<>();

    public static int getWeightMax() {
        return weightMax;
    }

    public static void setWeightMax(int weightMax) {
        KnapsackProblem.weightMax = weightMax;
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static void setItems(ArrayList<Item> items) {
        KnapsackProblem.items = items;
    }

    public static void addItem(Item item){
        items.add(item);
    }


}
