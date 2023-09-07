package lk.ijse.d24hostelsystem.dto;

import lk.ijse.d24hostelsystem.entity.Student;

import java.util.Date;

public class StudentDTO {
    String studentId;
    String studentName;
    String nic;
    String homeTown;
    String dob;
    String gender;
    String phoneNumber;
    String university;

    public StudentDTO() {
    }

    public StudentDTO(String studentId, String studentName, String nic, String homeTown, String gender, String phoneNumber, String university) {
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", university='" + university + '\'' +
                '}';
    }


}
