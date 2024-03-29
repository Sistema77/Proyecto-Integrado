package Proyecto.Java.Final.Servicios;

import java.util.List;

import Proyecto.Java.Final.DAO.UsuarioDAO;
import Proyecto.Java.Final.DTO.UsuarioDTO;


/**
 * Interface donde se declaran los metodos que son necesarios para el paso de una entidad
 * usuario (DAO) a usuarioDTO
 */
public interface IUsuarioToDto {
	
	/**
	 * Método que convierte campo a campo un objeto entidad Usuario a usuarioDTO
	 */
	public UsuarioDTO usuarioToDto(UsuarioDAO u);
	
	/**
	 * Metodo que convierte todos los objetos entidad usuario DAO a una lista UsuarioDTO 
	 */
	public List<UsuarioDTO> listaUsuarioToDto(List<UsuarioDAO> listaUsuario);
}