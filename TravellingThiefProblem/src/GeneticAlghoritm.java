import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class GeneticAlghoritm {

    private static int pop_size; //rozmiar populacji
    private static int gen; //liczba pokoleń
    private static double Px;
    private static double Pm;
    private static int Tour; //rozmiar turnieju

    private static ArrayList<ArrayList<City>> population = new ArrayList<>();

    private static ArrayList<City> bestGenotypeEver = new ArrayList<>();
    private static double bestValueEver = Double.MIN_VALUE;

    static FileWriter fw;

    static {
        try {
            fw = new FileWriter("log.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static PrintWriter pw = new PrintWriter(fw, true);

    public GeneticAlghoritm() throws IOException {
    }

    public static ArrayList<ArrayList<City>> getPopulation() {
        return population;
    }

    public static void setPopulation(ArrayList<ArrayList<City>> population) {
        GeneticAlghoritm.population = population;
    }

    public static ArrayList<City> getBestGenotypeEver() {
        return bestGenotypeEver;
    }

    public static void setBestGenotypeEver(ArrayList<City> bestGenotypeEver) {
        GeneticAlghoritm.bestGenotypeEver = bestGenotypeEver;
    }

    public static double getBestValueEver() {
        return bestValueEver;
    }

    public static void setBestValueEver(double bestValueEver) {
        GeneticAlghoritm.bestValueEver = bestValueEver;
    }

    public static int getPop_size() {
        return pop_size;
    }

    public static void setPop_size(int pop_size) {
        GeneticAlghoritm.pop_size = pop_size;
    }

    public static int getGen() {
        return gen;
    }

    public static void setGen(int gen) {
        GeneticAlghoritm.gen = gen;
    }

    public static double getPx() {
        return Px;
    }

    public static void setPx(double px) {
        Px = px;
    }

    public static double getPm() {
        return Pm;
    }

    public static void setPm(double pm) {
        Pm = pm;
    }

    public static int getTour() {
        return Tour;
    }

    public static void setTour(int tour) {
        Tour = tour;
    }

    public static ArrayList<City> generateGenotype(ArrayList<City> cities){
        //Genotype genotype = new Genotype();
        /*
        ArrayList<City> citiesClone = (ArrayList<City>) cities.clone();
        int i = 0;
        while(!citiesClone.isEmpty()){
            if(citiesClone.size() == 1){
                i = 0;
            }else {
                i = RandomGenerator.getRandomNumberInRange(0, citiesClone.size() - 1);
            }
            genotype.add(citiesClone.get(i));
            citiesClone.remove(i);
        }
        return genotype;
        */
        /*
        ArrayList<City> citiesClone = new ArrayList<>(cities);
        Collections.copy(citiesClone, cities);
        for(int i = 0; i < cities.size(); i++){
            City c = new City(citiesClone.get(i).getId(), citiesClone.get(i).getxCoordinate(), citiesClone.get(i).getyCoordinate());
            citiesClone.set(i, c);
            for(int ii = 0; i < citiesClone.get(i).getItems().size(); ii++){
                System.out.println(citiesClone.get(i).getItems().size());
                citiesClone.get(i).getItems().get(ii).setId(citiesClone.get(i).getItems().get(ii).getId());
                citiesClone.get(i).getItems().get(ii).setProfit(citiesClone.get(i).getItems().get(ii).getProfit());
                citiesClone.get(i).getItems().get(ii).setWeight(citiesClone.get(i).getItems().get(ii).getWeight());
                citiesClone.get(i).getItems().get(ii).setCity(c);
            }
        }
        Collections.shuffle(citiesClone);
        return citiesClone;
        */
        ArrayList<City> citiesClone = new ArrayList<>(cities);
        Collections.copy(citiesClone, cities);
        for(int i = 0; i < cities.size(); i++){
            City c = new City(citiesClone.get(i).getId(), citiesClone.get(i).getxCoordinate(), citiesClone.get(i).getyCoordinate());
            citiesClone.set(i, c);
            for(int ii = 0; ii < cities.get(i).getItems().size(); ii++){
                //System.out.println(citiesClone.get(i).getItems().size());
                //citiesClone.get(i).getItems().get(ii).setId(citiesClone.get(i).getItems().get(ii).getId());
                //citiesClone.get(i).getItems().get(ii).setProfit(citiesClone.get(i).getItems().get(ii).getProfit());
                //citiesClone.get(i).getItems().get(ii).setWeight(citiesClone.get(i).getItems().get(ii).getWeight());
                citiesClone.get(i).getItems().add(new Item(cities.get(i).getItems().get(ii).getId(),
                                                            cities.get(i).getItems().get(ii).getProfit(),
                                                            cities.get(i).getItems().get(ii).getWeight()));
                citiesClone.get(i).getItems().get(ii).setCity(c);
            }
        }
        Collections.shuffle(citiesClone);
        //System.out.println(citiesClone);
        return citiesClone;

    }


    public static void initialise(){
        for(int i = 0; i < pop_size; i++){
            population.add(generateGenotype(TravellingSalesmanProblem.getCities()));
        }
        //System.out.println(population);
    }

    public static double calculateValue(ArrayList<City> genotype){
        ArrayList<Item> grabedItems = GreedyAlghoritm.selectItemsToGrab(genotype, GreedyAlghoritm.getBestItems());
        int profitSum = Item.getProfitSum(grabedItems);
        ArrayList<Integer> actualWeightsInCities = GreedyAlghoritm.getActualWeightsInCities(genotype, grabedItems);
        double timeSum = 0.0;
        for(int ii = 1; ii < genotype.size()-1; ii++){
            timeSum += City.getDistance(genotype.get(ii), genotype.get(ii+1))
                    / (TravellingSalesmanProblem.getvMax() - actualWeightsInCities.get(ii)
                    *(TravellingSalesmanProblem.getvMax() - TravellingSalesmanProblem.getvMin())/KnapsackProblem.getWeightMax());
            //System.out.println("----------- licznik " + City.getDistance(genotype.get(ii), genotype.get(ii+1)));
            //System.out.println("--------------------- mianownik " + Double.toString(TravellingSalesmanProblem.getvMax() - actualWeightsInCities.get(ii)
            //        *(TravellingSalesmanProblem.getvMax() - TravellingSalesmanProblem.getvMin())/KnapsackProblem.getWeightMax()));
            //System.out.println("time" + timeSum);
        }
        timeSum += City.getDistance(genotype.get(genotype.size()-1), genotype.get(0))
                / (TravellingSalesmanProblem.getvMax() - actualWeightsInCities.get(actualWeightsInCities.size()-1)
                *(TravellingSalesmanProblem.getvMax() - TravellingSalesmanProblem.getvMin())/KnapsackProblem.getWeightMax());
        //System.out.println("profitSum " + profitSum);
        //System.out.println("timeSum" + timeSum);
        //System.out.println(gen + " " + population);
        return profitSum - timeSum;
    }

    public static void evaluate(){
        ArrayList<City> bestGenotypeInPopulation = population.get(0);
        double bestValue = Double.MIN_VALUE;
        for(int i = 0; i < pop_size; i++){
            double currentValue = calculateValue(population.get(i));
            //System.out.println(currentValue);
            if(currentValue > bestValue){
                bestGenotypeInPopulation = population.get(i);
                bestValue = currentValue;
                if(bestValue > bestValueEver){
                    bestGenotypeEver = bestGenotypeInPopulation;
                    bestValueEver = bestValue;
                    //System.out.println("best value " + currentValue);
                }
            }
        }
        System.out.println(bestValueEver);
        pw.println(Double.toString(bestValueEver).replace(".", ","));
    }

    public static void selection(){
        //metoda turniejowa
        /*
            1. Ustalamy liczność turnieju, np. k;
            2. Metodą ruletki losujemy k-osobników;
            3. Następnie spośród wylosowanych reprodukujemy najlepszego;
         */
        ArrayList<ArrayList<City>> newPopulation = new ArrayList<>();
        for(int i = 0; i < pop_size; i++){
            ArrayList<ArrayList<City>> toTour = new ArrayList<>();
            ArrayList<Double> values = new ArrayList<>();
            for(int ii = 0; ii < Tour; ii++){
                toTour.add(population.get(RandomGenerator.getRandomNumberInRange(0, pop_size-1)));
                //System.out.println(RandomGenerator.getRandomNumberInRange(0, pop_size-1));
                values.add(calculateValue(toTour.get(toTour.size()-1)));
            }
            double maxValue = Collections.max(values);
            newPopulation.add(toTour.get(values.indexOf(maxValue)));
            //System.out.println(toTour.get(values.indexOf(maxValue)));
        }
        population = newPopulation;
    }

    public static void crossover(){
        for(int i = 0; i < pop_size; i++){
            if(RandomGenerator.getRandomNumberInRange(0, 100)/100 <= Px){
                population.set(i, crossover(population.get(i), population.get(RandomGenerator.getRandomNumberInRange(0, pop_size-1))));
            }
        }
    }

    public static void mutate(){
        for(int i = 0; i < pop_size; i++){
            if(RandomGenerator.getRandomNumberInRange(0, 100)/100 <= Pm){
                population.set(i, mutate(population.get(i)));
            }
        }
    }

    public static ArrayList<City> crossover(ArrayList<City> parent1, ArrayList<City> parent2) {
        // Create new child tour
        ArrayList<City> child = new ArrayList<>();
        for(int i = 0; i < parent1.size(); i++){
            child.add(null);
        }

        // Get start and end sub tour positions for parent1's tour
        int startPos = (int) (Math.random() * parent1.size());
        int endPos = (int) (Math.random() * parent1.size());

        // Loop and add the sub tour from parent1 to our child
        for (int i = 0; i < child.size(); i++) {
            // If our start position is less than the end position
            if (startPos < endPos && i > startPos && i < endPos) {
                child.set(i, parent1.get(i));
            } // If our start position is larger
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.set(i, parent1.get(i));
                }
            }
        }

        // Loop through parent2's city tour
        for (int i = 0; i < parent2.size(); i++) {
            // If child doesn't have the city add it
            if (!child.contains(parent2.get(i))) {
                // Loop to find a spare position in the child's tour
                for (int ii = 0; ii < child.size(); ii++) {
                    // Spare position found, add city
                    if (child.get(ii) == null) {
                        child.set(ii, parent2.get(i));
                        break;
                    }
                }
            }
        }
        return child;

    }


    public static ArrayList<City> mutate(ArrayList<City> genotype){
        int index1 = RandomGenerator.getRandomNumberInRange(0, genotype.size()-1);
        int index2 = RandomGenerator.getRandomNumberInRange(0, genotype.size()-1);
        City city1 = genotype.get(index1);
        City city2 = genotype.get(index2);
        genotype.set(index1, city2);
        genotype.set(index2, city1);
        return genotype;
    }




}
