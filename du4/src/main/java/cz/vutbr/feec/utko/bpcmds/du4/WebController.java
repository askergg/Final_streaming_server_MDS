package cz.vutbr.feec.utko.bpcmds.du4;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @GetMapping(value = {"", "/", "/index"})
    public String Home(){
        return "index";
    }

    @RequestMapping(value="/player", method = RequestMethod.POST)
    public String player(@RequestParam String video, Model model){
        model.addAttribute("vid", video);
        return "player";
    }
}
