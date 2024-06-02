package JDBC.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class SqlRequestConstants {

    public static final String INSERT_COMPANY_QUERY = """
            insert into companies (company_name)
            values (?);
            """;
    public static final String INSERT_EMPLOYEE_QUERY = """
            insert into employs (id,first_name,last_name,role,email,companyname)
            values (?,?,?,?,?,?);
            """;
    public static final String INSERT_PROJECT_QUERY = """
            insert into projects (name, startdate)
            values (?,?);
            """;
    public static final String READ_DB = """
            select * from employs
            join companies on employs.companyname = companies.company_name
            join employes_projects on employs.id = employes_projects.employee_uuid
            join projects on projects_name = projects.name
            """;
    public static final String UPDATE_EMPLOYEE_QUERY = """
            update employs
            set first_name = ?, last_name = ?, role = ?, email = ?
            where id = ?
            """;
    public static final String UPDATE_PROJECT_QUERY = """
            update projects
            set startdate = ?
            where name = ?
            """;
    public static final String UPDATE_COMPANY_QUERY = """
            update companies
            set company_name = ?
            where company_name = ?
            """;
    public static final String DELETE_EMPLOYEE = """
            delete from employs
            where id = ?
            """;
    public static final String DELETE_PROJECT = """
            delete from projects
            where name = ?
            """;
    public static final String DELETE_COMPANY = """
            delete from companies
            where company_name = ?
            """;
}