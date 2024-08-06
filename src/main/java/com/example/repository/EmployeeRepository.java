package com.example.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.example.domain.Employee;

/**
 * employees テーブルを操作するリポジトリ(Dao).
 */
@Repository
public class EmployeeRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        // employee.setHireDate(null);
        employee.setMailAddress(rs.getString("mailAddress"));
        employee.setZipCode(rs.getString("zipCode"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependentsCount"));

        return employee;
    };

    /**
     * 従業員⼀覧情報を⼊社⽇順(降順)で取得する.
     * 
     * <pre>
     * レコードが存在しない場合は空のリストを返却します。
     * </pre>
     * 
     * @return List Employee 従業員リスト
     */
    public List<Employee> findAll() {
        String sql = """
                SELECT
                    id
                    , name
                    , image
                    , gender
                    , hire_date
                    , mail_address
                    , zip_code
                    , address
                    , telephone
                    , salary
                    , characteristics
                    , dependents_count
                FROM
                    employees
                """;
        List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
        return employeeList;
    }

    /**
     * 主キーから従業員情報を取得する.
     * 
     * <pre>
     * 従業員が存在しない場合はnullを返却します。
     * </pre>
     * 
     * @param id 従業員ID
     * @return Employee 又は null
     */
    public Employee load(Integer id) {
        String sql = """
                SELECT
                    id
                    , name
                    , image
                    , gender
                    , hire_date
                    , mail_address
                    , zip_code
                    , address
                    , telephone
                    , salary
                    , characteristics
                    , dependents_count
                FROM
                    employees
                WHERE
                    id = :id
                """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

        try {
            return template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 従業員情報を変更する.
     * 
     * <pre>
     * 対象従業員のの全てのカラムを更新します。
     * </pre>
     * 
     * @param employee 従業員情報
     */
    public void update(Employee employee) {

        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

        String sql = """
                UPDATE employees SET
                    name = :name
                    , image = :image
                    , gender = :gender
                    , hire_date= :hireDate
                    , mail_address = mailAddress
                    , zip_code = zipCode
                    , address = address
                    , telephone = telephone
                    , salary = salary
                    , characteristics = characteristics
                    , dependents_count = dependentsCount
                WHERE id = :id
                """;

        template.update(sql, param);
    }
}
