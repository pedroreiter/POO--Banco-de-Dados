package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import interfaces.IClienteCRUD;
import modelos.Cliente;
import utils.ConectaDB;

public class ClienteDao implements IClienteCRUD {

	@Override
	public Cliente salvar(Cliente cliente) {

		String sql = "INSERT INTO tb_clientes(cpf,nome,email,rua,numero,bairro,cep,cidade,estado) VALUES(?,?,?,?,?,?,?,?,?)";

		try {

			Connection con = ConectaDB.conectar();

			PreparedStatement stm = con.prepareStatement(sql);

			stm.setString(1, cliente.getCpf());
			stm.setString(2, cliente.getNome());
			stm.setString(3, cliente.getEmail());
			stm.setString(4, cliente.getRua());
			stm.setInt(5, cliente.getNumero());
			stm.setString(6, cliente.getBairro());
			stm.setString(7, cliente.getCep());
			stm.setString(8, cliente.getCidade());
			stm.setString(9, cliente.getEstado());

			stm.execute();

			stm.close();
			con.close();

			System.out.println("Cliente cadastrado!");

			return cliente;

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		}

	}

	@Override
	public void alterar(Cliente cliente) {

		String sql = "UPDATE tb_clientes SET cpf=?, nome=?, email=?, rua=?, numero=?, bairro=?, cep=?, cidade=?, estado=? WHERE id=?";

		try {

			Connection con = ConectaDB.conectar();

			PreparedStatement stm = con.prepareStatement(sql);

			stm.setString(1, cliente.getCpf());
			stm.setString(2, cliente.getNome());
			stm.setString(3, cliente.getEmail());
			stm.setString(4, cliente.getRua());
			stm.setInt(5, cliente.getNumero());
			stm.setString(6, cliente.getBairro());
			stm.setString(7, cliente.getCep());
			stm.setString(8, cliente.getCidade());
			stm.setString(9, cliente.getEstado());
			stm.setInt(10, cliente.getId());

			stm.execute();

			stm.close();
			con.close();

			System.out.println("Cliente alterado!");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Override
	public void deletar(int id) {

		String sql = "DELETE FROM tb_clientes WHERE id=?";

		try {

			Connection con = ConectaDB.conectar();

			PreparedStatement stm = con.prepareStatement(sql);

			stm.setInt(1, id);

			stm.execute();

			stm.close();
			con.close();

			System.out.println("Cliente removido!");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Override
	public Cliente consultar(int id) {

		String sql = "SELECT * FROM tb_clientes WHERE id=?";

		try {

			Connection con = ConectaDB.conectar();

			PreparedStatement stm = con.prepareStatement(sql);

			stm.setInt(1, id);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {

				Cliente cliente = new Cliente();

				cliente.setId(rs.getInt("id"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEmail(rs.getString("email"));
				cliente.setRua(rs.getString("rua"));
				cliente.setNumero(rs.getInt("numero"));
				cliente.setBairro(rs.getString("bairro"));
				cliente.setCep(rs.getString("cep"));
				cliente.setCidade(rs.getString("cidade"));
				cliente.setEstado(rs.getString("estado"));

				rs.close();
				stm.close();
				con.close();

				return cliente;

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	@Override
	public List<Cliente> consultar() {

		List<Cliente> lista = new ArrayList<>();

		String sql = "SELECT * FROM tb_clientes";

		try {

			Connection con = ConectaDB.conectar();

			PreparedStatement stm = con.prepareStatement(sql);

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {

				Cliente cliente = new Cliente();

				cliente.setId(rs.getInt("id"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEmail(rs.getString("email"));
				cliente.setRua(rs.getString("rua"));
				cliente.setNumero(rs.getInt("numero"));
				cliente.setBairro(rs.getString("bairro"));
				cliente.setCep(rs.getString("cep"));
				cliente.setCidade(rs.getString("cidade"));
				cliente.setEstado(rs.getString("estado"));

				lista.add(cliente);

			}

			rs.close();
			stm.close();
			con.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return lista;

	}

}