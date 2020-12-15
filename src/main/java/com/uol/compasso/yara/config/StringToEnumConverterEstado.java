package com.uol.compasso.yara.config;

import com.uol.compasso.yara.enuns.EnumEstado;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;

/**
 * Quando trabalhamos com microserviço, a maioria dos dados são tipos primitivos.
 * Quando o tipo no  primitivo, precisamos de um converter para converatar os dados.
 * Pq tudo que trafega no http string.
 */
@Configuration
public class StringToEnumConverterEstado implements Converter<String, EnumEstado> {
    @Override
    public EnumEstado convert(String s) {
        return EnumEstado.valueOf(s.toUpperCase());
    }
}
