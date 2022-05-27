package br.edu.ifpb.padroes.navegador;

import br.edu.ifpb.padroes.navegador.comandos.AcessarUrlCommand;

import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Navegador navegador = new Navegador();
        while (true) {
            System.out.println("Digite o comando que deseja acessar (#add, #undo, #go, #sair e o dado correspondente quando aplicável");
            Scanner scanner = new Scanner(System.in);
            String entrada = scanner.nextLine();
            String comando = entrada.split(" ")[0];
            if (comando.equals("sair")) {
                break;
            }
            String dado = "";
            if (!comando.equals("#undo") && !comando.equals("#back")) {
                dado = entrada.split(" ")[1];
            }
            navegador.executarComando(comando, dado);
            System.out.println("Página atual = "+navegador.getPaginaAtual());
            System.out.println("Histórico = "+ navegador.getHistorico().stream()
                    .filter(AcessarUrlCommand.class::isInstance).map(NavegadorCommand::getDados).collect(Collectors.joining(", ")));
            System.out.println("Urls válidas = "+ String.join(", ", navegador.getUrlsValidas()));
        }
    }

}
