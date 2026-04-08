import javax.print.DocFlavor;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    // global
    static Scanner entrada = new Scanner(System.in);
    static Veiculo [] veiculo = new Veiculo[5];
    static int indexVeiculo;
    static Registro [] registro = new Registro[10];
    static int indexRegistro;

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("------ Estacionamento ParkEasy ------ ");
            System.out.println("--- 1. Entrada de veiculos --- ");
            System.out.println("--- 2. Saida de veiculos --- ");
            System.out.println("--- 3. Imprimir  veiculos  estacionados --- ");
            System.out.println("--- 4. Imprimir a receita --- ");
            System.out.println("--- 5. Finalizar --- ");
            opcao=entrada.nextInt();

            switch (opcao){
                case 1 -> entrada ();
                case 2 -> saida ();
                case 3 -> imprimirVeiculos();
                case 4 -> receita();
                case 5 -> System.out.printf("Obrigado por usar nosso aplicativo !! Volte sempre ");
                default -> System.out.println("Opção inválida");

            }
        } while(opcao != 5);
        System.out.println();


    }

    private static void entrada() {
        String nome;
        long CPF;
        String placa;
        String marca;
        String modelo;
        String horaEntrada;

        Veiculo veiculoEncontrado = pesquisarVeiculo();

        if (veiculoEncontrado == null){
            System.out.println("Nome do proprietario: ");
            nome=entrada.next();
            System.out.println("CPF: ");
            CPF=entrada.nextLong();
            System.out.println(" Placa do carro: ");
            placa=entrada.next().toLowerCase();
            System.out.println("Marca do seu carro: ");
            marca=entrada.next();
            System.out.println("Modelo do seu carro: ");
            modelo=entrada.next();

            Proprietario proprietario = new Proprietario(CPF, nome);

            veiculoEncontrado = new Veiculo(placa,marca,modelo,proprietario);
            veiculo[indexVeiculo] = veiculoEncontrado;

            indexVeiculo++;
        }
        System.out.print("Hora de entrada no formato hh:mm -->");
        horaEntrada=entrada.next();
        registro[indexRegistro]= new Registro(horaEntrada,veiculoEncontrado);
        indexRegistro++;
    }

    private static void saida() {
        String horaSaida;
        double valor;

        // puxar o localizarVeiculo
        Registro carroAchado = localizarVeiculo();
        if (carroAchado != null){
            System.out.println("Hora de saida (hh:mm) --> ");
            horaSaida = entrada.next();
            carroAchado.horaSaida= horaSaida;

            valor = carroAchado.calularTotal();
            System.out.println("Valor total a pagar R$ " + valor);

        }
    }

    private static Veiculo pesquisarVeiculo (){
        String placa;

        System.out.print(" Placa ---> ");
        placa=entrada.next().toLowerCase();

        for (int i = 0; i < indexVeiculo; i++) {
            if (veiculo[i].placa.equals(placa)){
                return veiculo[i];
            }
        }
        System.out.println(" ------ Veiculo não cadastrado ------ ");
        return null;
    }

    private static void imprimirVeiculos() {
        System.out.println(" ##### VEICULOS ESTACIONADOS #####");
        for (int i = 0; i < indexRegistro; i++) {

            if (registro[i].horaSaida == null) {
                System.out.println("Placa --> " + registro[i].veiculo.placa);
            }
        }
    }

    private static Registro localizarVeiculo() {
        String placa;

        System.out.print(" Placa ---> ");
        placa=entrada.next().toLowerCase();

        for (int i = 0; i < indexRegistro; i++) {
            if (registro[i].veiculo.placa.equals(placa)){
                return registro[i];
            }
        }
        System.out.println(" ------ Veiculo não cadastrado ------ ");
        return null;

    }

    private static void receita() {
        double total = 0;
        // ele vai olhar os registros
        for (int i = 0; i < indexRegistro; i++) {
            // vamos apenas ligar se ele já saiu
            if (registro[i].horaSaida != null){
                total += registro[i].calularTotal();
            }
        }
        System.out.println("Receita até o momento ---> R$ " + total);

    }

}

