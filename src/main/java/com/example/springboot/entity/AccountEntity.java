package com.example.springboot.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNT")
public class AccountEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ACCOUNT_TYPE")
    private String AccountType;

    @Column(name = "ACCOUNT_NUMBER")
    private String AccountNumber;

    @Column(name = "ACCOUNT_FK")
    private String Account_Fk;

}
