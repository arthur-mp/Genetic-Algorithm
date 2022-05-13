package individual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class IndividualNQueens extends Individual {

    protected List<Integer> genes;

    protected IndividualNQueens(int nQueens) {
        this.genes = new ArrayList<>();
        List<Integer> genesRandom = new ArrayList<>();
        for (int i = 0; i < nQueens; i++) {
            genesRandom.add(i + 1);
        }
        for (int i = 0; i < nQueens; i++) {
            Random random = new Random();
            int index = random.nextInt(genesRandom.size());
            int value = genesRandom.get(index);
            this.genes.add(value);
            genesRandom.remove(index);
        }
    }

    protected IndividualNQueens(List<Integer> genes) {
        this.genes = genes;
    }

    @Override
    public Double evaluate() {
        Double fitness = 0.0;

        for (int i = 0; i < (this.genes.size() - 1); i++) {
            int k = 0;
            int geneColumnI = this.genes.get(i);
            for (int j = i + 1; j < this.genes.size(); j++) {
                k++;
                int geneColumnJ = this.genes.get(j);
                if ((geneColumnI == geneColumnJ + k) || (geneColumnI == geneColumnJ - k)) {
                    fitness++;
                }
            }
        }

        return fitness;
    }

    @Override
    public List<Individual> getChildren(Individual individual) {
        IndividualNQueens individualNQueen = (IndividualNQueens) individual;
        Random random = new Random();

        // Filhos resultantes do Crossover
        List<Individual> childrenCrossover = new ArrayList<>();

        IndividualNQueens children1;
        IndividualNQueens children2;

        List<Integer> genesChildren1 = new ArrayList<>();
        List<Integer> genesChildren2 = new ArrayList<>();

        // Valor do corte
        int cut = random.nextInt(this.genes.size());

        List<Integer> cloneGenesParents1 = new ArrayList<>(this.genes);
        List<Integer> cloneGenesParents2 = new ArrayList<>(individualNQueen.genes);

        List<Integer> initialPartGenesParents1 = new ArrayList<>(cloneGenesParents1.subList(0, cut));
        List<Integer> finalPartGenesParents1 = new ArrayList<>(
                cloneGenesParents1.subList(cut, cloneGenesParents1.size()));

        List<Integer> initialPartGenesParents2 = new ArrayList<>(cloneGenesParents2.subList(0, cut));
        List<Integer> finalPartGenesParents2 = new ArrayList<>(
                cloneGenesParents2.subList(cut, cloneGenesParents2.size()));

        // Gerar genes dos Filhos

        genesChildren1.addAll(initialPartGenesParents1);

        genesChildren2.addAll(initialPartGenesParents2);

        List<Integer> restChildren1 = new ArrayList<>();
        List<Integer> restChildren2 = new ArrayList<>();

        for (int i = 0; i < finalPartGenesParents2.size(); i++) {
            if (genesChildren1.contains(finalPartGenesParents2.get(i))) {
                genesChildren1.add(-1);
                restChildren1.add(finalPartGenesParents2.get(i));
            } else {
                genesChildren1.add(finalPartGenesParents2.get(i));
            }
        }

        for (int i = 0; i < finalPartGenesParents1.size(); i++) {
            if (genesChildren2.contains(finalPartGenesParents1.get(i))) {
                genesChildren2.add(-1);
                restChildren2.add(finalPartGenesParents1.get(i));
            } else {
                genesChildren2.add(finalPartGenesParents1.get(i));
            }
        }

        Collections.shuffle(restChildren1);
        Collections.shuffle(restChildren2);

        while (genesChildren1.contains(-1)) {
            int index = genesChildren1.indexOf(-1);
            genesChildren1.set(index, restChildren2.get(restChildren2.size() - 1));
            restChildren2.remove(restChildren2.size() - 1);
        }

        while (genesChildren2.contains(-1)) {
            int index = genesChildren2.indexOf(-1);
            genesChildren2.set(index, restChildren1.get(restChildren1.size() - 1));
            restChildren1.remove(restChildren1.size() - 1);
        }

        /*
         * if (genesChildren1.contains(-1) || genesChildren2.contains(-1)) {
         * for (int i = 0; i < genesChildren1.size(); i++) {
         * if (genesChildren1.get(i) == -1) {
         * genesChildren1.set(i, restChildren2.get(restChildren2.size() - 1));
         * restChildren2.remove(restChildren2.size() - 1);
         * }
         * if (genesChildren2.get(i) == -1) {
         * genesChildren2.set(i, restChildren1.get(restChildren1.size() - 1));
         * restChildren1.remove(restChildren1.size() - 1);
         * }
         * }
         * }
         */

        children1 = new IndividualNQueens(genesChildren1);
        children1.getEvaluation();

        children2 = new IndividualNQueens(genesChildren2);
        children2.getEvaluation();

        childrenCrossover.add(children1);
        childrenCrossover.add(children2);

        return childrenCrossover;
    }

    @Override
    public Individual getMutant() {
        Random random = new Random();

        Individual mutant;

        List<Integer> cloneGenesParents = new ArrayList<>(this.genes);

        int position1 = random.nextInt(cloneGenesParents.size());

        int position2;

        do {
            position2 = random.nextInt(cloneGenesParents.size());
        } while (position2 == position1);

        int gen1 = cloneGenesParents.get(position1);
        int gen2 = cloneGenesParents.get(position2);

        cloneGenesParents.set(position1, gen2);
        cloneGenesParents.set(position2, gen1);

        mutant = new IndividualNQueens(cloneGenesParents);

        mutant.getEvaluation();

        return mutant;
    }

    @Override
    public String toString() {
        return this.genes.toString() + " " + this.evaluation.toString();
    }

    public List<Integer> getGenes() {
        return this.genes;
    }

}
