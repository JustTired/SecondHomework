package JDBC.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProjectEntity {
    private String name;
    private Date date;
}
