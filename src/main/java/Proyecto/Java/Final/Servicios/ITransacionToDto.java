package Proyecto.Java.Final.Servicios;

import java.util.List;

import Proyecto.Java.Final.DAO.TransacionDAO;
import Proyecto.Java.Final.DTO.TransacionDTO;

public interface ITransacionToDto {
	//Metodo que convierte campo a campo un objeto UsuarioDTO a DAO
	public TransacionDTO trasacionToDao(TransacionDAO traDao);
		
	public List<TransacionDTO> listaTrasacionDao(List<TransacionDAO> lista);
}
