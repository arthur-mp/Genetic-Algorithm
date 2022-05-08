
import java.util.List;

import ag.Ag;
import individuo.Individuo;
import individuo.IndividuoNRainhas;
import individuo.IndividuoNRainhasFactory;
import interfaces.IndividuoFactory;

public class AgRunner {
    public static void main(String[] args) throws Exception {
        int nPop = 20;
        int nRainhas = 8;
        int nGer = 1000;
        int nElite = 4;

        IndividuoFactory individuoFactory = new IndividuoNRainhasFactory(nRainhas);

        Ag ag = new Ag();

        Individuo individuo = ag.run(nPop, individuoFactory, nElite, nGer);

        printOut(individuo, nGer);

    }

    private static void printOut(Individuo bestIndividuo, int nGer) {
        IndividuoNRainhas individuo = (IndividuoNRainhas) bestIndividuo;
        List<Integer> genes = individuo.getGenes();

        System.out.println("Geração  |  Avaliação  |  Indivíduo");
        System.out.print("    " + nGer + "    ");
        System.out.print("      " + individuo.getAvaliacao() + "        ");

        for (int i = 0; i < genes.size(); i++) {
            System.out.print(genes.get(i) + " ");
        }
    }
}
