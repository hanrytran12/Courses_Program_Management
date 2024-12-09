package Model;

/**
 * Class đại diện cho Learner trong hệ thống.
 *
 * @author Huy
 */
public class Learner {

    private String id;
    private String name;
    private String dateOfBirth;
    private int score;
    private String course;

    /**
     * Constructor của lớp Learner. Dùng để khởi tạo một đối tượng Learner với
     * các thông tin chi tiết.
     *
     * @param id Mã ID của sinh viên, là duy nhất cho mỗi học viên.
     * @param name Tên của sinh viên.
     * @param dateOfbirth Ngày sinh của sinh viên.
     * @param score Điểm số của sinh viên.
     * @param course Mã khóa học mà sinh viên đang tham gia.
     */
    public Learner(String id, String name, String dateOfbirth, int score, String course) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfbirth;
        this.score = score;
        this.course = course;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // Kiểm tra sinh viên có đậu khóa học không
    public boolean isPassCourse() {
        return score >= 60;
    }

    // Tính GPA dựa vào score
    public String getGPA() {
        if (score >= 90) {
            return "4.0";
        }
        if (score >= 80 && score <= 89) {
            return "3.5";
        }
        if (score >= 70 && score <= 79) {
            return "3.0";
        }
        if (score >= 60 && score <= 69) {
            return "2.0";
        }
        return "0.0";
    }

    @Override
    public String toString() {
        return String.format("|%-5s|%-20s|%-14s|%-5d|%-20s|%-7s|%-5s|", id, name, dateOfBirth, score, course, (isPassCourse()) ? "Pass" : "Fail", getGPA());
    }

    // convert String để thuận tiện trong việc đi vô File lưu
    public String convertString() {
        return String.format("%s, %s, %s, %s, %s", id, name, dateOfBirth, score, course);
    }
}
