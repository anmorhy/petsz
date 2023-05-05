package petsis.pet.security;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import ch.qos.logback.core.filter.Filter;

@Configuration
//@EnableWebMvc
//@EnableWebSecurity
public class WebConfig implements WebMvcConfigurer {
   
    private static String UPLOADED_FOLDER = "C:/Users/anmor/Desktop/petsisFotos";
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	Path usersUploadDir = Paths.get(UPLOADED_FOLDER);
    	String UserUploadPath = usersUploadDir.toFile().getAbsolutePath();
    	
    	registry.addResourceHandler(UPLOADED_FOLDER).addResourceLocations("file:/" + UserUploadPath + "/");
        exposeDirectory(UPLOADED_FOLDER, registry);
    }
     
    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();
         
        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
         
        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/"+ uploadPath + "/");
    }
    
    
    

}
