package Proyecto.Java.Final.Servicios;

import java.util.List;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DAO.FacturaDAO;
import Proyecto.Java.Final.DTO.CuentaDTO;
import Proyecto.Java.Final.DTO.FacturaDTO;

public interface IFactruraServicio {
	public void registrar(CuentaDTO cuenta, FacturaDTO factura);
	
	public List<FacturaDAO> listaFactura(CuentaDAO cuenta);
}
