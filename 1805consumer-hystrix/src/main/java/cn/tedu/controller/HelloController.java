package cn.tedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.tedu.feign.EurekaServiceFeign;

@RestController
public class HelloController {
	
	@Autowired
	private EurekaServiceFeign feign;
	@RequestMapping("/hello/{name}")
	@HystrixCommand(fallbackMethod="fallbackHello")
	public String hello(@PathVariable String name){
				
		return feign.hello(name);
	}
	//失败时，断路器自动回调这个方法
	//方法名字和fallbackMethod名称一致
	public String fallbackHello(String name){
		
		return"tony";
	}
}
