package cn.edu.zucc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
@EnableCaching
public class StudioDemoApplication {
    @RequestMapping("/")
    public String greeting(ModelMap map) {  
        String jreVersion = System.getProperty("java.specification.version");
        map.addAttribute("jreVersion", "v" + jreVersion);
        return "index";
    } 

	public static void main(String[] args) {
		SpringApplication.run(StudioDemoApplication.class, args);
	}
}
