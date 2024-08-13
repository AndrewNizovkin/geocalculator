package ru.taheoport.geocalculator_service.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InverseTaskDto {

    private long id;

    private long firstX;

    private long firstY;

    private long firstZ;

    private long secondX;

    private long secondY;

    private long secondZ;
}
