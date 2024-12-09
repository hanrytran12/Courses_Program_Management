package Service;

import Model.Course;
import Model.Learner;
import java.util.HashMap;
import java.util.Map;

/**
 * Class dùng để kiểm tra tính hợp lệ của các thuộc tính Learner.
 * 
 * @author Huy
 */
public class ValidationLearnerService {

    /**
     * Kiểm tra xem dữ liệu có hợp lệ để thêm vào danh sách learners hay không.
     * 
     * @param id ID của learner cần kiểm tra
     * @param name Tên của learner cần kiểm tra
     * @param dateOfBirth Ngày sinh của learner cần kiểm tra
     * @param score Điểm số của learner cần kiểm tra
     * @param course ID của course mà learner đăng ký
     * @param listOfLearner Danh sách learners hiện tại
     * @param listOfCourse Danh sách courses hiện tại
     * @return true nếu tất cả các thuộc tính hợp lệ, false nếu không
     */
    boolean isValidDataToAdd(String id, String name, String dateOfBirth, String score, String course, HashMap<String, Learner> listOfLearner, HashMap<String, Course> listOfCourse) {
        return isValidId(id, listOfLearner) && 
               isValidName(name) && 
               isValidDateOfBirth(dateOfBirth) && 
               isValidScore(score) && 
               isValidCourse(course, listOfCourse, score);
    }

    /**
     * Kiểm tra tính hợp lệ của ID.
     * 
     * @param id ID cần kiểm tra
     * @param listOfLearner Danh sách learners hiện tại
     * @return true nếu ID không trống và chưa tồn tại trong danh sách learners, false nếu không
     */
    public boolean isValidId(String id, HashMap<String, Learner> listOfLearner) {
        return !id.isEmpty() && !listOfLearner.containsKey(id);
    }

    /**
     * Kiểm tra tính hợp lệ của tên learner.
     * 
     * @param name Tên cần kiểm tra
     * @return true nếu tên không trống, false nếu trống
     */
    public boolean isValidName(String name) {
        return !name.isEmpty();
    }

    /**
     * Kiểm tra tính hợp lệ của ngày sinh.
     * 
     * @param dateOfBirth Ngày sinh cần kiểm tra theo định dạng dd-MM-yyyy
     * @return true nếu ngày sinh hợp lệ, false nếu không
     */
    public boolean isValidDateOfBirth(String dateOfBirth) {
        if (dateOfBirth.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
            String[] num = dateOfBirth.split("-");
            int day = Integer.parseInt(num[0]);
            int month = Integer.parseInt(num[1]);
            int year = Integer.parseInt(num[2]);
            // Kiểm tra ngày, tháng và năm
            return (day >= 1 && day <= 30) && (month >= 1 && month <= 12) && (year >= 1990 && year <= 2024);
        }
        return false;
    }

    /**
     * Kiểm tra tính hợp lệ của điểm số.
     * 
     * @param score Điểm cần kiểm tra
     * @return true nếu điểm nằm trong khoảng từ 1 đến 100, false nếu không
     */
    public boolean isValidScore(String score) {
        int num;
        try {
            num = Integer.parseInt(score);
            return (num >= 1 && num <= 100);
        } catch (NumberFormatException e) {
            return false; // Trả về false nếu không thể chuyển đổi điểm thành số
        }
    }

    /**
     * Kiểm tra xem khóa học có tồn tại trong danh sách hay không
     * và cập nhật số lượng learner đã đậu hoặc trượt.
     * 
     * @param course ID của course cần kiểm tra
     * @param listOfCourse Danh sách courses hiện tại
     * @param score Điểm số của learner
     * @return true nếu khóa học tồn tại, false nếu không
     */
    public boolean isValidCourse(String course, HashMap<String, Course> listOfCourse, String score) {
        boolean isExist = false;
        Course courseInList = null;
        int numScore = Integer.parseInt(score);
        // Kiểm tra từng course trong danh sách
        for (Map.Entry<String, Course> entry : listOfCourse.entrySet()) {  
            if (entry.getValue().getId().equals(course)) {             
                isExist = true;
                courseInList = entry.getValue();
                // Cập nhật số lượng learner đã đậu hoặc trượt
                if(numScore >= 60)
                    courseInList.increasePass();
                else
                    courseInList.increaseFail();
                break;
            }
        }
        return isExist; // Trả về true nếu khóa học tồn tại
    }
}
