import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loader {

    private static int dimensions;
    private static int numberOfItems;

    public static void load(String fileName){
        int line = 0;
        try (Scanner reader = new Scanner(new File(fileName))) {
            while (reader.hasNext()) {
                String str = reader.nextLine();
                if(line == 2){
                    dimensions = Integer.parseInt(str.split("\\s+")[1]);
                }
                if(line == 3){
                    numberOfItems = Integer.parseInt(str.split("\\s+")[3]);
                }
                if(line == 4){
                    KnapsackProblem.setWeightMax(Integer.parseInt(str.split("\\s+")[3]));
                }
                if(line == 5){
                    TravellingSalesmanProblem.setvMin(Double.parseDouble(str.split("\\s+")[2]));
                }
                if(line == 6){
                    TravellingSalesmanProblem.setvMax(Double.parseDouble(str.split("\\s+")[2]));
                }
                if(line >= 10 && line < 10+dimensions){
                    //System.out.println(str.split("\\s+")[0] + " " + str.split("\\s+")[1] + " " + str.split("\\s+")[2]);
                    City city = new City((int)Double.parseDouble(str.split("\\s+")[0]),
                                            (int)Double.parseDouble(str.split("\\s+")[1]),
                                            (int)Double.parseDouble(str.split("\\s+")[2]));
                    TravellingSalesmanProblem.addCity(city);
                }
                if(line >= 11+dimensions && line < 11+dimensions+numberOfItems){
                    //System.out.println(str.split("\\s+")[0] + " " + str.split("\\s+")[1] + " " + str.split("\\s+")[2] + str.split("\\s+")[1] + " " + str.split("\\s+")[3]);
                    Item item = new Item(Integer.parseInt(str.split("\\s+")[0]),
                                            Integer.parseInt(str.split("\\s+")[1]),
                                            Integer.parseInt(str.split("\\s+")[2]));
                    item.setCity(TravellingSalesmanProblem.getCities().get(Integer.parseInt(str.split("\\s+")[3])-1));
                    TravellingSalesmanProblem.getCities().get(Integer.parseInt(str.split("\\s+")[3])-1).addItem(item);
                    KnapsackProblem.addItem(item);
                }
                line++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
