package cz.vutbr.feec.utko.bpcmds.du3;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.thymeleaf.util.StringUtils;

@Controller
public class WebController {

    // Anotace Autowired vytvoří závislost na objektu handler, který je využit v metodě byterange
    @Autowired
    private MyResourceHttpRequestHandler handler;

    //TODO Přepsat cesty pro použití v učebně
    // Deklarace objektu typu File s názvem MP4_FILE s cestou k souboru videa

    private final static File MP4_FILE = new File("C:\\Users\\bened\\Documents\\Source\\VUT\\BPC-MDS\\Video\\bbb_1080p.mp4"); // puvodni cesta
    //private final static File MP4_FILE = new File("C:\\Users\\wwwhr\\Pictures\\bbb_1080p.mp4"); #Moje cesta k souboru

    // Deklarace proměnné String s cestou k souborům streamu
    //private final static String HLS_PATH = "C:\\Users\\bened\\Documents\\Source\\VUT\\BPC-MDS\\Video\\HLS\\"; //puvodni cesta
    //private final static String DASH_PATH = "C:\\Users\\bened\\Documents\\Source\\VUT\\BPC-MDS\\Video\\MPEG-DASH\\"; // puvodni cesta

    private final static String HLS_PATH = "C:\\Users\\wwwhr\\Pictures\\HLS\\"; //#Moje cesta k souboru
    private final static String DASH_PATH = "C:\\Users\\wwwhr\\Pictures\\MPEG-DASH\\"; //#Moje cesta k souboru


    @GetMapping("/index")
    public String home() {
        return "index";
    }


    @GetMapping("/byterange")
    public void byterange(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, MP4_FILE);
        handler.handleRequest(request, response);
    }


    @RequestMapping(value = {"/dash/{file}", "/hls/{file}", "/hls/{quality}/{file}"}, method = RequestMethod.GET)
    public void adaptive_streaming(
            @PathVariable("file") String file,
            @PathVariable(value = "quality", required = false) String quality,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        File STREAM_FILE = null;
        String handle = (String ) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        switch (handle){
            case "/dash/{file}":
                STREAM_FILE = new File(DASH_PATH + file);
                break;
            case "/hls/{file}":
                STREAM_FILE = new File(HLS_PATH + file);
                break;
            case "/hls/{quality}/{file}":
                if(!StringUtils.isEmpty(quality)){
                    STREAM_FILE = new File(HLS_PATH + quality + "\\" + file);
                }
                break;
            default:

                break;
        }
        request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, STREAM_FILE);
        handler.handleRequest(request, response);
    }


}
