package lk.ijse.d24hostelsystem.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    @Column(length = 10,name = "studentId")
    String studentId;
    @Column(name = "studentName")
    String studentName;
    @Column(name = "nic")
    String nic;
    @Column(name="homeTown")
    String homeTown;
    //    @Column(name = "dob")
//    Date dob;
    @Column(name = "gender")
    String gender;
    @Column(name = "phoneNumber")
    String phoneNumber;
    @Column(name = "university")
    String university;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "student")
    private List<Reservation> reservationList;

    public Student() {
    }

    public Student(String studentId, String studentName, String nic, String homeTown,  String gender, String phoneNumber, String university) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.nic = nic;
        this.homeTown = homeTown;

        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.university = university;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", nic='" + nic + '\'' +
                ", homeTown='" + homeTown + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", university='" + university + '\'' +
                '}';
    }


}
