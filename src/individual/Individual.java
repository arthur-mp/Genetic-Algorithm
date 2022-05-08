package individual;

import java.util.List;

public abstract class Individual implements Comparable<Individual> {
    protected Double evaluation;

    public abstract Double evaluate();

    public abstract List<Individual> getChildren(Individual individual);

    public abstract Individual getMutant();

    public Double getEvaluation() {
        if (this.evaluation == null) {
            this.evaluation = this.evaluate();
        }

        return this.evaluation;
    }

    @Override
    public int compareTo(Individual individual) {
        if (this.getEvaluation() < individual.getEvaluation()) {
            return -1;
        } else {
            if (this.getEvaluation() > individual.getEvaluation()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
