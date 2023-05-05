package petsis.pet.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import petsis.pet.model.Pet;
import petsis.pet.model.Usuario;
import petsis.pet.model.Formu;
import petsis.pet.repository.PetRepository;
import petsis.pet.repository.UsuarioRepository;
import petsis.pet.repository.FormuRepository;

@Controller
public class FormControler {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FormuRepository formuRepository;

    @RequestMapping(method=RequestMethod.GET, value="/page/form")
	public ModelAndView form() {
		ModelAndView andView = new ModelAndView("/Home/page/form");
		return andView;
	}

    // FUNCAO SALVAR
    @RequestMapping(method = RequestMethod.POST, value = "/page/formulario")
    public ModelAndView cadFormu(Formu formu, RedirectAttributes ra) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Formu> cod = formuRepository.findFormuListCod(formu.getCod());
        if (cod.isEmpty()) {        
            formu.setData(null);
            formu.setDeletado(false);
            
            formuRepository.save(formu);
            ra.addFlashAttribute("msg", "Formulario Cadastrado com Sucesso!!");
            ModelAndView andView = new ModelAndView("redirect:/page/form");
            return andView;
            
        } else {
            ra.addFlashAttribute("msgErro", "Formulario ja Existe");
            ModelAndView andView = new ModelAndView("redirect:/page/form");
            return andView;
        }

    }

    // @RequestMapping(method = RequestMethod.GET, value = "/page/listform")
    // public ModelAndView listaFormPage(ModelAndView model) {
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //     Usuario use = usuarioRepository.findUserByLogin(authentication.getName());
        
    //     ModelAndView andView = new ModelAndView("/Home/page/listform");
    //     andView.addObject("formlist", petRepository.findPetUser(use.getId()));
    //     andView.addObject("formobj", new Formu());
    //     return andView;
    // }
}
