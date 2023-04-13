package edu.pja.sri.ewcislo.sri02.model

import edu.pja.sri.ewcislo.sri02.dto.EmployeeDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate

@Entity
@Data
data class Employee(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        val firstName: String,
        val lastName: String,
        val birthDate: LocalDate,
        val job: String
)

fun Employee.toDomainModel() = EmployeeDto(
        id = id,
        firstName = firstName,
        lastName = lastName,
        birthDate = birthDate,
        job = job
)

fun EmployeeDto.toEntity() = Employee(
        id = id,
        firstName = firstName,
        lastName = lastName,
        birthDate = birthDate,
        job = job
)