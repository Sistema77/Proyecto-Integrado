package Proyecto.Java.Final.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Proyecto.Java.Final.DAO.CreditoDAO;
import Proyecto.Java.Final.DAO.CuentaDAO;

public interface CreditoRepositorio extends JpaRepository<CreditoDAO, Long>{
	
	// Método que devuelve una lista de Crediot que esten relacionado con la cuenta
	public List<CreditoDAO> findByCuenta(CuentaDAO cuenta);
}
