package com.javatpoint;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDao {
private JdbcTemplate template;

public void setTemplate(JdbcTemplate template) {
	this.template = template;
}

public List<Employee> getAllEmployeesRowMapper(){
	return template.query("select * from employee",new RowMapper<Employee>(){

		public Employee mapRow(ResultSet rs, int rownumber) throws SQLException {
			Employee e=new Employee();
			e.setId(rs.getInt(1));
			e.setName(rs.getString(2));
			e.setSalary(rs.getInt(3));
			return e;
		}
	});
}

}
