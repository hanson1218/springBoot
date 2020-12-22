package com.lhs.www.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLogBack {
	
	Logger log = LoggerFactory.getLogger(TestLogBack.class);
	
	@Test
	public void testLayoutConsole() {
		log.info("日志输出 info");
		log.debug("日志输出 debug");
		log.error("日志输出 error");
	}

}
