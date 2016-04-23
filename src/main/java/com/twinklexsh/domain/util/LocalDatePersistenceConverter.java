package com.twinklexsh.domain.util;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDatePersistenceConverter implements
    AttributeConverter<java.time.LocalDate, java.sql.Date> {

	public Date convertToDatabaseColumn(LocalDate entityValue) {
		return java.sql.Date.valueOf(entityValue);
	}

	public LocalDate convertToEntityAttribute(Date databaseValue) {
		return databaseValue.toLocalDate();
	}
	
	

	
}
