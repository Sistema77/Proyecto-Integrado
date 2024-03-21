package Proyecto.Java.Final.Servicios;

import java.util.ArrayList;
import java.util.List;

import Proyecto.Java.Final.DAO.FacturaDAO;
import Proyecto.Java.Final.DTO.FacturaDTO;

public class FacturaToDaoImpl implements IFacturaToDao{

	@Override
	public FacturaDAO facturaToDao(FacturaDTO facturaDTO) {
		try {
			FacturaDAO facturaDao = new FacturaDAO();
			
			facturaDao.setCantidadDinero(facturaDTO.getCantidadDinero());
			facturaDao.setDescripcion(facturaDTO.getDescripcion());
			facturaDao.setFecha_Hora(facturaDTO.getFecha_Hora());
			facturaDao.setId_factura(facturaDTO.getId_factura());
			facturaDao.setTipoFactura(facturaDTO.getTipoFactura());
			
			return facturaDao;
		}catch(Exception e) {
			 System.out.println(
	                    "\n[ERROR FacturaToDaoImpl - facturaToDao()] - Al convertir DTO a DAO (return null): "
	                            + e);
	            return null;
		}
	}

	@Override
	public List<FacturaDAO> listaFacturaToDao(List<FacturaDTO> listaFacturaDTO) {
		
		try {
			List<FacturaDAO> listaFacturaDao = new ArrayList<>();
			
			for(FacturaDTO facturaDTO : listaFacturaDTO) {
				listaFacturaDao.add(facturaToDao(facturaDTO));
			}
			
			return listaFacturaDao;
		}catch(Exception e) {
            System.out.println(
                    "\n[ERROR UsuarioToDaoImpl - toListUsuarioDao()] - Al convertir lista de usuarioDTO a lista de usuarioDAO (return null): "
                            + e);
            return null;
        }
	}

}
