package com.spring.entity;

import com.spring.config.AppConfig;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.*;
import java.lang.annotation.Annotation;

@Entity
@Table(name = "issue")
public class Issue {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

//    @JoinColumn(name="customer_id")
//    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
//            CascadeType.DETACH, CascadeType.REFRESH})
//    private Customer customer;
    @Column(name = "user_username")
    private String customer;

    @Column(name = "status")
    private String status;

    public Issue() {
    }

    public Issue(int id, String description, String customer, String status) {
        this.id = id;
        this.description = description;
        this.customer = customer;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", customer=" + customer +
                ", status='" + status + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        this.customer = context.getBean(Customer.class,id);
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
