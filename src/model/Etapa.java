package model;

import java.time.LocalDateTime;

public class Etapa {
    private String nome;
    private int ordem;
    private LocalDateTime concluidaEm;
    private Operador operador;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public Etapa(String nome, int ordem) {
        this.nome = nome;
        this.ordem = ordem;
        this.concluidaEm = null;
        this.operador = null;
    }

    public void marcarConcluidaEm(Operador operador) {
        this.operador = operador;
        this.concluidaEm = LocalDateTime.now();
    }

    public boolean isConcluida() {
        return concluidaEm != null;
    }

    public LocalDateTime getConcluidaEm() {
        return concluidaEm;
    }
}
