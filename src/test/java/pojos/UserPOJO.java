package pojos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class UserPOJO {

    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<Data> data;
    private Support support;

    @Getter
    public static class Data {
        private int id;
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;
    }

    @Getter
    public static class Support {
        private String url;
        private String text;
    }
}
