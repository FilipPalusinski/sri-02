package edu.pja.sri.ewcislo.sri02

import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Sri02Application

fun main(args: Array<String>) {
	runApplication<Sri02Application>(*args)

	@Bean
	fun modelMapper(): ModelMapper{
		return ModelMapper()
	}



}
