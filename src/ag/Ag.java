package ag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import individual.Individual;
import interfaces.IndividualFactory;

public class Ag {

    private double sumEvaluations;

    public Individual run(int nPop, IndividualFactory indFactory, int nElite, int nGer) {

        List<Individual> popParents = new ArrayList<>();
        List<Individual> popChildren;
        List<Individual> popMutant;
        List<Individual> popJoin;

        List<Individual> newPop;

        // Gerar nPop Individuos inciais
        for (int i = 0; i < nPop; i++) {
            Individual individual = indFactory.getIndividual();
            individual.getEvaluation();
            popParents.add(individual);
        }

        for (int i = 0; i < nGer; i++) {
            popChildren = new ArrayList<>();
            popMutant = new ArrayList<>();
            popJoin = new ArrayList<>();
            newPop = new ArrayList<>();

            // Embaralhar popParents para realizar o Crossover de forma aleatoria
            Collections.shuffle(popParents);

            // Crossover
            for (int j = 0; j <= (nPop - 1); j += 2) {
                Individual parents1 = popParents.get(j);
                Individual parents2 = popParents.get(j + 1);

                List<Individual> children = parents1.getChildren(parents2);
                popChildren.addAll(children);
            }

            // Mutação
            for (int k = 0; k < popParents.size(); k++) {
                Individual parents1 = popParents.get(k);
                Individual mutant = parents1.getMutant();
                popMutant.add(mutant);
            }

            // Juntar a população gerada
            popJoin.addAll(popParents);
            popJoin.addAll(popChildren);
            popJoin.addAll(popMutant);

            newPop.addAll(selection(popJoin, nPop, nElite));

            Collections.sort((ArrayList<Individual>) newPop);

            popParents.clear();
            popParents.addAll(newPop);

        }

        return (popParents.get(popParents.size() - 1));
    }

    private double calculateSumEvaluations(List<Individual> popJoin) {
        this.sumEvaluations = 0;
        for (int i = 0; i < popJoin.size(); i++) {
            this.sumEvaluations += popJoin.get(i).getEvaluation();
        }

        return this.sumEvaluations;
    }

    private int roulette(List<Individual> popJoin) {
        int i;
        double aux = 0;

        calculateSumEvaluations(popJoin);

        double limit = Math.random() * this.sumEvaluations;

        for (i = 0; ((i < popJoin.size()) && (aux < limit)); i++) {
            aux += popJoin.get(i).getEvaluation();
        }

        i--;

        return i;
    }

    private List<Individual> selection(List<Individual> popJoin, int nInd, int elitism) {
        List<Individual> newPop = new ArrayList<>();
        List<Individual> popAux = new ArrayList<>(popJoin);
        int index, lasIndividual;

        Collections.sort(popAux);

        for (int i = 0; i < elitism; i++) {
            lasIndividual = popAux.size() - 1;
            newPop.add(popAux.get(lasIndividual));
            popAux.remove(lasIndividual);
        }

        nInd = nInd - elitism;

        Collections.shuffle(popAux);

        for (int i = 0; i < nInd; i++) {
            index = roulette(popAux);
            newPop.add(popAux.get(index));
            popAux.remove(index);
        }

        return newPop;
    }
}