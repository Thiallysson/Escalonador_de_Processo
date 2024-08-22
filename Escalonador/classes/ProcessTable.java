package classes;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class ProcessTable {
    private final List<Process> table;
    private int Indice;

    {
        table = new ArrayList<>();// Inicializa a lista de processos. Bloco de inicialização de instância.
    }

    public ProcessTable(Process... processes) {
        // Construtor que recebe um número variável de processos e os adiciona à lista.
        Collections.addAll(table, processes);
    }

    public Process next() {
        Process p;
        int tabelaFormato = table.size();
        for (int i = 0; i < tabelaFormato; i++) {
            p = table.get(Indice);
            Indice = Indice < tabelaFormato - 1 ? Indice + 1 : 0;
            if (!p.isTerminated()) {
                // Retorna o próximo processo que não tenha terminado.
                return p;
            }
        }
        // Retorna null se todos os processos estiverem terminados.
        return null;
    }

    public boolean esseProcNaoTerminou() {
        // Verifica se existe algum processo na tabela que ainda não terminou
        for (Process p : table) {
            if (!p.isTerminated()) {
                return true;// Retorna true se encontrar um processo que não tenha terminado.
            }
        }
        return false;
    }
}
