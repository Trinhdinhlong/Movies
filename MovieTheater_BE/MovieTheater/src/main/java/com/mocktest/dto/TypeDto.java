package com.mocktest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.mocktest.entities.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeDto {
    private Long id;
    private String typeName;
    public TypeDto(Type entity){
        if (entity != null) {
            BeanUtils.copyProperties(entity, this);
        }
    }
}
