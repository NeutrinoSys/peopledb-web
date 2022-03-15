package com.neutrinosys.peopledbweb.biz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "First name can not be empty")
    private String firstName;

    @NotEmpty(message = "Last name can not be empty")
    private String lastName;

    @Past(message = "Date of birth must be in the past")
    @NotNull(message = "Date of birth must be specified")
    private LocalDate dob;

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email must not be empty")
    private String email;

    @DecimalMin(value="1000.00", message = "Salary must be at least 1000.00")
    @NotNull(message = "Salary can not be empty")
    private BigDecimal salary;

    private String photoFilename;

    public static Person parse(String csvLine) {
        String[] fields = csvLine.split(",\\s*");
        LocalDate dob = LocalDate.parse(fields[10], DateTimeFormatter.ofPattern("M/d/yyyy"));
        return new Person(null, fields[2], fields[4], dob, fields[6], new BigDecimal(fields[25]), null);
    }
}
