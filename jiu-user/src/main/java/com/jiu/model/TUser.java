package com.jiu.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author lujj
 * @since 2018-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String passWord;

    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//入参格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//json返回格式化 配合jar包使用
    private LocalDateTime createTime;


}
