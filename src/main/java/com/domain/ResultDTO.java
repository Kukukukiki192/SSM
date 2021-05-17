package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回结果 实体类
 * @param <T>
 */
@AllArgsConstructor
@Data
public class ResultDTO<T> implements Serializable {

    private int code;       //状态码

    private String errMsg;  //用户提示信息

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;         //返回对象

    public ResultDTO(int code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }
}
