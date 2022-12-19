package com.storedproc.demo.oracle;


import com.storedproc.demo.DatabaseTypes;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;

import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.REF_CURSOR;


@Entity
@Table(name = "USERS")
@NamedStoredProcedureQuery(
        name = "CARDMASK_SP",
        resultClasses = User.class,
        procedureName = "CARDMASK",
        parameters = {
                @StoredProcedureParameter(mode = IN, name = "FIRSTNAME", type = String.class),
                @StoredProcedureParameter(mode = IN, name = "LASTNAME", type = String.class),
                @StoredProcedureParameter(mode = REF_CURSOR, name = "RES", type = void.class)
        }
)
@Profile(DatabaseTypes.ORACLE)
public class User {

    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    private String id;

    @Column(name = "FIRSTNAME", length = 50)
    private String firstName;

    @Column(name = "LASTNAME", length = 50)
    private String lastName;

    @Column(name = "AGE", length = 50)
    private Long age;

    @Column(name = "PHONE", length = 50)
    private String phone;

    @Column(name = "CARDNUMBER", length = 50)
    private String cardNumber;

    public User() {
    }

    public User(String id, String firstName, String lastName, Long age, String phone, String cardNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.cardNumber = cardNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

}