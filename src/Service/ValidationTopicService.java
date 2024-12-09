package Service;

import Model.Topic;
import java.util.HashMap;

/**
 * Class dùng để kiểm tra tính hợp lệ của các thuộc tính Topic.
 * 
 * @author Huy
 */
public class ValidationTopicService {

    /**
     * Kiểm tra xem dữ liệu có hợp lệ để thêm Topic vào danh sách hay không.
     * 
     * @param id ID của topic cần kiểm tra
     * @param name Tên của topic cần kiểm tra
     * @param type Loại của topic cần kiểm tra (Long term hoặc Short term)
     * @param title Tiêu đề của topic cần kiểm tra
     * @param duration Thời gian của topic cần kiểm tra
     * @param listOfTopic Danh sách topic hiện tại
     * @return true nếu tất cả các thuộc tính hợp lệ, false nếu không
     */
    public boolean isValidDataToAddTopic(String id, String name, String type, String title, String duration, HashMap<String, Topic> listOfTopic) {
        return isValidId(id, listOfTopic) && 
               isValidName(name) && 
               isValidType(type) && 
               isValidTitle(title) && 
               isValidDuration(duration);
    }

    /**
     * Kiểm tra xem dữ liệu có hợp lệ để cập nhật Topic hay không.
     * 
     * @param name Tên của topic cần kiểm tra
     * @param type Loại của topic cần kiểm tra (Long term hoặc Short term)
     * @param title Tiêu đề của topic cần kiểm tra
     * @param duration Thời gian của topic cần kiểm tra
     * @return true nếu tất cả các thuộc tính hợp lệ, false nếu không
     */
    public boolean isValidDataToUpdate(String name, String type, String title, String duration) {
        return isValidName(name) && 
               isValidType(type) && 
               isValidTitle(title) && 
               isValidDuration(duration);
    }

    /**
     * Kiểm tra tính hợp lệ của ID.
     * 
     * @param id ID cần kiểm tra
     * @param listOfTopic Danh sách topic hiện tại
     * @return true nếu ID không trống và chưa tồn tại trong danh sách topic, false nếu không
     */
    public boolean isValidId(String id, HashMap<String, Topic> listOfTopic) {
        return !id.isEmpty() && !listOfTopic.containsKey(id);
    }

    /**
     * Kiểm tra tính hợp lệ của tên topic.
     * 
     * @param name Tên cần kiểm tra
     * @return true nếu tên không trống, false nếu trống
     */
    public boolean isValidName(String name) {
        return !name.isEmpty();
    }

    /**
     * Kiểm tra tính hợp lệ của loại topic.
     * 
     * @param type Loại cần kiểm tra
     * @return true nếu loại hợp lệ (Long term hoặc Short term), false nếu không
     */
    public boolean isValidType(String type) {
        return type.matches("(Long|Short) term");
    }

    /**
     * Kiểm tra tính hợp lệ của tiêu đề topic.
     * 
     * @param title Tiêu đề cần kiểm tra
     * @return true nếu tiêu đề không trống, false nếu trống
     */
    public boolean isValidTitle(String title) {
        return !title.isEmpty();
    }

    /**
     * Kiểm tra tính hợp lệ của thời gian.
     * 
     * @param duration Thời gian cần kiểm tra
     * @return true nếu thời gian là một số dương, false nếu không
     */
    public boolean isValidDuration(String duration) {
        int num;
        try {
            num = Integer.parseInt(duration);
            return (num > 0); // Kiểm tra thời gian phải lớn hơn 0
        } catch (NumberFormatException e) {
            return false; // Trả về false nếu không thể chuyển đổi thời gian thành số
        }
    }
}
