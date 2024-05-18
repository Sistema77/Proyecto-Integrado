package Proyecto.Java.Final.Controladores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import Proyecto.Java.Final.Servicios.ICreditoServicio;
import Proyecto.Java.Final.Servicios.ICuentaServicio;
import Proyecto.Java.Final.Servicios.IUsuarioServicio;

@Controller
public class SimuladorControlador {
	@Autowired
    private IUsuarioServicio usuarioServicio;
	
	@Autowired
	private ICuentaServicio cuentaServicio;
	
	@Autowired
	private ICreditoServicio creditoServicio;
	
	private static final Logger logger = LoggerFactory.getLogger(SimuladorControlador.class);
}
