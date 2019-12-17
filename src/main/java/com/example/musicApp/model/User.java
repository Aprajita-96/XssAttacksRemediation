package com.example.musicApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
//    @NumberFormat
    private int track_id;
    //@Size(min=10,max=25)
//    @Pattern(regexp="^([a-zA-Z])+$",
//            message="Name  is invalid")
    private String  track_Name;

    private String phoneNumber;
    private String date;

}
