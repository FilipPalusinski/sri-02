package edu.pja.sri.ewcislo.sri02.rest

import edu.pja.sri.ewcislo.sri02.dto.EmployeeDto
import edu.pja.sri.ewcislo.sri02.model.toDomainModel
import edu.pja.sri.ewcislo.sri02.model.toEntity
import edu.pja.sri.ewcislo.sri02.repo.EmployeeRepository
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/api/empoyees")
class EmployeeController(private val employeeRepository: EmployeeRepository){

    @GetMapping
    fun getEmployees(): ResponseEntity<Collection<EmployeeDto>> {
        val allEmployees = employeeRepository.findAll()
        val result = allEmployees.map { it.toDomainModel() }
        return ResponseEntity.ok(result)
    }


    @GetMapping("/{empId}")
    fun getEmployeeById(@PathVariable empId: Long): ResponseEntity<EmployeeDto> {
        val emp = employeeRepository.findById(empId)
        return if (emp.isPresent) {
            val employeeDto = emp.get().toDomainModel()
            ResponseEntity.ok(employeeDto)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun saveNewEmployee(@RequestBody emp: EmployeeDto): ResponseEntity<Any> {
        val entity = emp.toEntity()
        employeeRepository.save(entity)
        val headers = HttpHeaders()
        val location: URI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.id)
                .toUri()
        headers.add("Location", location.toString())
        return ResponseEntity(headers, HttpStatus.CREATED)
    }

    @PutMapping("/{empId}")
    fun updateEmployee(@PathVariable empId: Long, @RequestBody employeeDto: EmployeeDto): ResponseEntity<Any> {
        val currentEmp = employeeRepository.findById(empId)
        return if (currentEmp.isPresent) {
            employeeDto.id = empId
            val entity = employeeDto.toEntity()
            employeeRepository.save(entity)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{empId}")
    fun deleteEmployee(@PathVariable empId: Long): ResponseEntity<Any> {
        employeeRepository.deleteById(empId)
        return ResponseEntity.noContent().build()
    }





}