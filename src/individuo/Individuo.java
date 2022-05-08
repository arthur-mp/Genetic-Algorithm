package individuo;

import java.util.List;

public abstract class Individuo implements Comparable<Individuo> {
    protected Double avaliacao;

    public abstract Double avaliar();

    public abstract List<Individuo> getChildren(Individuo individuo);

    public abstract Individuo getMutant();

    public Double getAvaliacao() {
        if (this.avaliacao == null) {
            this.avaliacao = this.avaliar();
        }

        return this.avaliacao;
    }

    @Override
    public int compareTo(Individuo individuo) {
        if (this.getAvaliacao() < individuo.getAvaliacao()) {
            return -1;
        } else {
            if (this.getAvaliacao() > individuo.getAvaliacao()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
