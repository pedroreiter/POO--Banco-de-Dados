package interfaces;

import java.util.List;
import modelos.Pedido;

public interface IPedidoDAO {

	public void salvar(Pedido pedido);

	public List<Pedido> consultar();

	public Pedido consultar(int id);

	public void deletar(int id);

}
