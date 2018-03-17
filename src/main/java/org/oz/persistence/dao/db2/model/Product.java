package org.oz.persistence.dao.db2.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by <a href="https://twitter.com/jaehoox">jaehoo</a> on 16/03/2018
 */
@Entity
@Table(name = "PRODUCT")
@SequenceGenerator(name = "SQ_PROD", sequenceName = "SQ_PROD")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PROD")
    @SequenceGenerator(sequenceName = "SQ_PROD", allocationSize = 1, name = "PROD_SEQ")
    @Column(name = "id_product")
    private int id;
    private String name;
    private double price;
}
