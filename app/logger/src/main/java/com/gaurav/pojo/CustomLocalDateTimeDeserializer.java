package com.gaurav.pojo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomLocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    private static final long serialVersionUID = -7449444168934819290L;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    
    

    public CustomLocalDateTimeDeserializer() {
    	this(null);
    }

    public CustomLocalDateTimeDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDateTime deserialize(final JsonParser jsonparser, final DeserializationContext context) throws IOException, JsonProcessingException {
    	final String date = jsonparser.getText();
    	return LocalDateTime.parse(date, formatter);
    }
}