package com.uol.compasso.yara.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uol.compasso.yara.enuns.EnumEstado;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CIDADE")
@ApiModel(value = "Cidade", description = "Informaçções sobre a tabela cidade")
public class Cidade {

    @Getter
    @Setter
    @Id
    @Column(name = "ID") //jpa
    @ApiModelProperty(value = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Getter
    @Setter
    @Column(name = "NOME")
    @ApiModelProperty(value = "nome")
    private String nome;

    @Getter
    @Setter
    @Column(name ="ESTADO")
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "estado")
    private EnumEstado estado;




}
