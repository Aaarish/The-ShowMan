package com.roya.showman.apis.dtos;

import com.roya.showman.constants.Status;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private Object body;
    private Status status;

}
