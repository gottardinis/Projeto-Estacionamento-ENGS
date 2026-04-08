import java.time.Duration;
import java.time.LocalTime;

public class Registro {
    Veiculo veiculo;
    String horaEntrada;
    String horaSaida;


    public Registro(String horaEntrada, Veiculo veiculo) {
        this.horaEntrada = horaEntrada;
        this.veiculo = veiculo;
    }

    public double calularTotal() {
        LocalTime inicio, fim;
        long minutos;
        double valor;

        inicio = LocalTime.parse(horaEntrada);
        fim = LocalTime.parse(horaSaida);
        minutos = Duration.between(inicio, fim).toMinutes();
        valor = minutos * 0.75;

        return valor;

    }
}
