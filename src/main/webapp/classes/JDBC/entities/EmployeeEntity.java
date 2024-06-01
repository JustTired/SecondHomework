package JDBC.entities;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeEntity {

    private UUID id;
    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private CompanyEntity company;
}
