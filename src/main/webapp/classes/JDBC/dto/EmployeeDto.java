package JDBC.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@EqualsAndHashCode
@Getter
@ToString
@AllArgsConstructor
public class EmployeeDto {
    private final UUID uuid;
    private final String firstName;
    private final String role;
    private final String email;
}
