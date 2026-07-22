package interfaces;

import java.util.List;
import modelos.Cliente;

public interface IClienteCRUD {

	Cliente salvar(Cliente cliente);

	void alterar(Cliente cliente);

	void deletar(int id);

	Cliente consultar(int id);

	List<Cliente> consultar();

}
