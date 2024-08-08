package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import com.example.util.annotation.Numeric;
import com.example.util.annotation.Telephone;
import com.example.util.annotation.ZipCode;

/**
 * 従業員情報更新時に使⽤するフォーム.
 */
public class UpdateEmployeeForm {

    /** 従業員ID. */
    private String id;

    /** 名前. */
    @NotBlank
    private String name;

    /** 画像. */
    private String image;

    /** 性別. */
    @NotBlank
    private String gender;

    /** ⼊社⽇. */
    @NotBlank
    private String hireDate;

    /** メールアドレス. */
    @NotBlank
    @Email
    private String mailAddress;

    /** 郵便番号. */
    @NotBlank
    @ZipCode
    private String zipCode;

    /** 住所. */
    @NotBlank
    private String address;

    /** 電話番号. */
    @NotBlank
    @Telephone
    private String telephone;

    /** 給料. */
    @NotBlank
    @Numeric
    private String salary;

    /** 特性. */
    @NotBlank
    private String characteristics;

    /** 扶養⼈数. */
    @NotBlank
    @Numeric
    private String dependentsCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getDependentsCount() {
        return dependentsCount;
    }

    public void setDependentsCount(String dependentsCount) {
        this.dependentsCount = dependentsCount;
    }


    @Override
    public String toString() {
        return "UpdateEmployeeForm [id=" + id + ", name=" + name + ", image=" + image + ", gender="
                + gender + ", hireDate=" + hireDate + ", mailAddress=" + mailAddress + ", zipCode="
                + zipCode + ", address=" + address + ", telephone=" + telephone + ", salary="
                + salary + ", characteristics=" + characteristics + ", dependentsCount="
                + dependentsCount + "]";
    }
}
