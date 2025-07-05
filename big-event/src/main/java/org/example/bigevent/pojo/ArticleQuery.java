package org.example.bigevent.pojo;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ArticleQuery {
    @NotNull(message = "页码不能为空")
    private Integer pageNum;
    @NotNull(message = "页大小不能为空")
    private Integer pageSize;
    private Integer categoryId;
    private String state;
    private Integer userId;
}
