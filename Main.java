import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int LIMITE_CACHE = 10;

    public static void main(String[] args) {

        List<Pessoa> banco = new ArrayList<>();
        banco.add(new Pessoa(1, "Isis Ferreira", 16));
        banco.add(new Pessoa(2, "Evelyn Deveza", 17));
        banco.add(new Pessoa(3, "Natielie Santos", 17));
        banco.add(new Pessoa(4, "Claudineia da Rocha", 43));
        banco.add(new Pessoa(5, "Levi Leite", 17));

        LinkedList<Pessoa> cache = new LinkedList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            IO.println("\nDigite o ID da pessoa (ou -1 para sair): ");
            int idBuscado = scanner.nextInt();

            if (idBuscado == -1) {
                IO.println("Seu programa foi encerrado.");
                break;
            }


            Pessoa pessoaEncontrada = buscarPorId(cache, idBuscado);

            if (pessoaEncontrada != null) {
                IO.println("Pessoa encontrada no cache: " + pessoaEncontrada);
            } else {

                pessoaEncontrada = buscarPorId(banco, idBuscado);

                if (pessoaEncontrada != null) {

                    if (cache.size() >= LIMITE_CACHE) {
                        Pessoa removida = cache.removeFirst();
                        IO.println("[Cache Cheio] Pessoa mais antiga removida do cache: " + removida.getNome());
                    }

                    cache.addLast(pessoaEncontrada);
                    IO.println("Pessoa buscada no banco e adicionada ao cache: " + pessoaEncontrada);
                } else {
                    IO.println("Pessoa com ID " + idBuscado + " não encontrada no banco de dados.");
                }
            }
        }

        scanner.close();
    }

    private static Pessoa buscarPorId(List<Pessoa> lista, int id) {
        for (Pessoa p : lista) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}