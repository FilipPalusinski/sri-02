package edu.pja.sri.ewcislo.sri02.dto

import lombok.Data
import java.time.LocalDate

@Data

data class EmployeeDto(
        var id: Long,
        val firstName: String,
        val lastName: String,
        val birthDate: LocalDate,
        val job: String
)
