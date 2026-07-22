import java.util.List;
import java.util.Scanner;

import dao.ClienteDao;
import dao.ProdutoDao;
import dao.PedidoDao;
import modelos.Cliente;
import modelos.Produto;
import modelos.Pedido;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ProdutoDao produtoDao = new ProdutoDao();
		ClienteDao clienteDao = new ClienteDao();

		Pedido pedidoAtual = null;

		int opcaoPrincipal;


		do {

			System.out.println("\n==============================");
			System.out.println("       MENU PRINCIPAL");
			System.out.println("==============================");
			System.out.println("1 - Produtos");
			System.out.println("2 - Clientes");
			System.out.println("3 - Pedidos");
			System.out.println("0 - Sair");
			System.out.print("Opção: ");

			opcaoPrincipal = sc.nextInt();
			sc.nextLine();


			switch(opcaoPrincipal) {


			// ================= PRODUTOS =================

			case 1:

				int opProduto;


				do {

					System.out.println("\n===== MENU PRODUTOS =====");
					System.out.println("1 - Cadastrar Produto");
					System.out.println("0 - Voltar");
					System.out.print("Opção: ");


					opProduto = sc.nextInt();
					sc.nextLine();


					switch(opProduto) {


					case 1:

						System.out.print("Descrição: ");
						String descricao = sc.nextLine();


						System.out.print("Preço: ");
						double preco = sc.nextDouble();


						System.out.print("Estoque: ");
						int estoque = sc.nextInt();
						sc.nextLine();


						Produto produto = new Produto(
								descricao,
								preco,
								estoque
						);


						produtoDao.salvar(produto);


						break;



					case 0:

						break;



					default:

						System.out.println("Opção inválida!");

					}


				} while(opProduto != 0);


				break;



			// ================= CLIENTES =================


			case 2:


				int opCliente;


				do {


					System.out.println("\n===== MENU CLIENTES =====");
					System.out.println("1 - Cadastrar Cliente");
					System.out.println("2 - Listar Clientes");
					System.out.println("3 - Consultar Cliente");
					System.out.println("4 - Alterar Cliente");
					System.out.println("5 - Excluir Cliente");
					System.out.println("0 - Voltar");
					System.out.print("Opção: ");


					opCliente = sc.nextInt();
					sc.nextLine();



					switch(opCliente) {


					case 1:


						System.out.print("CPF: ");
						String cpf = sc.nextLine();


						System.out.print("Nome: ");
						String nome = sc.nextLine();


						System.out.print("Email: ");
						String email = sc.nextLine();


						System.out.print("Rua: ");
						String rua = sc.nextLine();


						System.out.print("Número: ");
						int numero = sc.nextInt();
						sc.nextLine();


						System.out.print("Bairro: ");
						String bairro = sc.nextLine();


						System.out.print("CEP: ");
						String cep = sc.nextLine();


						System.out.print("Cidade: ");
						String cidade = sc.nextLine();


						System.out.print("Estado: ");
						String estado = sc.nextLine();



						Cliente cliente = new Cliente(
								cpf,
								nome,
								email,
								rua,
								numero,
								bairro,
								cep,
								cidade,
								estado
						);



						clienteDao.salvar(cliente);



						break;



					case 2:


						List<Cliente> clientes = clienteDao.consultar();


						for(Cliente c : clientes) {

							System.out.println(
									c.getId()
									+ " - "
									+ c.getNome()
									+ " - "
									+ c.getCpf()
							);

						}


						break;



					case 3:


						System.out.print("ID: ");
						int idConsulta = sc.nextInt();



						Cliente c = clienteDao.consultar(idConsulta);



						if(c != null) {


							System.out.println("Nome: " + c.getNome());
							System.out.println("CPF: " + c.getCpf());
							System.out.println("Email: " + c.getEmail());


						}else {


							System.out.println("Cliente não encontrado.");

						}


						break;




					case 4:


						System.out.println(
						"Implementar alteração pelo método alterar()"
						);


						break;




					case 5:


						System.out.print("ID cliente: ");
						int idExcluir = sc.nextInt();



						clienteDao.deletar(idExcluir);



						break;



					case 0:

						break;



					default:

						System.out.println("Opção inválida!");

					}



				}while(opCliente != 0);



				break;



			// ================= PEDIDOS =================


			case 3:


				int opPedido;



				do {


					System.out.println("\n===== MENU PEDIDOS =====");
					System.out.println("1 - Novo Pedido");
					System.out.println("2 - Adicionar Produto");
					System.out.println("3 - Remover Produto");
					System.out.println("4 - Finalizar Pedido");
					System.out.println("5 - Listar Pedido");
					System.out.println("0 - Voltar");
					System.out.print("Opção: ");



					opPedido = sc.nextInt();
					sc.nextLine();



					switch(opPedido) {


					case 1:


						System.out.print("ID do cliente: ");
						int idCliente = sc.nextInt();



						Cliente clientePedido = clienteDao.consultar(idCliente);



						if(clientePedido != null) {


							pedidoAtual = new Pedido();


							pedidoAtual.setCliente(clientePedido);



							System.out.println(
							"Pedido criado com sucesso!"
							);



						}else {


							System.out.println(
							"Cliente não encontrado!"
							);

						}



						break;




					case 2:



						if(pedidoAtual != null) {



							System.out.print("Nome do produto: ");
							String nomeProduto = sc.nextLine();



							System.out.print("Preço: ");
							double precoProduto = sc.nextDouble();



							System.out.print("Estoque: ");
							int estoqueProduto = sc.nextInt();
							sc.nextLine();



							Produto p = new Produto(
									nomeProduto,
									precoProduto,
									estoqueProduto
							);



							pedidoAtual.adicionarProduto(p);



							System.out.println(
							"Produto adicionado!"
							);



						}else {


							System.out.println(
							"Crie um pedido primeiro!"
							);

						}



						break;




					case 3:



						if(pedidoAtual != null) {



							if(!pedidoAtual.getProdutos().isEmpty()) {



								Produto remover =
								pedidoAtual.getProdutos().get(0);



								pedidoAtual.removerProduto(remover);



								System.out.println(
								"Produto removido!"
								);



							}else {


								System.out.println(
								"Pedido sem produtos!"
								);

							}



						}else {


							System.out.println(
							"Nenhum pedido aberto!"
							);

						}



						break;




					case 4:

					    if(pedidoAtual != null){

					        pedidoAtual.finalizarPedido();

					        PedidoDao pedidoDao = new PedidoDao();

					        pedidoDao.salvar(pedidoAtual);

					        System.out.println("Pedido finalizado e salvo!");

					    }else{

					        System.out.println("Nenhum pedido aberto!");

					    }

					break;




					case 5:



						if(pedidoAtual != null) {



							System.out.println(
							"Cliente: "
							+ pedidoAtual.getCliente().getNome()
							);



							System.out.println(
							"Data: "
							+ pedidoAtual.getData()
							);



							System.out.println(
							"Status: "
							+ pedidoAtual.getStatus()
							);



							System.out.println("Produtos:");



							for(Produto p : pedidoAtual.getProdutos()) {


								System.out.println(
								"- "
								+ p.getDescricao()
								);

							}



						}else {


							System.out.println(
							"Nenhum pedido criado!"
							);

						}



						break;




					case 0:

						break;




					default:

						System.out.println(
						"Opção inválida!"
						);

					}



				}while(opPedido != 0);



				break;




			case 0:


				System.out.println(
				"Sistema encerrado."
				);



				break;




			default:


				System.out.println(
				"Opção inválida!"
				);

			}



		}while(opcaoPrincipal != 0);



		sc.close();


	}

}