package com.xzxy.log.web;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	private Logger logger = Logger.getLogger("mongodb");
	
	private static final String SUCCESS_JSON = "{\"reply\":\"success\"}";

//	private static final String ERROR_JSON = "{\"reply\":\"error\"}";

	@RequestMapping(value = "/logHello")
    @ResponseBody
    public String hello(@RequestParam String name) {
		logger.info("Info");
		logger.debug("debug");
		logger.error("error");
        return "Hello " + name;
    }

	@RequestMapping("/logInfo")
    @ResponseBody
	public String info(@RequestParam("msg") String msg){
		logger.info(msg);
		return SUCCESS_JSON;
	}
	
	@RequestMapping("/logDebug")
    @ResponseBody
	public String debug(@RequestParam("msg") String msg){
		logger.info(msg);
		return SUCCESS_JSON;
	}
	
	@RequestMapping("/logError")
    @ResponseBody
	public String error(@RequestParam("msg") String msg){
		logger.info(msg);
		return SUCCESS_JSON;
	}
}
