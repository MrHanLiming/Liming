package com.liming.entity.user;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResultMenuEntity {
    private String path;
    private String component;
    private String icon;
    private String name;
    private int parentId;
    private boolean hidden;
    private List children;
}
