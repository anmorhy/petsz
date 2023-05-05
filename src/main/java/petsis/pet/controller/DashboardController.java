package petsis.pet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {

    @RequestMapping(method=RequestMethod.GET, value="/use/dash")
    public ModelAndView dash() {
        ModelAndView andView = new ModelAndView("/Home/dashboard/dash");		
        return andView ;
    }
    
}
