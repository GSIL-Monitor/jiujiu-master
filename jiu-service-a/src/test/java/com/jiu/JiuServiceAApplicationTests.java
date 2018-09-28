package com.jiu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JiuServiceAApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void test(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("1","19908623115");
		map.put("2","18696111395");
		List<String> strlist = new ArrayList<String>();
		strlist.add("1");
		strlist.add("3");
		strlist.add("4");

		strlist.stream().forEach((s) -> {
			System.out.println(s);
		});
	}

}
