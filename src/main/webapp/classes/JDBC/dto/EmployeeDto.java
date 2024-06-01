package JDBC.dto;


import java.util.UUID;


public record EmployeeDto(UUID uuid, String firstName, String role, String email) {
}
