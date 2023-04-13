package edu.pja.sri.ewcislo.sri02.repo

import edu.pja.sri.ewcislo.sri02.model.Employee
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : CrudRepository<Employee, Long> {
    override fun findAll(): List<Employee>
}
