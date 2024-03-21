package Proyecto.Java.Final.Servicios;

import java.util.List;

import Proyecto.Java.Final.DAO.FacturaDAO;
import Proyecto.Java.Final.DTO.FacturaDTO;

public interface IFacturaToDao {
	//Metodo que convierte campo a campo un objeto FacturaDTO a DAO
	public FacturaDAO facturaToDao(FacturaDTO facturaDTO);
	
	//Metodo que convierte toda una lista de objetos FacturaDTO a lista de DAOs
	public List<FacturaDAO> listaFacturaToDao(List<FacturaDTO> listaFacturaDTO);
}
