package Proyecto.Java.Final.Servicios;

import java.util.List;

import Proyecto.Java.Final.DAO.CreditoDAO;
import Proyecto.Java.Final.DTO.CreditoDTO;

public interface ICreditoToDto {
	//Metodo que convierte campo a campo un objeto DAO a DTO
	public CreditoDTO creditoToDto(CreditoDAO dao);
	
	//Metodo que convierte toda una lista de objetos DTO a lista de DAOs
	public List<CreditoDTO> listCreditoDto(List<CreditoDAO> listaDao);
}
