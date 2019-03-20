import java.util.ArrayList;

public class Main {

    static ArrayList<City> bestGenotyp;
    static ArrayList<Item> grabedItems;
    static GeneticAlghoritm geneticAlghoritm;
    static GreedyAlghoritm greedyAlghoritm;

    public static void main(String[] args){
        GeneticAlghoritm.setPop_size(100); //rozmiar populacji
        GeneticAlghoritm.setGen(100); //liczba pokoleń
        GeneticAlghoritm.setPx(0.7);
        GeneticAlghoritm.setPm(0.01);
        GeneticAlghoritm.setTour(5); //rozmiar turnieju
        Loader.load("./testData/medium_4.ttp");
        runAlghoritms();
        System.out.println(GeneticAlghoritm.getBestGenotypeEver());
        System.out.println(GeneticAlghoritm.getBestValueEver());
    }

    public static void runAlghoritms(){
        GeneticAlghoritm.initialise();
        GeneticAlghoritm.evaluate();
        while(GeneticAlghoritm.getGen() > 0){
            System.out.println("gen " + GeneticAlghoritm.getGen());
            GeneticAlghoritm.selection();
            GeneticAlghoritm.crossover();
            GeneticAlghoritm.mutate();
            GeneticAlghoritm.evaluate();
            GeneticAlghoritm.setGen(GeneticAlghoritm.getGen() - 1);
        }
    }

}
