import java.util.ArrayList;

public class TravellingSalesmanProblem {

    private static double vMax;
    private static double vMin;
    private static ArrayList<City> cities = new ArrayList<>();

    public static double getvMax() {
        return vMax;
    }

    public static void setvMax(double vMax) {
        TravellingSalesmanProblem.vMax = vMax;
    }

    public static double getvMin() {
        return vMin;
    }

    public static void setvMin(double vMin) {
        TravellingSalesmanProblem.vMin = vMin;
    }

    public static ArrayList<City> getCities() {
        //System.out.println(cities.size());
        return cities;
    }

    public static void setCities(ArrayList<City> cities) {
        TravellingSalesmanProblem.cities = cities;
    }

    static void addCity(City city){
        //System.out.println(cities.size());
        cities.add(city);
    }



}
