package com.example.test12.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String to;
    private String from;
    private String body;
}
