package Proyecto.Java.Final.Servicios;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DAO.UsuarioDAO;
import Proyecto.Java.Final.DTO.CuentaDTO;
import Proyecto.Java.Final.Repositorio.CuentaRepositorio;
import Proyecto.Java.Final.Repositorio.UsuarioRepositorio;

@Service
public class CuentaServicioImpl implements ICuentaServicio{
	 
	@Autowired
	private CuentaRepositorio cuentaRepositorio; 
	
	@Autowired
	private ICuentaToDto toDto;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioServicioImpl.class);
	
	@Override
	public CuentaDAO crearCuenta(String usuarioDTO) {
	    try {
	        // Comprueba si ya existe un usuario por el Email
	    	
	        UsuarioDAO usuario = usuarioRepositorio.findByEmail(usuarioDTO);

	        // Comprueba si ya existe un usuario con el email que quiere registrar
	        if (usuario != null && usuario.isCuentaConfirmada()) {
	            CuentaDAO cuenta = new CuentaDAO();
	            
	            String NumeroCuenta = crearNumeroCuenta();

	            while(numeroCuentaExiste(NumeroCuenta)) {
	            	NumeroCuenta = crearNumeroCuenta();
	            }
	            
	            cuenta.setConNomina(false);
	            cuenta.setNumeroCuenta(NumeroCuenta);
	            cuenta.setFch_apertura(Calendar.getInstance());
	            cuenta.setSaldo(0.0);
	            cuenta.setUsuario(usuario);
	            cuentaRepositorio.save(cuenta);
	            //logger.info("Cuenta " + cuenta.getId_cuenta() + " Creada");
	            return cuenta;
	        } else if (usuario != null) { 
	            return null;
	        } else {
	            return null; // Devuelve null si no se encuentra el usuario
	        }
	    } catch (Exception e) {
	        //logger.error("Error en registrar: " + e.getMessage(), e);
	        return null; // Devuelve null en caso de excepción
	    }
	}

	@Override
	public List<CuentaDTO> verCuenta(String usuarioDTO) {
		  try {
		        // Comprueba si ya existe un usuario por el Email
		    	
		        UsuarioDAO usuario = usuarioRepositorio.findByEmail(usuarioDTO);

		        // Comprueba si ya existe un usuario con el email que quiere registrar
		        if (usuario != null && usuario.isCuentaConfirmada()) {
		        	
		        	// Devuelve una lista de las cuentas con relacion con el Usuario
		            
		        	return toDto.listaCuentaToDto(cuentaRepositorio.findByUsuario(usuario));
		            
		        } else if (usuario != null) { 
		            return null;
		        } else {
		            return null; // Devuelve null si no se encuentra el usuario
		        }
		    } catch (Exception e) {
		        //logger.error("Error en registrar: " + e.getMessage(), e);
		        return null; // Devuelve null en caso de excepción
		    }
	}
	
	@Override
    public CuentaDAO eliminarCuenta(long id) {
        try {
        	CuentaDAO cuenta = cuentaRepositorio.findById(id);

            if (cuenta != null) {
            	cuentaRepositorio.delete(cuenta);
                logger.info("Cuenta " + cuenta.getNumeroCuenta() + "Eliminado");
            } 
            return cuenta;
        } catch (Exception e) {
            logger.error("Error en eliminarCuenta: " + e.getMessage(), e);
            return null; 
        }
    }
	
	   // Método para buscar una Cuenta por su ID
    @Override
    public CuentaDAO buscarCuentaId(long id) {
        try {
            return cuentaRepositorio.findById(id);
        } catch (Exception e) {
            logger.error("Error en buscarCuentaId: " + e.getMessage(), e);
            return null; 
        }
    }
    
    // Método para obtener una lista de todss lss cuentas en formato DAO
    @Override
    public List<CuentaDAO> listadoCuentaDAO() {
        try {
            return cuentaRepositorio.findAll();
        } catch (Exception e) {
            logger.error("Error en listadoCuentaDAO: " + e.getMessage(), e);
            return null; 
        }
    }
    
 // Método para modificar los detalles de una cuenta
    @Override
    public boolean modificarCuenta(long id, CuentaDTO cuentaModificado) {
        try {
            // Verificar si la cuenta con el ID proporcionado existe en la base de datos
            CuentaDAO cuenta = cuentaRepositorio.findById(id);
            long idUser = cuenta.getUsuario().getId_usuario();
            UsuarioDAO usuario = usuarioRepositorio.findById(idUser);
            boolean yaTieneNomina = false;
            
            if (cuenta != null) {
            	
            	// Compruebo que ninguna cuenta tenga la nomina ya Asingnada
            	List<CuentaDAO> listaCuenta = cuentaRepositorio.findByUsuario(usuario);
            	for(CuentaDAO cuentaComparar : listaCuenta) {
            		if(cuentaComparar.getConNomina()) {
            			if(cuenta.getNumeroCuenta() != cuentaComparar.getNumeroCuenta()) {
            				yaTieneNomina = true;
            			}
            		}
            	}
            	
            	if(!yaTieneNomina) {
            		// Actualizar los campos de la cuenta existente con los nuevos valores
            		if(cuentaModificado.getConNomina() == null) {
            			cuenta.setConNomina(false);
            		}else {
            			cuenta.setConNomina(cuentaModificado.getConNomina());
            		}
            		
                	
                    // Guardar los cambios en la base de datos
                	cuentaRepositorio.save(cuenta);
                    logger.info("Cuenta " + cuenta.getNumeroCuenta() +" Fue modificado");
                    
                    return false;
            	}else {
            		return true;
            	}
            }
            
            return false;
            
        } catch (Exception e) {
            logger.error("Error en modificarCuenta: " + e.getMessage(), e);
            return false;
        }
    }
    
    //Comprueba si el Numero de Cuenta Existe en la BD
    @Override
    public boolean numeroCuentaExiste(String num) {
    	List<CuentaDAO> listaCuenta = cuentaRepositorio.findAll();
    	boolean re = false;
    	
    	for(CuentaDAO cuenta : listaCuenta) {
    		if(cuenta.getNumeroCuenta().equals(num)) {
    			re = true;
    		}
    	}
    	
    	return re;
    }

    // Crea el Numero de Cuenta
    @Override
    public String crearNumeroCuenta(){
    	String nC = "ES21 ";
    	int numeroAleatorio;
    	Random rand = new Random();
    	 
    	// Saca 4 digitos 2 veces
    	for(int e = 0; e<2; e++) {
	    	for(int i = 0; i<4; i++) {
	    		 numeroAleatorio = rand.nextInt(10);
	    		nC = nC +  numeroAleatorio;
	    	}
	    	nC = nC + " ";
    	}
    	
    	// Saca 2 digitos
    	for(int i = 0; i<2; i++) {
	   		numeroAleatorio = rand.nextInt(10);
	   		nC = nC +  numeroAleatorio;
    	}
    	
    	nC = nC + " ";
    	
    	// Saca 10 digitos
    	for(int i = 0; i<10; i++) {
	   		numeroAleatorio = rand.nextInt(10);
	   		nC = nC +  numeroAleatorio;
    	}
    	return nC;
    }
    
    @Override
    public boolean comprobacionDineroCuenta(String numeroCuenta, double pago) {
    	try {
    		CuentaDAO cuenta = new CuentaDAO();
    		
    		cuenta = cuentaRepositorio.findByNumeroCuenta(numeroCuenta);
    		
    		if(cuenta.getSaldo() >= pago) {
    			return true;
    		}
    		
    		return false;
    		
    	}catch(Exception e ) {
    		logger.error("Error en la comprobación [comprobacionDineroCuenta]: " + e.getMessage(), e);
    		return false;
    	}
    }

	@Override
	public boolean sacarDineroCuenta(String numeroCuenta, double dinero) {
		try {
			
			CuentaDAO cuenta = new CuentaDAO();
    		
    		cuenta = cuentaRepositorio.findByNumeroCuenta(numeroCuenta);
    		
    		if(cuenta != null && dinero > 0.0) {
    			cuenta.setSaldo(cuenta.getSaldo()-dinero);
    			cuentaRepositorio.save(cuenta);
    			return true;
    		}
    		
    		return false;
    		
		}catch(Exception e) {
			logger.error("Error [sacarDineroCuenta]: " + e.getMessage(), e);
    		return false;
		}
	}

	@Override
	public boolean ponerDineroCuenta(String numeroCuenta, double dinero) {
try {
			
			CuentaDAO cuenta = new CuentaDAO();
    		
    		cuenta = cuentaRepositorio.findByNumeroCuenta(numeroCuenta);
    		
    		if(cuenta != null && dinero > 0) {
    			cuenta.setSaldo(cuenta.getSaldo()+dinero);
    			cuentaRepositorio.save(cuenta);
    			return true;
    		}
    		
    		return false;
    		
		}catch(Exception e) {
			logger.error("Error [sacarDineroCuenta]: " + e.getMessage(), e);
    		return false;
		}
	} 
    
	@Override
	public CuentaDAO guardarCuenta(CuentaDAO cuenta) {
		try {
			cuentaRepositorio.save(cuenta);
			return cuenta;
		}catch(Exception e) {
			logger.error("Error [guardarCuenta]: " + e.getMessage(), e);
    		return null;
		}
	}
}
