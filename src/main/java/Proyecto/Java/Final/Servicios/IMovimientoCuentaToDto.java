package Proyecto.Java.Final.Servicios;

import java.util.List;

import Proyecto.Java.Final.DAO.MovimientoCuentaDAO;
import Proyecto.Java.Final.DTO.MovimientoCuentaDTO;

public interface IMovimientoCuentaToDto {
	//Método que convierte campo a campo un objeto entidad DAO a DTO
	public MovimientoCuentaDTO movimientoCuentaDto(MovimientoCuentaDAO u);
	
	// Metodo que convierte todos los objetos entidad DAO a una lista DTO 
	public List<MovimientoCuentaDTO> listaMovimientoCuentaToDto(List<MovimientoCuentaDAO> listaMovimientoCuenta);
}
