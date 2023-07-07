package com.test.swagger.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.Objects;


@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
public class Category   {
  public int id;
  public String name;
}

