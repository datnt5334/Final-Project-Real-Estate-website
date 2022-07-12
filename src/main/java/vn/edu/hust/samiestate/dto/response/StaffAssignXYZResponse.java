package vn.edu.hust.samiestate.dto.response;

public class StaffAssignXYZResponse {

    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String profilepicture;
    private String checked;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {

        return fullName;
    }

    public void setFullName(String fullName) {

        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChecked() {

        return checked;
    }

    public void setChecked(String checked) {

        this.checked = checked;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }
}
