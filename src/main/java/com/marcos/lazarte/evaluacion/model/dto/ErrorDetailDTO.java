package com.marcos.lazarte.evaluacion.model.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ErrorDetailDTO {
	
    private static final String GENERIC_ERROR = "An internal server error occurred, please try again later";
    private static final int GENERIC_STATUS = 500;
    private static final String DATA_TIME_FORMAT = "LLL dd, yyyy hh:mm:ss a";

    private final Integer status;
    private final String description;
    private final String timestamp = LocalDateTime.now(ZoneId.of("UTC"))
            .format(DateTimeFormatter.ofPattern(DATA_TIME_FORMAT));

    public static ErrorDetailDTO createGenericError() {
        return ErrorDetailDTO.builder().status(GENERIC_STATUS).description(GENERIC_ERROR).build();
    }

}
