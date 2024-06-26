package Proyecto.Java.Final.Controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Proyecto.Java.Final.DAO.UsuarioDAO;
import Proyecto.Java.Final.DTO.UsuarioDTO;
import Proyecto.Java.Final.Servicios.IUsuarioServicio;
import Proyecto.Java.Final.Servicios.IUsuarioToDto;
import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AdministradorControlador {
    
    @Autowired
    private IUsuarioServicio usuarioServicio;
    
    @Autowired
    private IUsuarioToDto usuarioToDto;
    
    private static final Logger logger = LoggerFactory.getLogger(AdministradorControlador.class);
    
    // Método para mostrar la lista de usuarios
    @GetMapping("/privada/administracion")
    public String listadoUsuarios(Model model, HttpServletRequest request, Authentication authentication) {
        try {
            // Verifica si el usuario tiene el rol de administrador
            if(request.isUserInRole("ROLE_ADMIN")) {
                // Recupera la lista de usuarios
                List<UsuarioDTO> usuarios = usuarioServicio.listadoUsuario();
                
                // Agrega la lista de usuarios al modelo para mostrarla en la vista
                model.addAttribute("usuarios", usuarios);
                model.addAttribute("foto", usuarioServicio.verFoto(authentication.getName()));
                return "administracion";    
            } 
            
            // Si el usuario no es administrador, agrega un mensaje de error al modelo y redirige a la página de inicio
            model.addAttribute("noAdmin", "No eres admin");
            model.addAttribute("name", authentication.getName());
            model.addAttribute("foto", usuarioServicio.verFoto(authentication.getName()));
            
            return "home";
        } catch (Exception e) {
            // Manejo de errores
            logger.error("Error en listadoUsuarios: " + e.getMessage(), e);
            model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
            return "home"; 
        }
    }
    
    // Método para eliminar un usuario
    @PostMapping("/privada/eliminar/{id}")
    public String eliminarUsuario(@PathVariable long id, Model model, HttpServletRequest request) {
        
        try {
            // Busca el usuario por ID
            UsuarioDAO usuario = usuarioServicio.buscarUsuarioId(id);
            // Obtiene la lista de usuarios
            List<UsuarioDAO> usuarios = usuarioServicio.listadoUsuarioDAO();
            
            // Verifica si el usuario actual es administrador y si el usuario a eliminar no es un administrador
            if(request.isUserInRole("ROLE_ADMIN") && usuario.getTipoUsuario().equals("ROLE_ADMIN")) {
                // Si el usuario a eliminar es un administrador, vuelve a cargar la lista de usuarios y muestra la página de administración
                model.addAttribute("usuarios", usuarios);
                model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
                return "administracion";
            }
            
            // Elimina el usuario y redirige a la página de administración
            usuarioServicio.eliminarUsuario(id);
            return "redirect:/privada/administracion";
        } catch (Exception e) {
            // Manejo de errores
        	model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
            logger.error("Error en eliminarUsuario: " + e.getMessage(), e);
            return "redirect:/privada/administracion"; 
        }
        
    }
    
     // Método para mostrar el formulario de modificación de usuario
    @PostMapping("/privada/modificar/{id}")
    public String mostrarFormularioModificar(@PathVariable long id, Model model,Authentication authentication) {
         try {
            // Busca el usuario por ID y lo convierte en un DTO para mostrar en el formulario
            UsuarioDAO usuarioDto = usuarioServicio.buscarUsuarioId(id);
            UsuarioDTO usuarioDao = usuarioToDto.usuarioToDto(usuarioDto);
            
            // Agrega el usuario al modelo
            model.addAttribute("usuario", usuarioDao);
            model.addAttribute("foto", usuarioServicio.verFoto(authentication.getName()));
            return "editar";
        } catch (Exception e) {
            // Manejo de errores
            logger.error("Error en mostrarFormularioModificar: " + e.getMessage(), e);
            return "editar"; 
        }
    }
    
    // Método para procesar la solicitud de modificación de usuario
    @PostMapping("/privada/modificar/usuario/{id}")
    public String modificarUsuario(@PathVariable long id, @ModelAttribute UsuarioDTO usuarioModificado) {
         try {
             // Actualiza los datos del usuario
             usuarioServicio.modificarUsuario(id, usuarioModificado);
             return "redirect:/privada/administracion";
         } catch (Exception e) {
             // Manejo de errores
             logger.error("Error en modificarUsuario: " + e.getMessage(), e);
             return "redirect:/privada/administracion"; 
         }
    }
    
    // Busca a un usuario introducido por el email
    @GetMapping("/privada/buscarUsuario")
    public String buscarUsuarioPorEmail(@RequestParam("email") String email, Model model, HttpServletRequest request, Authentication authentication) {
        try {
            // Verifica si el usuario tiene el rol de administrador
            if(request.isUserInRole("ROLE_ADMIN")) {
                // Busca al usuario por email
                email = email + "%"; // Usa el símbolo de porcentaje para indicar que el correo debe empezar con el texto proporcionado
                List<UsuarioDTO> usuarios = usuarioServicio.buscarUsuariosPorEmail(email);//////////////

                // Agrega los usuarios encontrados al modelo para mostrarlos en la vista
                model.addAttribute("usuarios", usuarios);
                
                model.addAttribute("foto", usuarioServicio.verFoto(authentication.getName()));
                return "administracion"; // Nombre de la vista donde mostrarás la información de los usuarios encontrados
            } 
            
            // Si el usuario no es administrador, agrega un mensaje de error al modelo y redirige a la página de inicio
            model.addAttribute("noAdmin", "No eres admin");
            model.addAttribute("name", authentication.getName());
            model.addAttribute("foto", usuarioServicio.verFoto(authentication.getName()));
            
            return "home";
        } catch (Exception e) {
            // Manejo de errores
            logger.error("Error en buscarUsuarioPorEmail: " + e.getMessage(), e);
            model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
            return "home"; 
        }
    }
}
