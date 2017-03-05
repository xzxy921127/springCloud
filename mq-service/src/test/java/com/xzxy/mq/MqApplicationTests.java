package com.xzxy.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xzxy.mq.config.Sender;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MqApplication.class)
public class MqApplicationTests {
	
	@Autowired
    private Sender sender;
	
    @Test
    public void hello() throws Exception {
        sender.send();
    }
    
    @Test
    public void sendMsg() throws Exception {
    	sender.send("Linux");
    }
}
