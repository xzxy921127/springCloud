package com.xzxy.web;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 失败页面控制器
 * @author XZXY
 *
 */
@Controller
public class MainsiteErrorController implements ErrorController {
	private static final String ERROR_PATH = "/error";  
	
    @RequestMapping(value=ERROR_PATH)  
    public String handleError(){  
        return "common/error";  
    }
    
    @Override  
    public String getErrorPath() {  
        return ERROR_PATH;  
    }
}
