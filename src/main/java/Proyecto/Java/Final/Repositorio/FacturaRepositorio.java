package Proyecto.Java.Final.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DAO.FacturaDAO;

public interface FacturaRepositorio extends JpaRepository<FacturaDAO, Long>{
	
	// Devuelve todas las facturas de una cuenta
	public List<FacturaDAO> findByCuenta(CuentaDAO cuenta);
}
