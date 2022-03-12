package vn.edu.hust.samiestate.dto.response;

public class StaffAssignResponse {

    private Long id;
    private String fullName;
    private String email;
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

}
