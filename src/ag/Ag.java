package ag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import individuo.Individuo;
import interfaces.IndividuoFactory;

public class Ag {

    private double sumEvaluations;

    public Individuo run(int nPop, IndividuoFactory indFactory, int nElite, int nGer) {

        List<Individuo> popParents = new ArrayList<>();
        List<Individuo> popChildren;
        List<Individuo> popMutant;
        List<Individuo> popJoin;

        List<Individuo> newPop;

        // Gerar nPop Individuos inciais
        for (int i = 0; i < nPop; i++) {
            Individuo individuo = indFactory.getIndividuo();
            individuo.getAvaliacao();
            popParents.add(individuo);
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
                Individuo parents1 = popParents.get(j);
                Individuo parents2 = popParents.get(j + 1);

                List<Individuo> children = parents1.getChildren(parents2);
                popChildren.addAll(children);
            }

            // Mutação
            for (int k = 0; k < popParents.size(); k++) {
                Individuo parents1 = popParents.get(k);
                Individuo mutant = parents1.getMutant();
                popMutant.add(mutant);
            }

            // Juntar a população gerada
            popJoin.addAll(popParents);
            popJoin.addAll(popChildren);
            popJoin.addAll(popMutant);

            newPop.addAll(selection(popJoin, nPop, nElite));

            Collections.sort((ArrayList<Individuo>) newPop);

            popParents.clear();
            popParents.addAll(newPop);

        }

        return (popParents.get(0));
    }

    private double calculateSumEvaluations(List<Individuo> popJoin) {
        this.sumEvaluations = 0;
        for (int i = 0; i < popJoin.size(); i++) {
            this.sumEvaluations += popJoin.get(i).getAvaliacao();
        }

        return this.sumEvaluations;
    }

    private int roulette(List<Individuo> popJoin) {
        int i;
        double aux = 0;

        calculateSumEvaluations(popJoin);

        double limit = Math.random() * this.sumEvaluations;

        for (i = 0; ((i < popJoin.size()) && (aux < limit)); i++) {
            aux += popJoin.get(i).getAvaliacao();
        }

        i--;

        return i;
    }

    private List<Individuo> selection(List<Individuo> popJoin, int nInd, int elitism) {
        List<Individuo> newPop = new ArrayList<>();
        List<Individuo> popAux = new ArrayList<>(popJoin);
        int index, lasIndividual;

        // Ordenação decrescente de getAvaliacao,
        // maior avaliação == pior é o fitness, ou seja, mais conflitos.
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