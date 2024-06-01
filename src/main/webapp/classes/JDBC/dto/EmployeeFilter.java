package JDBC.dto;

public record EmployeeFilter(int limit, int offset, String role, String email) {

}
