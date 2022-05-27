package br.edu.ifpb.padroes.navegador.comandos;

import br.edu.ifpb.padroes.navegador.Navegador;
import br.edu.ifpb.padroes.navegador.NavegadorCommand;
import br.edu.ifpb.padroes.navegador.NavegadorException;
import lombok.Getter;

import java.util.Optional;

public class AcessarUrlCommand implements NavegadorCommand {

    private final Navegador navegador;

    @Getter
    private final String dados;

    public AcessarUrlCommand(Navegador navegador, String url) {
        this.navegador = navegador;
        this.dados = url;
    }

    @Override
    public void executar() throws NavegadorException {
        if (!this.navegador.getUrlsValidas().contains(dados)) {
            throw new NavegadorException("Página não encontrada");
        } else {
            this.navegador.setPaginaAtual(dados);
        }
    }

    @Override
    public void desfazer() {
        navegador.getHistorico().stream()
                .filter(h -> !h.getDados().equals(navegador.getPaginaAtual()))
                .findFirst()
                .ifPresent( cmd -> this.navegador.setPaginaAtual(cmd.getDados()));
    }

}
