package com.maidservice.maidtoorder.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "maid")
public class Maid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maid_id")
    private Long maidId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "gender", length = 10)
    private String gender;

    @Lob
    @Column(name = "profile_picture")
    private byte[] profilePicture;
}