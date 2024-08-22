import classes.Process;
import classes.ProcessTable;

public class Main {
    public static void main(String[] args) {
        Process p0 = new Process(0, 10000);
        Process p1 = new Process(1, 5000);
        Process p2 = new Process(2, 7000);
        Process p3 = new Process(3, 3000);
        Process p4 = new Process(4, 3000);
        Process p5 = new Process(5, 8000);
        Process p6 = new Process(6, 2000);
        Process p7 = new Process(7, 5000);
        Process p8 = new Process(8, 4000);
        Process p9 = new Process(9, 10000);

         // Criação da tabela de processos e inicialização com os processos criados.
        ProcessTable processTable = new ProcessTable(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
        final int QUANTUM = 1000;// Define limite de quantum para o processamento.

        Process AtualP;// Variável para armazenar o processo atual em execução.
        System.out.println("-".repeat(150));
        System.out.printf("%-20s%-30s%-20s%-20s%-20s%-20s%s%n", "PID", "tempo de execução", "PC", "Estado", "I/O",
                "CPU", "Transição");
        System.out.println("-".repeat(150));

        // Loop principal que continua enquanto houver processos não terminados.
        while (processTable.esseProcNaoTerminou()) {
            AtualP = processTable.next(); //Retorna o próximo processo não concluído.
            if (AtualP.isWaiting()) { //Se o processo atual estiver aguardando a conclusão de uma operação de E/S.
                AtualP.verDDisponiveis();
                System.out.println(AtualP.formatoTabela() + "WAITING -> " + AtualP.getState());
                continue;
            }


            AtualP.run(); //O processo é carregado na CPU para processamento.
            System.out.println(AtualP.formatoTabela() + "READY -> RUNNING");
            for (int i = 0; i <  QUANTUM; i++) { 
                AtualP.process();
                if (AtualP.isTerminated() || AtualP.isWaiting()) {
                    //Mudança de contexto porque o processo executou tudo o que precisava ou uma operação de E/S foi realizada.
                    break;
                }
            }
            // Se o processo ainda está em execução, marca-o como pronto para o próximo ciclo.
            if (AtualP.isRunning()) { 
                AtualP.getReady();
            }

            System.out.println(AtualP.formatoTabela() + "RUNNING -> " + AtualP.getState());
        }
    }
}
