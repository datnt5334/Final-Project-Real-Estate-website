package vn.edu.hust.samiestate.builder;

public class CustomerSearchBuilder {

    private String fullName;
    private String phone;
    private String email;
    private Long staffId;
    private String statusCode;

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Long getStaffId() {
        return staffId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    private CustomerSearchBuilder(Builder builder) {
        this.fullName = builder.fullName;
        this.phone = builder.phone;
        this.email = builder.email;
        this.staffId = builder.staffId;
        this.statusCode = builder.statusCode;
    }

    public static class Builder {

        private String fullName;
        private String phone;
        private String email;
        private Long staffId;
        private String statusCode;

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }

        public Builder setStatusCode(String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public CustomerSearchBuilder build() {
            return new CustomerSearchBuilder(this);
        }

    }
}
