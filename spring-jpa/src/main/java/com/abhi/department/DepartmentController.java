package com.abhi.department;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.department.model.Department;
import com.abhi.department.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/findOne/{id}")
	public ResponseEntity<Department> findOne(@PathVariable("id") Long id) {
		logger.debug("DepartmentController.findOne id :"+id);
		Department department = departmentService.findOne(id);
		logger.debug("DepartmentController.findOne name  :"+department.getName());
		return new ResponseEntity<Department>(department, HttpStatus.OK);
	}
	
	@GetMapping("/findOneWithEmployee/{id}")
	public ResponseEntity<Department> findOneWithEmployee(@PathVariable("id") Long id) {
		logger.debug("DepartmentController.findOneWithEmployee id :"+id);
		Department department = departmentService.findOneWithEmployee(id);
		logger.debug("DepartmentController.findOneWithEmployee name  :"+department.getName());
		return new ResponseEntity<Department>(department, HttpStatus.OK);
	}

	
/*	@RequestMapping("/error")
	public ResponseEntity<String> handleError(HttpServletRequest request) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	     
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	     
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	        	return new ResponseEntity<String>("There is no such method here", HttpStatus.NOT_FOUND);
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	        	return new ResponseEntity<String>("There is a issue at server end", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
    	return new ResponseEntity<String>("There is generic error", HttpStatus.BAD_REQUEST);
	}*/
}
