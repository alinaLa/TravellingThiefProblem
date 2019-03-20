import java.util.ArrayList;

public class GreedyAlghoritm {

    public static ArrayList<Item> selectItemsToGrab(ArrayList<City> genotyp, ArrayList<Item> bestItems){ //zwraca listę predmiotów do wzięcia w genotypie, null oznacza "nie bierz nic"
        //ArrayList<Item> bestItems2 = KnapsackProblem.getBestItems();
        ArrayList<Item> itemsToGrab = new ArrayList<>();
        for(int i = 0; i < genotyp.size(); i++){
            itemsToGrab.add(null);
        }
        int currentWeight = 0;
        for(int i = genotyp.size()-1; i >= 0; i--) {
            //System.out.println(bestItems.size());
            if (currentWeight + ((bestItems.get(i) == null) ? 0 : bestItems.get(i).getWeight()) <= KnapsackProblem.getWeightMax()) {
                //System.out.println();
                itemsToGrab.add(i, bestItems.get(genotyp.get(i).getId()-1));
            }
        }
        return itemsToGrab;
    }

    public static ArrayList<Item> getBestItems(){
        ArrayList<Item> bestItems = new ArrayList<>();
        //System.out.println("----->" + TravellingSalesmanProblem.getCities().size());
        for(City city : TravellingSalesmanProblem.getCities()){
            bestItems.add(city.getBestItem());
        }
        //System.out.println(bestItems.size());
        return bestItems;
    }

    public static ArrayList<Integer> getActualWeightsInCities(ArrayList<City> genotyp, ArrayList<Item> grabedItems){
        ArrayList<Integer> actualWeightsInCities = new ArrayList<>();
        int actualWeight = 0;
        for(int i = 0; i < genotyp.size(); i++){
            actualWeight += grabedItems.get(i) != null ? grabedItems.get(i).getWeight() : 0;
            actualWeightsInCities.add(actualWeight);
        }
        return actualWeightsInCities;
    }



}
