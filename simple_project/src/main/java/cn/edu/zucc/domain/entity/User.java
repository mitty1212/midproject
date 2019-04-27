package cn.edu.zucc.domain.entity;

//import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
//import javax.persistence.Entity;

@Entity
//@Table(name = "test", schema = "user")
public class User {
        @Id
        @GeneratedValue
        private Long id;
        @Column(name = "name")
        @NotEmpty(message = "注解声明:用户名不能为空")
        private String name;
        @Column(name = "age")
        private Integer age;
        private  String role;
        private String email;
        private String password;
        private String salt;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //        private double monthincome;
//        private double yearincome;

//        public double getMonthincome() {
//            return monthincome;
//        }

        //public void setMonthincome(double monthincome) {
//            this.monthincome = monthincome;
//        }

//        public double getYearincome() {
//            return yearincome;
//        }

//        public void setYearincome(double yearincome) {
//            this.yearincome = yearincome;
//        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getAge() {
            return age;
        }
}
