package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int idPedido;
    private LocalDateTime dataPedido;
    private String statusAtual;
    private List<Etapa> etapas;
    private String impressora;

    public int getId() {
        return idPedido;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public String getStatusAtual() {
        return statusAtual;
    }

    public String getImpressora() {
        return impressora;
    }

    public void setImpressora(String impressora) {
        this.impressora = impressora;
    }

    public List<Etapa> getEtapas() {
        return etapas;
    }

    public Pedido(int idPedido) {
        this.idPedido = idPedido;
        this.dataPedido = LocalDateTime.now();
        this.etapas = new ArrayList<>();
        this.impressora = impressora;

        String[] nomesEtapas = {"Preparação", "Impressão", "Película", "Corte Lateral", "Corte Central", "Visualização", "Digitalização", "CQ"};

        for (int i = 0; i < nomesEtapas.length; i++) {
            etapas.add(new Etapa(nomesEtapas[i], i + 1));
        }
    }

    public Pedido() {

    }

    public Etapa getEtapaAtual() {
        for (Etapa etapa : etapas) {
            if (!etapa.isConcluida()) {
                return etapa;
            }
        }
        return null;
    }

    public void avancarEtapa(Operador operador) {
        Etapa atual = getEtapaAtual();
        if (atual != null && !atual.isConcluida()) {
            atual.marcarConcluidaEm(operador);
            System.out.println("Etapa '" + atual.getNome() + "' concluída por " + operador.getNome());
        } else {
            System.out.println("Não há etapas pendentes ou a etapa já foi concluída.");
        }
    }

    public static Pedido buscarPorId(List<Pedido> pedidos, int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }

}
