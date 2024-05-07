package Proyecto.Java.Final.Servicios;

import java.util.List;

import Proyecto.Java.Final.DAO.TransacionDAO;
import Proyecto.Java.Final.DTO.TransacionDTO;

public interface ITransacionToDao {
	//Metodo que convierte campo a campo un objeto UsuarioDTO a DAO
	public TransacionDAO trasacionToDao(TransacionDTO traDto);
	
	public List<TransacionDAO> listaTrasacionDao(List<TransacionDTO> lista);
}
