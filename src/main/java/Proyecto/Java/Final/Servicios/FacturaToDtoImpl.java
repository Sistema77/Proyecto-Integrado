package Proyecto.Java.Final.Servicios;

import java.util.ArrayList;
import java.util.List;

import Proyecto.Java.Final.DAO.FacturaDAO;
import Proyecto.Java.Final.DTO.FacturaDTO;

public class FacturaToDtoImpl implements IFacturaToDto{

	@Override
	public FacturaDTO facturaToDto(FacturaDAO u) {
		try {
			FacturaDTO dto = new FacturaDTO();
			
			dto.setCantidadDinero(u.getCantidadDinero());
			dto.setDescripcion(u.getDescripcion());
			dto.setFecha_Hora(u.getFecha_Hora());
			dto.setId_factura(u.getId_factura());
			dto.setTipoFactura(u.getTipoFactura());
			
			return dto;
			
		}catch (Exception e) {
			 System.out.println(
	                    "\n[ERROR FacturaToDtoImpl - facturaToDto()] - Error al convertir DAO a DTO (return null): "
	                            + e);
	            return null;
		}
	}

	@Override
	public List<FacturaDTO> listaFacturaToDto(List<FacturaDAO> listaFactura) {
		try {
			List<FacturaDTO> listaDto = new ArrayList<>();
			
			for(FacturaDAO u : listaFactura) {
				listaDto.add(this.facturaToDto(u));
			}
			
			return listaDto;
			
		}catch(Exception e) {
			System.out.println(
                    "\n[ERROR FacturaToDtoImpl - listaFacturaToDto()] - Error al convertir lista de DAO a lista de DTO (return null): "
                            + e);
			return null;
		}
	}

}
