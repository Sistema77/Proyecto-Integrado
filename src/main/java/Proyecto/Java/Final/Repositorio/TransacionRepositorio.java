package Proyecto.Java.Final.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DAO.TransacionDAO;

public interface TransacionRepositorio extends JpaRepository<TransacionDAO, Long> {
	// Devuelve todas los Movimientos de una cuenta
		public List<TransacionDAO> findByCuenta(CuentaDAO cuenta);
}
