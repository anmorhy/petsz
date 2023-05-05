package petsis.pet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import petsis.pet.model.Usuario;
@Controller
public class loginControler {
    @RequestMapping(method=RequestMethod.GET, value="/page/login")
	public ModelAndView login() {
		ModelAndView andView = new ModelAndView("/Home/page/login");
		return andView;
	}
	@RequestMapping(method=RequestMethod.GET, value="/page/erro")
    public ModelAndView loginOng(Usuario usuario, RedirectAttributes ra) { 
        // Usuario user = usuarioRepository.findOngLogin(usuario.getUsername(), usuario.getPassword());
        if (usuario.getId() == 0) {
                ra.addFlashAttribute("msgErro", "Login ou senha errado!!!");
                ModelAndView andView = new ModelAndView("redirect:/page/login");
                return andView;				    
        }else{
            ModelAndView andView = new ModelAndView("/Home/dashboard/dash");
                return andView;
        }
        
    }
}
