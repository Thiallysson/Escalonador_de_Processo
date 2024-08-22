package classes;
import java.util.Random;
import estados.estado;

public class Process {
    private final int pid, tempoTotalP;
    private int tempExeProcesso, programCounter, esOperacao, cpuUsage;
    private estado Estado;

    // Construtor que inicializa o processo com estado 'NEW'.
    public Process(int pid, int tempoTotalP) {
        this(pid, tempoTotalP, estado.NEW);
    }

    // Construtor que permite inicializar o processo com um estado específico
    public Process(int pid, int tempoTotalP, estado Estado) {
        this.pid = pid;
        this.tempoTotalP = tempoTotalP;
        this.programCounter = 1; //tempExeProcesso + 1.
        this.Estado = Estado;
    }

    // Método para simular a execução do processo.
    public void process() {
        if (isTerminated()) {
            return;
        }
        tempExeProcesso++;
        programCounter++; //A próxima operação a ser realizada..
        if (tempExeProcesso == tempoTotalP) {
            terminate();
            return;
        }
        //Possível operação de E/S..
        Random r = new Random();
        int numRandom = r.nextInt(100); //0-99
        if (numRandom < 5) {
            waitIO();
        }
    }

    // Método para verificar se dados estão disponíveis para o processo.
    public void verDDisponiveis() {
        Random r = new Random();
        int numRandom = r.nextInt(100);
        if (numRandom < 30) {
            getReady();
        }
    }

    public void run() {
        Estado = Estado.RUNNING;
        cpuUsage++;
    }

    public void waitIO() {
        Estado = Estado.WAITING;
        esOperacao++;
    }

    public void getReady() {
        Estado = Estado.READY;
    }

    public void terminate() {
        Estado = Estado.TERMINATED;
    }

    public boolean isTerminated() {
        return Estado == Estado.TERMINATED;
    }

    public boolean isWaiting() {
        return Estado == Estado.WAITING;
    }

    public boolean isRunning() {
        return Estado == Estado.RUNNING;
    }

    public int getPID() {
        return pid;
    }

    public estado getState() {
        return Estado;
    }
    //Método que retorna uma string formatada.
    public String formatoTabela() {
        return String.format("%-20d%-30d%-20d%-20s%-20d%-20d", pid, tempExeProcesso, programCounter, Estado,
                esOperacao, cpuUsage);
    }
}
