package Service;

import Model.Course;
import Model.Topic;
import java.util.HashMap;
import java.util.Map;

/**
 * Class ValidationCourseService dùng để kiểm tra tính hợp lệ của các thuộc tính (attribute) của đối tượng Course.
 * 
 * @author Huy
 */
public class ValidationCourseService {

    /**
     * Kiểm tra dữ liệu có hợp lệ để thêm Course vào danh sách hay không.
     * 
     * @param id           ID của Course.
     * @param name         Tên của Course.
     * @param type         Kiểu của Course (Online/Offline).
     * @param title        Tiêu đề của Course.
     * @param beginDate    Ngày bắt đầu của Course.
     * @param endDate      Ngày kết thúc của Course.
     * @param tuitionFee   Học phí của Course.
     * @param topic        Chủ đề (Topic) của Course.
     * @param listOfCourse Danh sách hiện tại của các Course.
     * @param listOfTopic  Danh sách hiện tại của các Topic.
     * @return boolean     Trả về true nếu tất cả dữ liệu hợp lệ, false nếu có bất kỳ lỗi nào.
     */
    public boolean isValidDataToAddCourse(String id, String name, String type, String title, String beginDate, String endDate, String tuitionFee, String topic, HashMap<String, Course> listOfCourse, HashMap<String, Topic> listOfTopic) {
        return isValidId(id, listOfCourse) && isValidName(name) && isValidType(type) && isValidTitle(title) 
               && isValidDate(beginDate, endDate) && isValidTuitionFee(tuitionFee) && isValidTopic(topic, listOfTopic);
    }

    /**
     * Kiểm tra dữ liệu có hợp lệ để cập nhật Course hay không (không kiểm tra ID vì ID đã tồn tại).
     * 
     * @param name         Tên của Course.
     * @param type         Kiểu của Course (Online/Offline).
     * @param title        Tiêu đề của Course.
     * @param beginDate    Ngày bắt đầu của Course.
     * @param endDate      Ngày kết thúc của Course.
     * @param tuitionFee   Học phí của Course.
     * @param topic        Chủ đề (Topic) của Course.
     * @param listOfCourse Danh sách hiện tại của các Course.
     * @param listOfTopic  Danh sách hiện tại của các Topic.
     * @return boolean     Trả về true nếu tất cả dữ liệu hợp lệ, false nếu có bất kỳ lỗi nào.
     */
    boolean isValidDataToUpdate(String name, String type, String title, String beginDate, String endDate, String tuitionFee, String topic, HashMap<String, Course> listOfCourse, HashMap<String, Topic> listOfTopic) {
        return isValidName(name) && isValidType(type) && isValidTitle(title) 
               && isValidDate(beginDate, endDate) && isValidTuitionFee(tuitionFee) && isValidTopic(topic, listOfTopic);
    }

    /**
     * Kiểm tra ID có hợp lệ và chưa tồn tại trong danh sách Course hay không.
     * 
     * @param id           ID của Course.
     * @param listOfCourse Danh sách các Course hiện tại.
     * @return boolean     Trả về true nếu ID hợp lệ và chưa tồn tại, ngược lại trả về false.
     */
    public boolean isValidId(String id, HashMap<String, Course> listOfCourse) {
        return !id.isEmpty() && !listOfCourse.containsKey(id);
    }

    /**
     * Kiểm tra tên có hợp lệ (không rỗng) hay không.
     * 
     * @param name Tên của Course.
     * @return boolean Trả về true nếu tên không rỗng, ngược lại trả về false.
     */
    public boolean isValidName(String name) {
        return !name.isEmpty();
    }

    /**
     * Kiểm tra loại Course có hợp lệ (Online hoặc Offline) hay không.
     * 
     * @param type Kiểu của Course.
     * @return boolean Trả về true nếu loại là Online hoặc Offline, ngược lại trả về false.
     */
    public boolean isValidType(String type) {
        return type.matches("(Online|Offline)");
    }

    /**
     * Kiểm tra tiêu đề có hợp lệ (không rỗng) hay không.
     * 
     * @param title Tiêu đề của Course.
     * @return boolean Trả về true nếu tiêu đề không rỗng, ngược lại trả về false.
     */
    public boolean isValidTitle(String title) {
        return !title.isEmpty();
    }

    /**
     * Kiểm tra tính hợp lệ của ngày bắt đầu và ngày kết thúc của Course.
     * 
     * @param beginDate Ngày bắt đầu (dạng dd-MM-yyyy).
     * @param endDate   Ngày kết thúc (dạng dd-MM-yyyy).
     * @return boolean  Trả về true nếu ngày hợp lệ và ngày kết thúc lớn hơn ngày bắt đầu.
     */
    public boolean isValidDate(String beginDate, String endDate) {
        if (beginDate.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}") && endDate.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
            String[] num1 = beginDate.split("-");
            int day1 = Integer.parseInt(num1[0]);
            int month1 = Integer.parseInt(num1[1]);
            int year1 = Integer.parseInt(num1[2]);
            String[] num2 = endDate.split("-");
            int day2 = Integer.parseInt(num2[0]);
            int month2 = Integer.parseInt(num2[1]);
            int year2 = Integer.parseInt(num2[2]);

            // Kiểm tra ngày, tháng, năm hợp lệ
            if ((day1 >= 1 && day1 <= 30 && day2 >= 1 && day2 <= 30)
                    && (month1 >= 1 && month1 <= 12 && month2 >= 1 && month2 <= 12)
                    && (year1 >= 2000 && year1 <= 2024 && year2 >= 2000 && year2 <= 2024)) {
                // Kiểm tra logic ngày kết thúc phải lớn hơn ngày bắt đầu
                return (day2 > day1 || month2 > month1 || year2 > year1);
            }
        }
        return false;
    }

    /**
     * Kiểm tra học phí có hợp lệ (là số nguyên lớn hơn 0) hay không.
     * 
     * @param tuitionFee Học phí của Course.
     * @return boolean    Trả về true nếu học phí hợp lệ, ngược lại trả về false.
     */
    public boolean isValidTuitionFee(String tuitionFee) {
        int fee;
        try {
            fee = Integer.parseInt(tuitionFee);
            return fee > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Kiểm tra Topic có tồn tại trong danh sách Topic hay không.
     * 
     * @param topic       ID của Topic cần kiểm tra.
     * @param listOfTopic Danh sách Topic hiện tại.
     * @return boolean    Trả về true nếu Topic tồn tại, ngược lại trả về false.
     */
    public boolean isValidTopic(String topic, HashMap<String, Topic> listOfTopic) {
        boolean isExist = false;
        for (Map.Entry<String, Topic> entry : listOfTopic.entrySet()) {
            if (entry.getValue().getId().equals(topic)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }
}
