package vn.edu.hust.samiestate.dto.request;

import vn.edu.hust.samiestate.dto.AbstractDTO;

public class NewsSearchRequest extends AbstractDTO {

    private String title;
    private String categoryCode;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}
