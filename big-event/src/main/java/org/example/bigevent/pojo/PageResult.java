package org.example.bigevent.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PageResult {
    private Integer totals;
    private Object items;
}
