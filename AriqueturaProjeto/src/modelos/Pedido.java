package modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

	private int id;
	private Cliente cliente;
	private LocalDate data;
	private String status;
	private List<Produto> produtos;

	public Pedido() {
		produtos = new ArrayList<>();
		data = LocalDate.now();
		status = "Aberto";
	}

	public Pedido(int id, Cliente cliente) {
		this.id = id;
		this.cliente = cliente;
		this.data = LocalDate.now();
		this.status = "Aberto";
		this.produtos = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public void adicionarProduto(Produto produto) {
		produtos.add(produto);
	}

	public void removerProduto(Produto produto) {
		produtos.remove(produto);
	}

	public void finalizarPedido() {
		status = "Finalizado";
	}

}