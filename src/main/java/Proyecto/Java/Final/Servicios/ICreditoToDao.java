package Proyecto.Java.Final.Servicios;

import java.util.List;

import Proyecto.Java.Final.DAO.CreditoDAO;
import Proyecto.Java.Final.DTO.CreditoDTO;

public interface ICreditoToDao {
	//Metodo que convierte campo a campo un objeto DTO a DAO
	public CreditoDAO creditoToDao(CreditoDTO dto);
	
	//Metodo que convierte toda una lista de objetos DTO a lista de DAOs
	public List<CreditoDAO> listCreditoDao(List<CreditoDTO> listaDto);
}
