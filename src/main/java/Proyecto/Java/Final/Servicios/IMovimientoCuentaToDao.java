package Proyecto.Java.Final.Servicios;

import java.util.List;

import Proyecto.Java.Final.DAO.MovimientoCuentaDAO;
import Proyecto.Java.Final.DTO.MovimientoCuentaDTO;

public interface IMovimientoCuentaToDao {
	//Metodo que convierte campo a campo un objeto DTO a DAO
	public MovimientoCuentaDAO movimientoCuentaToDao(MovimientoCuentaDTO movimientoCuentaDto);

	//Metodo que convierte toda una lista de objetos UsuarioDTO a lista de DAOs
	public List<MovimientoCuentaDAO> listMovimientoCuentaToDao(List<MovimientoCuentaDTO>listaMovimientoCuentaDTO);
}
