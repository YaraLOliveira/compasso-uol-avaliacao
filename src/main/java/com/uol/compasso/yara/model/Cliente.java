package com.uol.compasso.yara.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder //swagger
@Entity //jpa
@NoArgsConstructor //swagger
@AllArgsConstructor //swwager
@Table(name = "CLIENTE") //jpa
@ApiModel(value = "Cliente", description = "Informaçções sobre a tabela cliente") //swagger
public class Cliente {

    @Getter
    @Setter
    @Id
    @Column(name = "ID") //jpa
    @ApiModelProperty(value = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Getter//swagger
    @Setter//swagger
    @Column(name = "NOME") //jpa
    @ApiModelProperty(value = "nome") //documentacao do swagger
    private String nome;

    @Getter
    @Setter
    @Column(name = "SEXO")
    @ApiModelProperty(value = "sexo")
    private String sexo;

    @Getter
    @Setter
    @Column(name = "DATANASCIMENTO")
    @ApiModelProperty(value = "dataNascimento")
    private LocalDate dataNascimento;

    @Getter
    @Setter
    @Column(name = "IDADE")
    @ApiModelProperty(value = "idade")
    private int idade;

    @Getter
    @Setter
    @ApiModelProperty(value = "cidade")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId
    private Cidade cidade;
}
