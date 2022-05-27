package br.edu.ifpb.padroes.navegador;

public interface NavegadorCommand {

    void executar() throws NavegadorException;
    void desfazer();
    String getDados();

}
