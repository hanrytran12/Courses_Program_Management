package Model;

/**
 * Class đại diện cho Topic trong hệ thống.
 *
 * @author Huy
 */
public class Topic {

    private String id;
    private String name;
    private String type;
    private String title;
    private String duration;

    /**
     * Constructor của lớp Topic. Dùng để khởi tạo một đối tượng Topic với các
     * thông tin chi tiết.
     *
     * @param id Mã ID của chủ đề, là duy nhất cho mỗi chủ đề.
     * @param name Tên của chủ đề.
     * @param type Loại của chủ đề (Long term/Short term).
     * @param title Tiêu đề của chủ đề.
     * @param duration Thời lượng của chủ đề.
     */
    public Topic(String id, String name, String type, String title, String duration) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.title = title;
        this.duration = duration;
    }

    //Getter và Setter các attribute
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("|%-5s|%-20s|%-11s|%-22s|%-10s|", id, name, type, title, duration + " hours");
    }

    // convert String để thuận tiện trong việc đi vô File lưu
    public String convertString() {
        return String.format("%s, %s, %s, %s, %s", id, name, type, title, duration);
    }
}
