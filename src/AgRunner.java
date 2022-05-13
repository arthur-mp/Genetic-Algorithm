
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
        int nGer = 50;
        int nElite = 4;

        IndividualFactory individualFactory = new IndividualNQueensFactory(nQueens);

        Ag ag = new Ag();

        Individual individual = ag.run(nPop, individualFactory, nElite, nGer);

    }

}
