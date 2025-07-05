package org.example.bigevent.pojo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.Builder;
import lombok.Data;
import org.example.bigevent.annotation.State;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
public class Article {
    private Integer id;
    @Pattern(regexp = "^\\S{1,10}$", message = "标题长度为1~10个字符")
    @NotEmpty(message = "标题不能为空", groups = Article.Add.class)
    private String title;
    private String content;
    @URL
    private String coverImg;
    @State
    private String state;
    private Integer categoryId;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public interface Add extends Default {

    }

}
