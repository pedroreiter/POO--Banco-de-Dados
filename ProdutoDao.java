package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.ICRUD;
import modelos.Produto;
import utils.ConectaDB;

public class ProdutoDao implements ICRUD {

	@Override
	public Produto salvar(Produto prod) {

		String sql = "INSERT INTO tb_produtos(descricao, preco, estoque) VALUES(?,?,?)";

		try {

			Connection con = ConectaDB.conectar();

			PreparedStatement stm = con.prepareStatement(sql);

			stm.setString(1, prod.getDescricao());
			stm.setDouble(2, prod.getPreco());
			stm.setInt(3, prod.getEstoque());

			stm.execute();

			stm.close();
			con.close();

			System.out.println("Produto cadastrado!");

			return prod;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void alterar(Produto prod) {

		String sql = "UPDATE tb_produtos SET descricao=?, preco=?, estoque=? WHERE id=?";

		try {

			Connection con = ConectaDB.conectar();

			PreparedStatement stm = con.prepareStatement(sql);

			stm.setString(1, prod.getDescricao());
			stm.setDouble(2, prod.getPreco());
			stm.setInt(3, prod.getEstoque());
			stm.setInt(4, prod.getId());

			stm.execute();

			stm.close();
			con.close();

			System.out.println("Produto alterado!");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Override
	public Produto consultar(int id) {

		String sql = "SELECT * FROM tb_produtos WHERE id=?";

		try {

			Connection con = ConectaDB.conectar();

			PreparedStatement stm = con.prepareStatement(sql);

			stm.setInt(1, id);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {

				Produto prod = new Produto();

				prod.setId(rs.getInt("id"));
				prod.setDescricao(rs.getString("descricao"));
				prod.setPreco(rs.getDouble("preco"));
				prod.setEstoque(rs.getInt("estoque"));

				rs.close();
				stm.close();
				con.close();

				return prod;

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	@Override
	public List<Produto> consultar() {

		List<Produto> lista = new ArrayList<>();

		String sql = "SELECT * FROM tb_produtos";

		try {

			Connection con = ConectaDB.conectar();

			PreparedStatement stm = con.prepareStatement(sql);

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {

				Produto prod = new Produto();

				prod.setId(rs.getInt("id"));
				prod.setDescricao(rs.getString("descricao"));
				prod.setPreco(rs.getDouble("preco"));
				prod.setEstoque(rs.getInt("estoque"));

				lista.add(prod);

			}

			rs.close();
			stm.close();
			con.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return lista;

	}

	@Override
	public void deletar(int id) {

		String sql = "DELETE FROM tb_produtos WHERE id=?";

		try {

			Connection con = ConectaDB.conectar();

			PreparedStatement stm = con.prepareStatement(sql);

			stm.setInt(1, id);

			stm.execute();

			stm.close();
			con.close();

			System.out.println("Produto removido!");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}