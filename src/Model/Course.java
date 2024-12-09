package Model;

/**
 * Class đại diện cho Course trong hệ thống
 *
 * @author Huy
 */
public class Course {

    private String id;
    private String name;
    private String type;
    private String title;
    private String beginDate;
    private String endDate;
    private String tuitionFee;
    private String topic;
    private int numberOfLearnerPass = 0;
    private int numberOfLearnerFail = 0;

    /**
     * Constructor của lớp Course. Dùng để khởi tạo một đối tượng Course với các
     * thông tin chi tiết.
     *
     * @param id Mã ID của khóa học, là duy nhất cho mỗi khóa học.
     * @param name Tên của khóa học.
     * @param type Loại khóa học (online hoặc offline).
     * @param title Tiêu đề của khóa học.
     * @param beginDate Ngày bắt đầu của khóa học.
     * @param endDate Ngày kết thúc của khóa học.
     * @param tuitionFee Học phí của khóa học.
     * @param topic Chủ đề liên quan đến khóa học.
     */
    public Course(String id, String name, String type, String title, String beginDate, String endDate, String tuitionFee, String topic) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.tuitionFee = tuitionFee;
        this.topic = topic;
    }

    // Getter và Setter các attribute
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

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(String tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    // Tăng, giảm số lượng sinh viên pass-fail
    public void increasePass() {
        this.numberOfLearnerPass++;
    }

    public void decreasePass() {
        this.numberOfLearnerPass--;
    }

    public void increaseFail() {
        this.numberOfLearnerFail++;
    }

    public void decreaseFail() {
        this.numberOfLearnerFail--;
    }

    // Tổng size của 1 khóa học = số người pass + số người trượt
    public int getSizeOfClass() {
        return this.numberOfLearnerFail + this.numberOfLearnerPass;
    }

    // Doanh thu bằng số lượng người tham gia khóa học * tuitionFee
    public String getIncome() {
        return Integer.toString(this.getSizeOfClass() * Integer.parseInt(this.tuitionFee));
    }

    @Override
    public String toString() {
        return String.format("|%-5s|%-22s|%-8s|%-28s|%-11s|%-11s|%-13s|%-13s|%-16d|%-17d|%-8s|", id, name, type, title, beginDate, endDate, "$" + tuitionFee, topic, numberOfLearnerPass, numberOfLearnerFail, "$" + getIncome());
    }

    // convert String để thuận tiện trong việc đi vô File lưu
    public String convertString() {
        return String.format("%s, %s, %s, %s, %s, %s, %s, %s", id, name, type, title, beginDate, endDate, tuitionFee, topic);
    }
}
