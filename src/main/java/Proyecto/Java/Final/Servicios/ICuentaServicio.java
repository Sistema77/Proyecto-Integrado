package Proyecto.Java.Final.Servicios;

import java.util.List;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DTO.CuentaDTO;
import Proyecto.Java.Final.DTO.UsuarioDTO;

public interface ICuentaServicio {
	public CuentaDAO crearCuenta(String usuario);
	
	public List<CuentaDTO> verCuenta(String usuario);
	
	public CuentaDAO eliminarCuenta(long id);
	
	public CuentaDAO buscarCuentaId(long id);
	
	public List<CuentaDAO> listadoCuentaDAO();
	
	public boolean modificarCuenta(long id, CuentaDTO cuentaModificado);
	
	public boolean numeroCuentaExiste(String num);
	
	public String crearNumeroCuenta();
	
	//Comprobar si la Cuenta tiene dinero suficiente para realizar el pago
	public boolean comprobacionDineroCuenta(String numeroCuenta, double pago);
	
	// Quitar el dinero de la cuenta
	public boolean sacarDineroCuenta(String numeroCuenta, double dinero);
	// Poner el dinero en la cuenta
	public boolean ponerDineroCuenta(String numeroCuenta, double dinero);
	
	//Guardar Cuenta 
	public CuentaDAO guardarCuenta(CuentaDAO cuenta);
}
