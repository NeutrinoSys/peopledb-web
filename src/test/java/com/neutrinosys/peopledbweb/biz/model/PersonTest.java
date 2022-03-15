package com.neutrinosys.peopledbweb.biz.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    public void canParse() {
        String csvLine = "224660,Drs.,    Imogene,P,Hagopian,F,imogene.hagopian@gmail.com,Delmar Hagopian,Carolina Hagopian,Lockett,     2/19/1995,    03:15:09 PM,25.55,52,6/8/2017,Q2,H1,2017,6,June,Jun,8,Thursday,Thu,3.23,55761,14%,656-36-0772,210-460-6327,Bryan,Brazos,Bryan,TX,77806,South,iphagopian,d[aHK7aiH&";
        Person person = Person.parse(csvLine);
        assertThat(person.getDob()).isEqualTo(LocalDate.of(1995, 2, 19));
    }

}