package model;

import java.util.List;

public class Operador {
    private int idOperador;
    private String nome;

    public int getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(int idOperador) {
        this.idOperador = idOperador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Operador(int idOperador, String nome) {
        this.idOperador = idOperador;
        this.nome = nome;
    }

    public Operador() {
    }

    public static Operador buscarPorId(List<Operador> operadores, int id) {
        for (Operador operador : operadores) {
            if (operador.getIdOperador() == id) {
                return operador;
            }
        }
        return null;
    }
}
