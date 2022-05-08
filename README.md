# Algoritmo Genético

## Implementação N-Rainhas

Implementado em Java o algoritmo genético aplicado ao caso das N-Rainhas.

Disciplina: Inteligência Artificial

## Algortimo Genético

![Diagrama Algoritmo Genético](https://icaroagostino.github.io/post/sbo/ga.png)

O algoritmo genético busca uma otimização global. Baseia-se nos mecanismos de seleção natural e de genética.
Basicamente é gerado uma população inicial de indivíduos, onde um indivíduo possui um gene proóprio.
A partir de dois indivíduos é gerado por cruzamento dois filhos, e a partir de um indivíduo apenas é gerado um mutate, que possuí genes modificados.
Cada indivíduo possui um fitness, um atributo que indica a qualidade do indivíduo.
Gerado os inidivíduos iniciais, ou os pais, os filhos e os mutantes, ambos passam por uma seleção de geração, onde uma quantidade n que é mais "capacitada" e uma quantidade m aleatória é selecionada para proxima geração.
A escolha aleatória, se dá pelo uso da "roleta viciada", na qual cada indivíduo recebe um pedaço proporcional à sua avaliação, depois é rodado a roleta e será selecionado o indivíduo sobre o qual ela parar.
O algoritmo chega em seu fim quando a condição de parada é satisfeita, condição essa que é definida pela implementação.

## Implementação N-Rainhas

No código elaborado tem como base a implementação das N-Rainhas, onde é definido o número da população de indivíduos "nPop", o número de Rainhas "nRainhas", a quantidade de geração "nGer", e a quantidade de indivíduos elite "nElite".

    . nPop: Indica a quantide de pais, filhos, mutatentes que precisam ser gerados;
    . nRainhas: Ou "genes", indica a quantidade de rainhas que ficaram no tabuleiro nRainhas x nRainhas;
    . nGer: Indica o critério de parada, ou seja, o algoritmo só chegará em seu fim, após ser executado nGer vezes;
    . nElite: Indica os mais "capacitados" que são selecionados para proxima geração.

Ao critério de parada ser satisfeito, é escolhido o indivíduo com melhor avaliação, que indica o caso em que em um tabuleiro as rainhas tiveram menos colisões entre si.

O resultado indica a ultima geração, a avaliação (que é o número de colisões), e as rainhas.
O valor de cada rainha indica a linha da coluna (index do array), que a rainha se encontra.
