package petsis.pet.controller;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.StandardCopyOption;

import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.hibernate.annotations.Check;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import petsis.pet.model.Pet;
import petsis.pet.model.Role;
import petsis.pet.model.Usuario;
import petsis.pet.repository.PetRepository;
import petsis.pet.repository.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PetRepository petRepository;
    


//===============================================================================================FOTO
//===============================================================================================FOTO
//===============================================================================================FOTO
    //cria a pasta do usuario com id
    // private static String UPLOADED_FOLDER = "C:/Users/anmor/Desktop/petsisFotos/user";

    // @Transient
	// public String getPhotosImagePath() {
	// 	Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
	// 	Usuario use = usuarioRepository.findUserByLogin(authentication.getName()); 	
		
	// 	if (use.getFoto() == null || use.getId() == 0) return null;
			
	// 	return UPLOADED_FOLDER + use.getId() + "/" + use.getFoto();
	// }
    // @GetMapping("/use/AlterarFoto")
	// public ModelAndView editarFoto() {
	// 	Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
	// 	Usuario use = usuarioRepository.findUserByLogin(authentication.getName());
	// 	Optional<Usuario> usuario2 = usuarioRepository.findById(use.getId());
	// 	ModelAndView modelAndView = new ModelAndView("redirect:/use/alterar");
	// 	modelAndView.addObject("usuarioobj", usuario2.get());
	// 	modelAndView.addObject("userfoto", usuarioRepository.findUserByid(use.getId()));
	// 	return modelAndView ;
	// }
    
    // @ResponseBody
	// @GetMapping("/use/img")
	// public byte[] img() {
	// 	Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
	// 	Usuario use = usuarioRepository.findUserByLogin(authentication.getName());
	// 	if (use.getFoto() == null) {
	// 		return null;
	// 	}else {
	// 		return use.getFotoImagePath().getBytes();
	// 	}	
	// }
	// //busca a foto do usuario
	// private static final String EXTERNAL_FILE_PATH = "C:/Users/anmor/Desktop/petsisFotos/user";

	// @ResponseBody
	// @GetMapping("/use/buscarfoto")
	// public void buscarFoto(HttpServletRequest request, HttpServletResponse response, @PathVariable("idUsuario") Long idUsuario) throws IOException {
	// 	Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
	// 	Usuario use = usuarioRepository.findUserByLogin(authentication.getName());
    //     Usuario usuario = usuarioRepository.findUserByid(idUsuario);
    //     File file = new File(EXTERNAL_FOLDER + idUsuario+"//"+usuario.getFoto());
	// 	// File file = new File(EXTERNAL_FOLDER + use.getId()+"/"+use.getFoto());
	// 	if (file.exists()) {
	// 		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
	// 		if(mimeType == null) {
	// 			mimeType = "application/octet-stream";
	// 		}
			
	// 		response.setContentType(mimeType);
	// 		response.setHeader("Content-Disposition", String.format("inline; filename=\""+file.getName()+"\""));
	// 		response.setContentLength((int) file.length());
	// 		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
	// 		FileCopyUtils.copy(inputStream, response.getOutputStream());
	// 	}
			
	// }	

    // public void visualizarFoto(HttpServletRequest requeste, HttpServletResponse response, @PathVariable("idUsuario") Long idUsuario) throws IOException {
	// 	Usuario usuario = usuarioRepository.findUserByid(idUsuario);
	// 	File file = new File(EXTERNAL_FOLDER + idUsuario+"//"+usuario.getFoto());
	// 	if (file.exists()) {
	// 		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
	// 		if(mimeType == null) {
	// 			mimeType = "application/octet-stream";
	// 		}
			
	// 		response.setContentType(mimeType);
	// 		response.setHeader("Content-Disposition", String.format("inline; filename=\""+file.getName()+"\""));
	// 		response.setContentLength((int) file.length());
	// 		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
	// 		FileCopyUtils.copy(inputStream, response.getOutputStream());
	// 	}
		
	// }

	// @ResponseBody
	// @GetMapping("/use/buscarfotoUserLista/{idUsuario}")
	// public byte[] buscarFotoLista(HttpServletRequest request, HttpServletResponse response,@PathVariable("idUsuario") Long idUsuario) throws IOException {
	// 	Usuario use = usuarioRepository.findUserByid(idUsuario);
		
	// 	File file = new File(EXTERNAL_FOLDER + use.getId()+"/"+use.getFoto());
	// 	if(file.exists()) {
	// 		return Files.readAllBytes(file.toPath());
	// 	}else {
	// 		return null;
	// 	}
		
	// }

    // @PostMapping("/use/SalvarFoto")
	// public ModelAndView salvar(Usuario usuario, RedirectAttributes ra,@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
	// 	Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
	// 	Usuario use = usuarioRepository.findUserByLogin(authentication.getName());
	// 	Usuario user2 = usuarioRepository.findUserByid(usuario.getId());
	// 	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	// 		user2.setFoto(fileName);
	// 	    usuarioRepository.save(user2);
			
	// 		String uploadDir = UPLOADED_FOLDER + user2.getId();
	// 		Path uploadPath = Paths.get(uploadDir);
			
    //         //criacao da pasta do usuario com id
	// 		if(!Files.exists(uploadPath)) {
	// 			Files.createDirectories(uploadPath);
	// 			 File fileaux = null;
	// 			 fileaux = new File(uploadDir);
	// 			 fileaux.mkdir();
	// 		}
			
    //         //tratacao da foto 
	// 		try(InputStream inputStream = multipartFile.getInputStream()){
                
	// 			Path filePath = uploadPath.resolve(fileName);
	// 			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	// 		} catch (IOException e) {
	// 			throw new IOException("Não foi possível salvar o arquivo enviado: " + fileName);
	// 		}
			
	// 		ra.addFlashAttribute("msg", "Usuário Atualizado com sucesso");
	// 		ModelAndView modelandView = new ModelAndView("redirect:/use/alterar");

	// 		return modelandView;
		
	// }

    

    // @GetMapping("/use/AlterarFoto")
	// public ModelAndView editarFoto() {
	// 	Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
	// 	Usuario use = usuarioRepository.findUserByLogin(authentication.getName());
	// 	Optional<Usuario> usuario2 = usuarioRepository.findById(use.getId());
	// 	ModelAndView modelAndView = new ModelAndView("redirect:/use/alterar");
	// 	modelAndView.addObject("usuarioobj", usuario2.get());
	// 	modelAndView.addObject("userfoto", usuarioRepository.findUserByid(use.getId()));
	// 	return modelAndView ;
	// }

    
    // @PostMapping("/use/SalvarFoto")
	// public ModelAndView salvar(Usuario usuario, RedirectAttributes ra,@RequestParam("file") MultipartFile file) throws IOException {
	// 	Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
    //     Usuario check = usuarioRepository.findUserByid(usuario.getId());
    //     String nomeFoto;
    //     nomeFoto = StringUtils.cleanPath(file.getOriginalFilename());
    //     check.setDataCadUse(check.getDataCadUse());
    //     List<Role> papel = new  ArrayList<Role>();
    //     Role role = new Role();
    //     role.setId(2);
    //     papel.add(role);
    //     check.setRoles(papel);
    //     check.setDeletado(false);
    //     //foto
        
    //     check.setFoto(nomeFoto);

    //     String uploadDiretorio = UPLOADED_FOLDER + check.getId();
    //     Path uploadPath = Paths.get(uploadDiretorio);
        
    //     if (!Files.exists(uploadPath)) {
    //         Files.createDirectories(uploadPath);
    //         File fileAux = null;
    //         fileAux = new File(uploadDiretorio);
    //         fileAux.mkdir();						   
    //     }
        
    //     file.transferTo(new File(uploadDiretorio, file.getOriginalFilename()));
    //     ra.addFlashAttribute("msg", "Foto Atualizado com sucesso");

    //     ModelAndView andView = new ModelAndView("redirect:/use/alterar");                           
    //     return andView;
		
	// }

    //===============================================================================================PAGINAS PADRAO
    //===============================================================================================PAGINAS PADRAO
    //===============================================================================================PAGINAS PADRAO

    @RequestMapping(method=RequestMethod.GET, value="/page/registro")
    public ModelAndView registroPage() {
        ModelAndView andView = new ModelAndView("/Home/page/registro");	
        andView.addObject("usuarioobj", new Usuario());
        return andView;
    }

    @RequestMapping(method=RequestMethod.GET, value="/page/recuperar")
    public ModelAndView recuperaSenhaPage() {
        ModelAndView andView = new ModelAndView("/Home/page/recuperar");		
        return andView;
    }
    //====END PAGINAS PADRAO
    @RequestMapping(method=RequestMethod.GET, value="/use/alterar")
    public ModelAndView alterarUserPage() {
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario use = usuarioRepository.findUserByLogin(authentication.getName());

        ModelAndView andView = new ModelAndView("/Home/usuario/alterar");
        andView.addObject("usuarioobj", use);	
        return andView;
    }  

    @RequestMapping(method = RequestMethod.POST , value = "/use/salvarRegistro")
    public ModelAndView salvarCadastro (Usuario usuario, RedirectAttributes ra, @RequestParam("file") MultipartFile file) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        Usuario use = usuarioRepository.findUserByLogin(authentication.getName());
        Usuario check = usuarioRepository.findUserByid(usuario.getId());

        List<Usuario> loginnome = usuarioRepository.findUserListOng(usuario.getNomeOng());
        List<Usuario> loginresgis = usuarioRepository.findUserListRegis(usuario.getRegisOng());
        List<Usuario> listlogin = usuarioRepository.findUserListLogin(usuario. getUsername());

        Role role = new Role();
        List<Role> papel = new  ArrayList<Role>();
		String nomeFoto;
        
        if (usuario.getId() == 0) {
            if(loginnome.isEmpty() ){
                if(loginresgis.isEmpty()){
                    if(listlogin.isEmpty()){
                        if (!file.isEmpty()) {
                            role.setId(2);
                            papel.add(role);
                            usuario.setRoles(papel);
                            usuario.setDataCadUse(LocalDate.now());
                            usuario.setDeletado(false);
                           
                            
                            //senha
                            if (usuario.getSenha().isEmpty()) {
                                usuario.setSenha(check.getSenha());
                            }else {
                                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                                String crypi = encoder.encode(usuario.getSenha());
                                usuario.setSenha(crypi);
                                }
                            //end senha
                            Usuario user2 = usuarioRepository.save(usuario);


                            //Foto
                            nomeFoto = StringUtils.cleanPath(file.getOriginalFilename());
                            usuario.setFoto(nomeFoto);
                            String uploadDiretorio = UPLOADED_FOLDER + user2.getId();
                            Path uploadPath = Paths.get(uploadDiretorio);
                            if (!Files.exists(uploadPath)) {
                                Files.createDirectories(uploadPath);
                                File fileAux = null;
                                fileAux = new File(uploadDiretorio);
                                fileAux.mkdir();						   
                            }
                            file.transferTo(new File(uploadDiretorio, file.getOriginalFilename())); 
                            //END FOTO
                            usuarioRepository.save(usuario);
                            ra.addFlashAttribute("msg", "Usuário Cadastrado com sucesso");
                            ModelAndView andView = new ModelAndView("redirect:/page/login");                           
                            return andView;
                        }else{
                            role.setId(2);
                            papel.add(role);
                            usuario.setRoles(papel);
                            usuario.setDataCadUse(LocalDate.now());
                            usuario.setDeletado(false);
                            //senha
                            if (usuario.getSenha().isEmpty()) {
                                usuario.setSenha(check.getSenha());
                            }else {
                                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                                String crypi = encoder.encode(usuario.getSenha());
                                usuario.setSenha(crypi);
                                }
                            //end senha                              
                            usuarioRepository.save(usuario);

                            ra.addFlashAttribute("msg", "Usuário Cadastrado com sucesso");
                            ModelAndView andView = new ModelAndView("redirect:/page/login");                           
                            return andView;
                        }                                         
                    }else{      
                        ra.addFlashAttribute("msgErro", "E-mail Já Cadastrado!");
                        ModelAndView andView = new ModelAndView("redirect:/page/registro");
                        return andView;	   
                    }
                }else{
                    ra.addFlashAttribute("msgErro", "CPF|CNPJ Já Cadastrado!");
                    ModelAndView andView = new ModelAndView("redirect:/page/registro");
                    return andView;	
                }   
            }else{
                ra.addFlashAttribute("msgErro", "Nome Já Cadastrado!");
                ModelAndView andView = new ModelAndView("redirect:/page/registro");
                return andView;				    
            } 
            
        }
        ra.addFlashAttribute("msgErro", "Usuario ja existe");
        ModelAndView andView = new ModelAndView("redirect:/page/registro");
        return andView;
    }

    @RequestMapping(method = RequestMethod.POST , value = "/use/editarUser")
    public ModelAndView editarUser (Usuario usuario, RedirectAttributes ra) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        Usuario check = usuarioRepository.findUserByid(usuario.getId());  
        
        List<Usuario> loginnome = usuarioRepository.findUserListOng(usuario.getNomeOng());
        List<Usuario> loginresgis = usuarioRepository.findUserListRegis(usuario.getRegisOng());
        List<Usuario> listlogin = usuarioRepository.findUserListLogin(usuario.getLogin());
        
        Role role = new Role();
        List<Role> papel = new  ArrayList<Role>();
            if(check.getNomeOng().equals(usuario.getNomeOng()) || loginnome.isEmpty()){
                if(check.getRegisOng().equals(usuario.getRegisOng()) || loginresgis.isEmpty()){
                    if(check.getLogin().equals(usuario.getLogin()) || listlogin.isEmpty()){    
                            check.setDeletado(false);
                            
                            //mantem os dados
                            role.setId(2);
                            papel.add(role);
                            check.setRoles(papel);
                            check.setDataCadUse(check.getDataCadUse());
                            check.setDeletado(false);
                            
                            //altera os dados
                            check.setNomeOng(usuario.getNomeOng());
                            check.setRespOng(usuario.getRespOng());
                            check.setRegisOng(usuario.getRegisOng());
                            check.setFoneOng(usuario.getFoneOng());
                            check.setLocalOng(usuario.getLocalOng());
                            check.setLogin(usuario.getLogin());
                            
                            
                            //senha
                            if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
                                usuario.setSenha(check.getSenha());
                            }else {
                                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                                String crypi = encoder.encode(check.getSenha());
                                usuario.setSenha(crypi);
                                }
                            //end senha                 
                            	
                            usuarioRepository.save(check);
                            
                            ModelAndView andView = new ModelAndView("redirect:/logout");                           
                            ra.addFlashAttribute("msg", "Usuário Atualizado com sucesso!");

                            return andView;
                    }else{
                        ra.addFlashAttribute("msgErro", "E-mail Ja Correponde a Outro usuario ");
                        ModelAndView andView = new ModelAndView("redirect:/use/alterar");
                        return andView;	
                    }
                }else{
                    ra.addFlashAttribute("msgErro", "CPF|CNPJ Ja Correponde a Outro usuario ");
                    ModelAndView andView = new ModelAndView("redirect:/use/alterar");
                    return andView;	

                }   
            }else{
                ra.addFlashAttribute("msgErro", "Nome Ja Correponde a Outro usuario ");
                ModelAndView andView = new ModelAndView("redirect:/use/alterar");
                return andView;				    
            } 
    }

    //Metodo para visualizar imagem e abrir em nova guia para tela cheia
	private static String EXTERNAL_FOLDER = "C:/petsisFotos/user";
	
	@RequestMapping("/use/fotoVisualizar/{idUsuario}")
	public void visualizarFoto(HttpServletRequest requeste, HttpServletResponse response, @PathVariable("idUsuario") Long idUsuario) throws IOException {
		Usuario usuario = usuarioRepository.findUserByid(idUsuario);
		File file = new File(EXTERNAL_FOLDER + idUsuario+"//"+usuario.getFoto());
		if (file.exists()) {
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if(mimeType == null) {
				mimeType = "application/octet-stream";
			}
			
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", String.format("inline; filename=\""+file.getName()+"\""));
			response.setContentLength((int) file.length());
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		}
		
	}
    private static String UPLOADED_FOLDER = "C:/petsisFotos/user";

    @RequestMapping(method = RequestMethod.POST , value = "/use/editarFoto")
    public ModelAndView editarFoto (Usuario usuario, RedirectAttributes ra, 
        @RequestParam("file") MultipartFile file) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        Usuario check = usuarioRepository.findUserByid(usuario.getId());          
        String nomeFoto;

        if (!file.isEmpty()) {
            nomeFoto = StringUtils.cleanPath(file.getOriginalFilename());
            check.setFoto(nomeFoto);

            String uploadDiretorio = UPLOADED_FOLDER + check.getId();
            Path uploadPath = Paths.get(uploadDiretorio);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                File fileAux = null;
                fileAux = new File(uploadDiretorio);
                fileAux.mkdir();
            }
            file.transferTo(new File(uploadDiretorio, file.getOriginalFilename()));
        }    
            
        usuarioRepository.save(check);
        
        ra.addFlashAttribute("msg", "Foto Atualizada com sucesso!");
        ModelAndView andView = new ModelAndView("redirect:/use/alterar");                           
        return andView;             
    }  
    
    //===============================================================================================ADM
    //===============================================================================================ADM
    //===============================================================================================ADM

    @RequestMapping(method={RequestMethod.GET}, value="/adm/listauser")
	public ModelAndView listaAdm(ModelAndView model) {
		ModelAndView andView = new ModelAndView("/Home/adm/listauser");
		andView.addObject("usuariolist", usuarioRepository.findUser());
		andView.addObject("usuarioobj", new Usuario());
		return andView;
	}

    @RequestMapping(method={RequestMethod.GET, RequestMethod.POST}, value = "/adm/salvar")
    public ModelAndView salvarAdm (Usuario usuario, RedirectAttributes ra ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        Usuario user = usuarioRepository.verificarUsuario(usuario.getLogin(), usuario.getRegisOng(), usuario.getNomeOng());        
        Role role = new Role();
        List<Role> papel = new  ArrayList<Role>();
        if (usuario.getId() == 0) {
            if(user==null ) {
                usuario.setDeletado(false);
                role.setId(2);
                papel.add(role);
                usuario.setRoles(papel);
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String crypi = encoder.encode(usuario.getSenha());
                usuario.setSenha(crypi);
                usuarioRepository.save(usuario);			
                ra.addFlashAttribute("msg", "Usuário Cadastrado com sucesso");
                
                ModelAndView andView = new ModelAndView("redirect:/adm/listauser");                           
                return andView;
                
            }else{
                ra.addFlashAttribute("msgErro", "Login já Cadastrado!!!");
                ModelAndView andView = new ModelAndView("redirect:/adm/listauser");   
                return andView;				    
            }
        }else{
        
        }
        role.setId(2);
        papel.add(role);
        usuario.setRoles(papel);
        usuario.setDeletado(false);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String crypi = encoder.encode(usuario.getSenha());
        usuario.setSenha(crypi);		
        usuarioRepository.save(usuario); 
        ra.addFlashAttribute("msg", "Usuário Atualizado com sucesso");
        System.out.println(role);
        ModelAndView andView = new ModelAndView("redirect:/adm/listauser");
        return andView;
    }
    

    @GetMapping(value="/adm/editaruser/{iduser}")
    public ModelAndView editarAdm(@PathVariable("iduser") Long iduser, RedirectAttributes ra){
        Optional<Usuario> user = usuarioRepository.findById(iduser);

        ra.addFlashAttribute("msg", "Usuario Editado com sucesso");
        ModelAndView andView = new ModelAndView("/Home/adm/listauser");
        andView.addObject("usuarioobj", user.get());
        
        return andView;
    }
    
    @GetMapping("/adm/removeruser/{iduser}")
	public ModelAndView removerAdm(@PathVariable("iduser")Long iduser, RedirectAttributes ra) {
		Usuario usuario = usuarioRepository.findUserByid(iduser);
        usuario.setDeletado(true);
        usuarioRepository.save(usuario);
        ra.addFlashAttribute("msg", "Usuario Removido com sucesso");
		ModelAndView andView = new ModelAndView("redirect:/adm/listauser");
		return andView;
	}
 
//===============================================================================================PET
//===============================================================================================PET
//===============================================================================================PET
    // @GetMapping("/pet/{iduse}")
    // public ModelAndView telefones(@PathVariable("iduse") Long iduse) {
        
    //     Optional<Usuario> use = usuarioRepository.findById(iduse);

    //     ModelAndView andView = new ModelAndView("cadastro/telefones");
    //     andView.addObject("useobj", use.get());
    //     andView.addObject("pets", petRepository.getPets(iduse));
    //     return andView;
        
    // }

    // @PostMapping("/addpetUse/{useid}")
    // public ModelAndView addFonePessoa(Pet pet, @PathVariable("useid") Long useid) {
        
    //     Usuario use = usuarioRepository.findById(useid).get();
    //     pet.setOngPet(use);
        
    //     usuarioRepository.save(pet);
        
    //     ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
    //     modelAndView.addObject("pessoaobj", use);
    //     modelAndView.addObject("telefones", petRepository.getPets(useid));
    //     return modelAndView;
    // }

}//END
