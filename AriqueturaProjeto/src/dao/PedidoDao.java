package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import utils.ConectaDB;
import interfaces.IPedidoDAO;
import modelos.Pedido;
import modelos.Produto;

public class PedidoDao implements IPedidoDAO {


	@Override
	public void salvar(Pedido pedido) {

		String sql = "INSERT INTO pedido(cliente_id, data, status) VALUES (?, ?, ?)";


		try {

			Connection con = ConectaDB.conectar();

			PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);


			stmt.setInt(1, pedido.getCliente().getId());

			stmt.setDate(2, java.sql.Date.valueOf(pedido.getData()));

			stmt.setString(3, pedido.getStatus());


			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();

			int idPedido = 0;

			if(rs.next()) {
				idPedido = rs.getInt(1);
			}
			
			for(Produto produto : pedido.getProdutos()) {


				String sqlProduto = 
				"INSERT INTO pedido_produto(pedido_id, produto_id, quantidade) VALUES (?, ?, ?)";


				PreparedStatement stmtProduto = con.prepareStatement(sqlProduto);


				stmtProduto.setInt(1, idPedido);

				stmtProduto.setInt(2, produto.getId());

				stmtProduto.setInt(3, 1);


				stmtProduto.execute();


				stmtProduto.close();

			}

			stmt.close();
			con.close();


			System.out.println("Pedido salvo com sucesso!");


		} catch(Exception e) {

			System.out.println(e.getMessage());

		}

	}



	@Override
	public List<Pedido> consultar() {


		List<Pedido> pedidos = new ArrayList<>();

		String sql = "SELECT * FROM pedido";


		try {


			Connection con = ConectaDB.conectar();

			PreparedStatement stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();



			while(rs.next()) {


				Pedido pedido = new Pedido();


				pedido.setId(rs.getInt("id"));

				pedido.setData(
					rs.getDate("data").toLocalDate()
				);


				pedido.setStatus(
					rs.getString("status")
				);



				pedidos.add(pedido);

			}


			stmt.close();
			con.close();


		} catch(Exception e) {

			System.out.println(e.getMessage());

		}


		return pedidos;

	}




	@Override
	public Pedido consultar(int id) {


		String sql = "SELECT * FROM pedido WHERE id=?";


		Pedido pedido = null;


		try {


			Connection con = ConectaDB.conectar();


			PreparedStatement stmt = con.prepareStatement(sql);


			stmt.setInt(1, id);


			ResultSet rs = stmt.executeQuery();



			if(rs.next()) {


				pedido = new Pedido();


				pedido.setId(
					rs.getInt("id")
				);


				pedido.setData(
					rs.getDate("data").toLocalDate()
				);


				pedido.setStatus(
					rs.getString("status")
				);

			}



			stmt.close();
			con.close();



		}catch(Exception e){

			System.out.println(e.getMessage());

		}


		return pedido;

	}




	@Override
	public void deletar(int id) {


		String sql = "DELETE FROM pedido WHERE id=?";


		try {


			Connection con = ConectaDB.conectar();


			PreparedStatement stmt = con.prepareStatement(sql);


			stmt.setInt(1, id);


			stmt.execute();


			stmt.close();
			con.close();



			System.out.println("Pedido removido!");



		}catch(Exception e){

			System.out.println(e.getMessage());

		}


	}


}
