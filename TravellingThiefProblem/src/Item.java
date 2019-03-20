import java.util.ArrayList;

public class Item {

    private int id;
    private int profit;
    private int weight;
    private City city;

    Item(int id, int profit, int weight){
        this.id = id;
        this.profit = profit;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public static int getProfitSum(ArrayList<Item> items){
        int sum = 0;
        for(Item item : items){
            sum += (item == null ? 0 : item.profit);
        }
        return sum;
    }

}
