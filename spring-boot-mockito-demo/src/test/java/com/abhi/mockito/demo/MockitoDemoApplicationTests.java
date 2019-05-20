package com.abhi.mockito.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.abhi.mockito.service.BusinessServiceImpl;
import com.abhi.mockito.service.DataService;

@RunWith(MockitoJUnitRunner.class)
public class MockitoDemoApplicationTests {
	
	@Mock
	private DataService dataServiceMock;
	
	@InjectMocks
	private BusinessServiceImpl businessService; // use the Impl class, interface name will throw error

	@Test
	public void testGreatestValueFromArray() {
		when(dataServiceMock.getData()).thenReturn(new int[] {1,2,3,4});
		 assertEquals(4, businessService.getGreatest());		
	}
}