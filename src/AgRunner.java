
import java.util.List;

import ag.Ag;
import individual.Individual;
import individual.IndividualNQueens;
import individual.IndividualNQueensFactory;
import interfaces.IndividualFactory;

public class AgRunner {
    public static void main(String[] args) throws Exception {
        int nPop = 20;
        int nQueens = 8;
        int nGer = 1000;
        int nElite = 4;

        IndividualFactory individualFactory = new IndividualNQueensFactory(nQueens);

        Ag ag = new Ag();

        Individual individual = ag.run(nPop, individualFactory, nElite, nGer);

        printOut(individual, nGer);

    }

    private static void printOut(Individual bestIndividual, int nGer) {
        IndividualNQueens individual = (IndividualNQueens) bestIndividual;
        List<Integer> genes = individual.getGenes();

        System.out.println("Geração  |  Avaliação  |  Indivíduo");
        System.out.print("    " + nGer + "    ");
        System.out.print("      " + individual.getEvaluation() + "        ");

        for (int i = 0; i < genes.size(); i++) {
            System.out.print(genes.get(i) + " ");
        }
    }
}
