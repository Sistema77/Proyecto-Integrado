package Proyecto.Java.Final.Servicios;

import java.util.List;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DAO.MovimientoCuentaDAO;
import Proyecto.Java.Final.DTO.MovimientoCuentaDTO;

public interface IMovimientoCuentaServicio {
	// Método para registrar un nuevo usuario
    public MovimientoCuentaDTO registrar(MovimientoCuentaDTO movimientoCuentaDto);
    
    // Lista de MovimientoCuenta de una cuenta
    public List<MovimientoCuentaDAO> listaMovimiento(CuentaDAO cuenta);
}
