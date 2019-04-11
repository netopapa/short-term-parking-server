package com.parking.rest.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PeriodSearchDto {
    private Date start;
    private Date end;
    private Boolean inside;
}
