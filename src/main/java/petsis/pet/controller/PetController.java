package petsis.pet.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.beans.Transient;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.net.URLConnection;
import org.springframework.util.FileCopyUtils;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import petsis.pet.repository.PetRepository;
import petsis.pet.repository.UsuarioRepository;

@Controller
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // PAGINA CADASTRO
    @RequestMapping(method = RequestMethod.GET, value = "/pet/cadpet")
    public ModelAndView cadPetPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario use = usuarioRepository.findUserByNomeOng(authentication.getName());

        ModelAndView andView = new ModelAndView("/Home/pet/cadpet");
        andView.addObject("petobj", new Pet());
        return andView;
    }

    // PAGINA LISTAR
    // @RequestMapping(method= RequestMethod.GET, value="/pet/listapet")
    // public ModelAndView listapetPage(ModelAndView model) {
    // ModelAndView andView = new ModelAndView("/Home/pet/listapet");
    // andView.addObject("petlist", petRepository.findPet());
    // andView.addObject("petobj", new Pet());
    // return andView;
    // }

    @RequestMapping(method = RequestMethod.GET, value = "/pet/listapet")
    public ModelAndView listapetUserPage(ModelAndView model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario use = usuarioRepository.findUserByLogin(authentication.getName());
        // System.out.println(use.getId());
        ModelAndView andView = new ModelAndView("/Home/pet/listapet");
        andView.addObject("petlist", petRepository.findPetUser(use.getId()));
        andView.addObject("petobj", new Pet());
        return andView;
    }

    // PAGINA EDITAR PET
    @GetMapping(value = "/pet/editarpet/{idpet}")
    public ModelAndView editarPetPage(@PathVariable("idpet") Long idpet, RedirectAttributes ra) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario use = usuarioRepository.findUserByNomeOng(authentication.getName());
        Pet pet = petRepository.findPetByid(idpet);

        ModelAndView andView = new ModelAndView("/Home/pet/editarpet");
        andView.addObject("petobj", pet);

        return andView;
    }

    // FUNCAO SALVAR
    @RequestMapping(method = RequestMethod.POST, value = "/pet/cadPet")
    public ModelAndView cadPet(Pet pet, RedirectAttributes ra,
        @RequestParam("file") MultipartFile file) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario use = usuarioRepository.findUserByLogin(authentication.getName());
        List<Pet> pettag = petRepository.findPetListTag(pet.getTag());
        String nomeFoto;
        if (pettag.isEmpty()) {
            if (!file.isEmpty()) {
                pet.setDataCadPet(LocalDate.now());
                pet.setDeletado(false);
                pet.setOngPet(use);

                Pet pet2 = petRepository.save(pet);

                // Foto
                nomeFoto = StringUtils.cleanPath(file.getOriginalFilename());
                pet.setFotoPet(nomeFoto);
                String uploadDiretorio = UPLOADED_FOLDER + pet2.getId();
                Path uploadPath = Paths.get(uploadDiretorio);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                    File fileAux = null;
                    fileAux = new File(uploadDiretorio);
                    fileAux.mkdir();
                }
                file.transferTo(new File(uploadDiretorio, file.getOriginalFilename()));
                // END FOTO

                petRepository.save(pet);
                ra.addFlashAttribute("msg", "Pet Cadastrado com Sucesso!!");

                ModelAndView andView = new ModelAndView("redirect:/pet/listapet");
                return andView;
            } else {
                pet.setDataCadPet(LocalDate.now());
                pet.setDeletado(false);
                pet.setOngPet(use);
                petRepository.save(pet);
                ra.addFlashAttribute("msg", "Pet Cadastrado com Sucesso!!");

                ModelAndView andView = new ModelAndView("redirect:/pet/listapet");
                return andView;
            }

        } else {

            ra.addFlashAttribute("msgErro", "Pet Tag ja Cadastrado!!");

            ModelAndView andView = new ModelAndView("redirect:/pet/listapet");
            return andView;
        }

    }

    // FUNCAO EDITAR
    @RequestMapping(method = RequestMethod.POST, value = "/pet/editarPet")
    public ModelAndView editarPet(Pet pet, RedirectAttributes ra){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Pet pe = petRepository.findPetByid(pet.getId());
        // System.out.println(pet.getId());
        
        //mantem os dados
        pe.setDataCadPet(pe.getDataCadPet());
        pe.setDeletado(false);
        pe.setOngPet(pe.getOngPet());
        pe.setTag(pe.getTag());

        //altera os dados
        pe.setNomePet(pet.getNomePet());
        pe.setSexoPet(pet.getSexoPet());
        pe.setTipoPet(pet.getTipoPet());
        pe.setTxtPet(pet.getTxtPet());

        petRepository.save(pe);
        ra.addFlashAttribute("msg", "Pet Editado com Sucesso!");
        ModelAndView andView = new ModelAndView("redirect:/pet/listapet");
        return andView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/pet/editarFotoPet")
    public ModelAndView editarFotoPet(Pet pet, RedirectAttributes ra,
    @RequestParam("file") MultipartFile file) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Pet pe = petRepository.findPetByid(pet.getId());
        String nomeFoto;

        if (!file.isEmpty()) {
            nomeFoto = StringUtils.cleanPath(file.getOriginalFilename());
            pe.setFotoPet(nomeFoto);

            String uploadDiretorio = UPLOADED_FOLDER + pe.getId();
            Path uploadPath = Paths.get(uploadDiretorio);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                File fileAux = null;
                fileAux = new File(uploadDiretorio);
                fileAux.mkdir();
            }
            file.transferTo(new File(uploadDiretorio, file.getOriginalFilename()));
        }     
        // END FOTO

        petRepository.save(pe);

        ra.addFlashAttribute("msg", "Foto do Pet Atualizada com sucesso!");
        ModelAndView andView = new ModelAndView("redirect:/pet/listapet");
        return andView;
    }

    // FUNCAO REMOVER
    @GetMapping("/pet/removerpet/{idpet}")
    public ModelAndView removerPetUser(@PathVariable("idpet") Long idpet, RedirectAttributes ra) {
        Pet pet = petRepository.findPetByid(idpet);
        pet.setDeletado(true);
        petRepository.save(pet);
        ra.addFlashAttribute("msg", "Pet Removido com sucesso");
        ModelAndView andView = new ModelAndView("redirect:/pet/listapet");
        return andView;
    }

    // Metodo para visualizar imagem e abrir em nova guia para tela cheia
    private static String EXTERNAL_FOLDER = "C:/petsisFotos/user/Pets";

    @RequestMapping("/pet/fotoVer/{idPet}")
    public void visualizarFoto(HttpServletRequest requeste, HttpServletResponse response,
            @PathVariable("idPet") Long idPet) throws IOException {
        Pet pet = petRepository.findPetByid(idPet);
        File file = new File(EXTERNAL_FOLDER + idPet + "//" + pet.getFotoPet());
        if (file.exists()) {
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }

    }

    private static String UPLOADED_FOLDER = "C:/petsisFotos/user/Pets";

}// END
