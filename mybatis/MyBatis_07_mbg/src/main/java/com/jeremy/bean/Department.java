package com.jeremy.bean;

public class Department {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_dept.id
     *
     * @mbg.generated Sun Apr 18 16:59:36 BST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_dept.dept_name
     *
     * @mbg.generated Sun Apr 18 16:59:36 BST 2021
     */
    private String deptName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_dept.id
     *
     * @return the value of tb_dept.id
     *
     * @mbg.generated Sun Apr 18 16:59:36 BST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_dept.id
     *
     * @param id the value for tb_dept.id
     *
     * @mbg.generated Sun Apr 18 16:59:36 BST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_dept.dept_name
     *
     * @return the value of tb_dept.dept_name
     *
     * @mbg.generated Sun Apr 18 16:59:36 BST 2021
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_dept.dept_name
     *
     * @param deptName the value for tb_dept.dept_name
     *
     * @mbg.generated Sun Apr 18 16:59:36 BST 2021
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}