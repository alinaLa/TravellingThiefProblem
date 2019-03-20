import java.util.ArrayList;

public class City {

    private int id;
    private int xCoordinate;
    private int yCoordinate;
    private ArrayList<Item> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    City(int id, int xCoordinate, int yCoordinate){
        this.id = id;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        items = new ArrayList<>();
    }

    public String toString(){
        return Integer.toString(this.id);
    }

    public void addItem(Item item){
        items.add(item);
    }

    public Item getBestItem(){
        if(items.size() > 0) {
            int i = 0;
            double bestRatio = 0;
            int bestItemIndex = 0;
            for (Item item : items) {
                double ratio = item.getProfit() / item.getWeight();
                if (ratio > bestRatio) {
                    bestRatio = ratio;
                    bestItemIndex = i;
                }
                i++;
            }
            //System.out.println("items size " + items.size());
            return items.get(bestItemIndex);
        }else{
            return null;
        }
    }


    static public double getDistance(City city1, City city2){
        double x = city1.getxCoordinate() - city2.getxCoordinate();
        double y = city1.getyCoordinate() - city2.getyCoordinate();
        return Math.ceil(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
        //return Math.sqrt(Math.pow(((double)city1.xCoordinate - (double)city2.xCoordinate), 2.0) - Math.pow(((double)city1.yCoordinate - (double)city2.yCoordinate), 2.0));
    }

}
