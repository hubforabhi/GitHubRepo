package com.abhi.mockito.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.abhi.mockito.service.BusinessService;
import com.abhi.mockito.service.DataService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMockitoDemoApplicationTests {
	
	@MockBean
	private DataService dataServiceMock;
	
	@Autowired
	private BusinessService businessService;

	@Test
	public void testGreatestValueFromArray() {
		when(dataServiceMock.getData()).thenReturn(new int[] {1,2,3,4});
		 assertEquals(4, businessService.getGreatest());		
	}
}