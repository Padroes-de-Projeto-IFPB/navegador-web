package br.edu.ifpb.padroes.navegador.comandos;

import br.edu.ifpb.padroes.navegador.Navegador;
import br.edu.ifpb.padroes.navegador.NavegadorCommand;
import br.edu.ifpb.padroes.navegador.NavegadorException;
import lombok.Getter;

public class AdicionarUrlCommand implements NavegadorCommand {

    private final Navegador navegador;
    @Getter
    private String dados;

    public AdicionarUrlCommand(Navegador navegador, String url) {
        this.navegador = navegador;
        this.dados = url;
    }

    @Override
    public void executar() throws NavegadorException {
        this.navegador.getUrlsValidas().add(dados);
    }

    @Override
    public void desfazer() {
        this.navegador.getUrlsValidas().remove(dados);
    }

}
