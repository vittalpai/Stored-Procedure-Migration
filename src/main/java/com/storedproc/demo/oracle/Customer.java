package com.storedproc.demo.oracle;


import com.storedproc.demo.DatabaseTypes;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;

import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.REF_CURSOR;


@Entity
@Table(name = "CUSTOMER")
@NamedStoredProcedureQuery(
        name = "TOPCUSTOMERS_SP",
        resultClasses = Customer.class,
        procedureName = "TOPCUSTOMERS",
        parameters = {
                @StoredProcedureParameter(mode = IN, name = "ZIPCODE", type = String.class),
                @StoredProcedureParameter(mode = IN, name = "OCCUPATION", type = String.class),
                @StoredProcedureParameter(mode = REF_CURSOR, name = "RES", type = void.class)
        }
)
@Profile(DatabaseTypes.ORACLE)
public class Customer {

    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    private String id;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "PHONENUMBER", length = 50)
    private String phone;

    @Column(name = "MASKEDCARDNUMBER", length = 50)
    private String cardNumber;

    public Customer() {
    }

    public Customer(String id, String name, String phone, String cardNumber) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.cardNumber = cardNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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