package com.xzxy;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xzxy.model.User;
import com.xzxy.model.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(UserServiceApplication.class)
public class ApplicationTests {
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testAdd() throws Exception {
		// 创建10条记录
		userRepository.save(new User("AAA", 10));
	}
	
	@Test
	public void testLoad() throws Exception {
		User u = userRepository.find(1L);
		System.err.println(u.getName()+","+u.getAge());
	}
	
	@Test
	public void testFindAll() throws Exception {
		List<User> list = userRepository.findAll();
		for(User u : list){
			System.err.println(u.getName()+","+u.getAge());
		}
	}
	
	@Test
	public void testUpdate() throws Exception {
		User u = userRepository.find(6L);
		u.setName("ABC");
		userRepository.update(u.getName(), u.getAge(), u.getId());
		System.err.println("修改成功！");
	}
	
	@Test
	public void testDelete() throws Exception {
		userRepository.delete(6L);
		System.err.println("删除成功！");
	}
}
