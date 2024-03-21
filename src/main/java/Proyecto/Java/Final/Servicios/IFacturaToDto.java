package Proyecto.Java.Final.Servicios;

import java.util.List;

import Proyecto.Java.Final.DAO.FacturaDAO;
import Proyecto.Java.Final.DTO.FacturaDTO;

public interface IFacturaToDto {
	
	//Método que convierte campo a campo un objeto entidad Factura a FacturaDTO
	public FacturaDTO facturaToDto(FacturaDAO u);
	
	// Metodo que convierte todos los objetos entidad Factura DAO a una lista FacturaDTO 
	public List<FacturaDTO> listaFacturaToDto(List<FacturaDAO> listaFactura);
	
}
