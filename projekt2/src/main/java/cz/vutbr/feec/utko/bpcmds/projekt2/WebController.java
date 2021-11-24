package cz.vutbr.feec.utko.bpcmds.projekt2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class WebController {

    @Autowired
    private ProjectResourceComponent handler;


    private final static File MP4_FILE = new File("C:\\Users\\wwwhr\\Pictures\\bbb_1080p.mp4");


    private final static String HLS_PATH = "C:\\Users\\wwwhr\\Pictures\\HLS\\";
    private final static String DASH_DIRECTORY = "C:\\Users\\wwwhr\\Pictures\\MPEG-DASH\\";




    @RequestMapping(value = {"/dash/{file}"}, method = RequestMethod.GET)
    public void streaming(
            @PathVariable("file") String file,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        File STREAM_FILE = null;

        STREAM_FILE = new File(DASH_DIRECTORY + file);

        request.setAttribute(ProjectResourceComponent.ATTR_FILE, STREAM_FILE);
        handler.handleRequest(request, response);
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/addvideo")
    public String addVideo() {
        return "addvideo";
    }

    @RequestMapping(value="/videolibrary", method = {RequestMethod.POST, RequestMethod.GET})
    public String videoLibrary(@RequestParam String URL, String name){
        return "videolibrary";
    }
}


}
