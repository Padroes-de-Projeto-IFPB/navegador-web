package br.edu.ifpb.padroes.navegador;

import br.edu.ifpb.padroes.navegador.comandos.AcessarUrlCommand;
import br.edu.ifpb.padroes.navegador.comandos.AdicionarUrlCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Navegador {

    @Getter
    private final List<String> urlsValidas = new ArrayList<>();

    @Getter
    private final Queue<NavegadorCommand> historico = new ArrayDeque<>();

    @Getter
    @Setter
    private String paginaAtual = "";

    public void executarComando(String comando, String dado) {
        switch(comando) {
            case "#add":
                AdicionarUrlCommand adicionarUrlCommand = new AdicionarUrlCommand(this, dado);
                try {
                    adicionarUrlCommand.executar();
                    this.historico.add(adicionarUrlCommand);
                    System.out.printf("\nadicionada url %s\n", dado);
                } catch (NavegadorException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "#undo":
                System.out.println("Desfazendo última ação realizada");
                Optional.ofNullable(historico.poll()).ifPresent(NavegadorCommand::desfazer);
                break;
            case "#back":
                System.out.println("Voltando para última página");
                historico.stream()
                        .filter(AcessarUrlCommand.class::isInstance)
                        .findFirst()
                        .ifPresent(NavegadorCommand::desfazer);
                historico.remove();
                break;
            case "#go":
                AcessarUrlCommand acessarUrlCommand = new AcessarUrlCommand(this, dado);
                try {
                    acessarUrlCommand.executar();
                    this.historico.add(acessarUrlCommand);
                } catch (NavegadorException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println("Comando inválido!");

        }

    }

}
