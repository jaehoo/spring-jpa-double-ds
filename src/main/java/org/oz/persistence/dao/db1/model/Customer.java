package org.oz.persistence.dao.db1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by <a href="https://twitter.com/jaehoox">jaehoo</a> on 16/03/2018
 */
@Entity
@Table(name = "CUSTOMER")
@SequenceGenerator(name = "SQ_CUSTOMER", sequenceName = "SQ_CUSTOMER")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CUSTOMER")
    @SequenceGenerator(sequenceName = "SQ_CUSTOMER", allocationSize = 1, name = "CUST_SEQ")
    @Column(name = "id_customer")
    private Integer idCustomer;

    @Column(name = "name")
    private String name;
}
