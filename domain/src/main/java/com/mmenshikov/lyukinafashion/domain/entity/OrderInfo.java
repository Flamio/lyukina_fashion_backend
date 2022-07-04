package com.mmenshikov.lyukinafashion.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "order_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderInfo {

    @PrePersist
    public void prePersist() {
        dateTime = OffsetDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;
    private String address;
    private String email;

    @Column(name = "date_time")
    private OffsetDateTime dateTime;

    @Column(name = "contact_name")
    private String contactName;

    @Enumerated(EnumType.STRING)
    @Column(name = "contact_type")
    private ContactType contactType;


    public enum ContactType {
        EMAIL("email"), PHONE("телефон");

        ContactType(String value) {
            this.value = value;
        }

        @Getter
        private final String value;

    }
}
