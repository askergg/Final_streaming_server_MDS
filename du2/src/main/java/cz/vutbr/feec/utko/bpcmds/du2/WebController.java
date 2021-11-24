package cz.vutbr.feec.utko.bpcmds.du2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @GetMapping("/bob")
    public String bob(Model model){
        model.addAttribute("name", "Bob");
        return "index";
    }

    @GetMapping("/alice")
    public String alice(Model model){
        model.addAttribute("name", "Alice");
        return "index";
    }
}
