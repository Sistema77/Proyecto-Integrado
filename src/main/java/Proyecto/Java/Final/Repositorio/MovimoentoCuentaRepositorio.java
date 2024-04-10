package Proyecto.Java.Final.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DAO.MovimientoCuentaDAO;

public interface MovimoentoCuentaRepositorio extends JpaRepository<MovimientoCuentaDAO, Long> {

	// Devuelve todas los Movimientos de una cuenta
	public List<MovimientoCuentaDAO> findByCuenta(CuentaDAO cuenta);
}
