package com.xzxy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 构建前端框架的Controller
 * @author XZXY
 *
 */
@Controller
@RequestMapping("frameset")
public class FramesetController {
	
	@RequestMapping("/hi")
	public String hello(){
		return "hello";
	}

	@RequestMapping("/head")
	public String head(){
		return "frameset/head";
	}
	
	@RequestMapping("/left")
	public String left(){
		return "frameset/left";
	}
	
	@RequestMapping("/home")
	public String home(){
		return "frameset/home";
	}
}