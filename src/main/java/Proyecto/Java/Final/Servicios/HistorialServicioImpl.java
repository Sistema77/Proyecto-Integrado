package Proyecto.Java.Final.Servicios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DAO.FacturaDAO;
import Proyecto.Java.Final.DAO.MovimientoCuentaDAO;
import Proyecto.Java.Final.DAO.TransacionDAO;
import Proyecto.Java.Final.DTO.FacturaDTO;
import Proyecto.Java.Final.DTO.MovimientoCuentaDTO;
import Proyecto.Java.Final.DTO.TransacionDTO;
import Proyecto.Java.Final.Util.FechaHoraComparable;

@Service
public class HistorialServicioImpl implements IHistorialServicio {
    @Autowired
    private IMovimientoCuentaServicio movimientoCuentaServicio;

    @Autowired
    private ITransacionServicio transacionServicio;

    @Autowired
    private IFactruraServicio facturaServicio;

    private static final Logger logger = LoggerFactory.getLogger(HistorialServicioImpl.class);

    @Override
    public List<FechaHoraComparable> historial(CuentaDAO cuenta) {
        try {
            List<FechaHoraComparable> listaHistorial = new ArrayList<>();
            List<FacturaDAO> listaFactura = facturaServicio.listaFactura(cuenta);
            List<MovimientoCuentaDAO> listaMovimientoCuenta = movimientoCuentaServicio.listaMovimiento(cuenta);
            List<TransacionDAO> listaTrasancion = transacionServicio.listaTransaciones(cuenta);

            IFacturaToDto facturaToDto = new FacturaToDtoImpl();
            IMovimientoCuentaToDto movimientoToDto = new MovimientoCuentaToDtoImple();
            ITransacionToDto transacionToDto = new TransacionToDtoImple();

            List<FacturaDTO> listaFacturaDto = facturaToDto.listaFacturaToDto(listaFactura);
            List<MovimientoCuentaDTO> listaMovimientoCuentaDto = movimientoToDto.listaMovimientoCuentaToDto(listaMovimientoCuenta);
            List<TransacionDTO> listaTrasancionDto = transacionToDto.listaTrasacionDao(listaTrasancion);

            listaHistorial.addAll(listaFacturaDto);
            listaHistorial.addAll(listaMovimientoCuentaDto);
            listaHistorial.addAll(listaTrasancionDto);

            /////////////////////////////////////
            System.out.println(listaMovimientoCuentaDto.toString());
            ////////////////////////////////////
            
            Collections.sort(listaHistorial);

            return listaHistorial;
        } catch (Exception e) {
            logger.error("Error en historial: " + e.getMessage(), e);
            return null;
        }
    }
}
