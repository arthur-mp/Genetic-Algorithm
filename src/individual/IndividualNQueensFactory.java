package individual;

import interfaces.IndividualFactory;

public class IndividualNQueensFactory implements IndividualFactory {
    private int nQueens;

    public IndividualNQueensFactory(int nQueens) {
        this.nQueens = nQueens;
    }

    @Override
    public Individual getIndividual() {
        return new IndividualNQueens(nQueens);
    }

}