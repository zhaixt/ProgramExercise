/*

 */
package com.spring.exercize.web;

import java.util.Date;

import com.spring.exercize.bean.TestBean;
import com.spring.exercize.service.impl.ActionTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/***
 * 测试用的Controller
 * 
 * @author zhoujunhui
 *
 */
@Controller
@RestController
@RequestMapping("/zalabs/demo")
@Configuration
public class TestController {

	@Value("${tddl.appname:}")
	private String appname;
	@Autowired
	private ActionTest actionTest;

	// http://localhost:8080/zalabs/demo/appname
	@RequestMapping(value = "/appname", method = RequestMethod.GET)
	public TestBean appname() {
		TestBean ret = new TestBean(appname, 3, new Date(2014, 4, 4));
		String result = actionTest.test();
		System.out.println("result is:"+result);
		return ret;

	}
}