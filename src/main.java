import model.Etapa;
import model.Operador;
import model.Pedido;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        var sc = new Scanner(System.in);
        List<Operador> operadores = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();
        int idPedido = 1;
        int opcao = 0;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Cadastrar operador");
            System.out.println("2. Operação");
            System.out.printf("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1 -> {
                    var operador = new Operador();
                    System.out.printf("Digite o nome do operador: ");
                    operador.setNome(sc.next());
                    operador.setIdOperador(operadores.size() + 1);
                    operadores.add(operador);
                    System.out.println("Operador cadastrado com sucesso!" + "\nNome: " + operador.getNome() + "\nId: " + operador.getIdOperador());
                    System.in.read();
                }
                case 2 -> {
                    boolean menuPrincipal = false;
                    do {
                        int escolha;
                        System.out.println("\n--- OPERAÇÕES ---");
                        System.out.println("1. Criar novo pedido");
                        System.out.println("2. Pesquisar pedido");
                        System.out.println("3. Ver histórico de um pedido");
                        System.out.println("4. Voltar ao menu principal");
                        System.out.print("Escolha: ");
                        escolha = sc.nextInt();

                        switch (escolha) {
                            case 1 -> {
                                var pedido = new Pedido(idPedido++);
                                pedidos.add(pedido);
                                System.out.println("Pedido: " + pedido.getId());
                                System.in.read();
                            }
                            case 2 -> {
                                System.out.print("Digite o ID do pedido: ");
                                var id = sc.nextInt();
                                var pedidoSelecionado = Pedido.buscarPorId(pedidos, id);
                                if (pedidoSelecionado == null) {
                                    System.out.println("Pedido não encontrado.");
                                    System.in.read();
                                    break;
                                }
                                var etapaAtual = pedidoSelecionado.getEtapaAtual();
                                if (etapaAtual != null) {
                                    System.out.println("Etapa: " + etapaAtual.getNome());
                                    System.out.print("Digite o ID do operador: ");
                                    int idOperador = sc.nextInt();
                                    sc.nextLine();

                                    var operador = Operador.buscarPorId(operadores, idOperador);
                                    if (operador == null) {
                                        System.out.println("Operador não encontrado.");
                                        System.in.read();
                                        break;
                                    }

                                    if (etapaAtual.getNome().equals("Impressão")) {
                                        System.out.println("Pedido: " + pedidoSelecionado.getId());
                                        System.out.print("Informe a Impressora: ");
                                        String impressora = sc.nextLine();
                                        pedidoSelecionado.setImpressora(impressora);
                                        pedidos.add(pedidoSelecionado);
                                    }
                                    System.out.println("Pedido: " + pedidoSelecionado.getId() + "\nEtapa '" + etapaAtual.getNome() + "' será concluída por " + operador.getNome() + "." + " Impresso em: " + pedidoSelecionado.getImpressora() + ".");
                                    System.out.print("Pressione ENTER para confirmar ou digite qualquer tecla para cancelar: ");
                                    String confirmacao = sc.nextLine();

                                    if (confirmacao.isEmpty()) {
                                        etapaAtual.marcarConcluidaEm(operador);
                                        System.out.println("Etapa" + etapaAtual.getNome() + " marcada como concluída por " + operador.getNome() + ".");
                                    } else {
                                        System.out.println("Confirmação cancelada. Etapa não foi concluída.");
                                        break;
                                    }


                                } else {
                                    System.out.println("Todas as etapas já foram concluídas para esse pedido.");
                                    System.in.read();
                                }
                            }
                            case 3 -> {
                                System.out.println("Digite o ID do pedido: ");
                                var id = sc.nextInt();
                                Pedido pedido = Pedido.buscarPorId(pedidos, id);
                                if (pedido == null) {
                                    System.out.println("Pedido não encontrado.");
                                    System.in.read();
                                    break;
                                }
                                System.out.println("\n--- Histórico do Pedido " + pedido.getId() + " ---");
                                for (var etapa : pedido.getEtapas()) {
                                    System.out.println(etapa.getOrdem() + " - " + etapa.getNome() +
                                            " | Status: " + (etapa.isConcluida() ? "Concluida por " + etapa.getOperador().getNome() + " às " + etapa.getConcluidaEm() + "Impressora: " + pedido.getImpressora() : "Pendente"));
                                }
                                System.in.read();
                            }
                            case 4 -> {
                                System.out.println("Voltar ao menu principal...");
                                System.in.read();
                                menuPrincipal = true;
                            }
                            default -> System.out.println("Opção invalida");
                        }
                    } while (!menuPrincipal);
                }
                default -> System.out.println("Opção invalida");
            }
        } while (true);
    }
}
