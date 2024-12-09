package Service;

import Model.Course;
import Model.Learner;
import View.MenuView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class dùng để xử lý các task chính của Learner.
 * 
 * @author Huy
 */
public class LearnerService {
    
    // Khởi tạo các đối tượng của các dịch vụ và view
    InputService inputService = new InputService(); 
    ValidationLearnerService validationLearnerService = new ValidationLearnerService(); 
    SortService sortService = new SortService(); 
    MenuView view = new MenuView(); 

    /**
     * Phương thức thêm Learner vào danh sách.
     * 
     * @param listOfLearner danh sách Learner hiện có
     * @param listOfCourse danh sách Course để xác thực thông tin Learner
     */
    public void addLearner(HashMap<String, Learner> listOfLearner, HashMap<String, Course> listOfCourse) {
        // Lấy dữ liệu từ người dùng
        String id = inputService.inputString("Enter id: ");
        String name = inputService.inputString("Enter name: ");
        String dateOfBirth = inputService.inputString("Enter dateOfBirth: ");
        String score = inputService.inputString("Enter score: ");
        String course = inputService.inputString("Enter course: ");

        // Xác thực dữ liệu đầu vào trước khi thêm Learner mới
        if(validationLearnerService.isValidDataToAdd(id, name, dateOfBirth, score, course, listOfLearner, listOfCourse)) {
            // Thêm Learner vào danh sách nếu dữ liệu hợp lệ
            listOfLearner.put(id, new Learner(id, name, dateOfBirth, Integer.parseInt(score), course));
            System.out.println("SUCCESS!");
        } else {
            System.out.println("FAILURE!");
        }
    }
    
    /**
     * Phương thức nhập điểm cho Learner.
     * 
     * @param id ID của Learner mà điểm cần cập nhật
     * @param listOfLearner danh sách Learner hiện có
     */
    public void enterScore(String id, HashMap<String, Learner> listOfLearner) {
        // Lấy điểm mới từ người dùng
        String score = inputService.inputString("Enter new score: ");

        // Kiểm tra tính hợp lệ của điểm và cập nhật nếu hợp lệ
        if(validationLearnerService.isValidScore(score)) {
            listOfLearner.get(id).setScore(Integer.parseInt(score));
            System.out.println("SUCCESS!");
        } else {
            System.out.println("FAILURE!");
        }
    }

    /**
     * Phương thức hiển thị danh sách tất cả Learner.
     * 
     * @param listOfLearner danh sách Learner hiện có
     */
    public void displayLearner(HashMap<String, Learner> listOfLearner) {
        List<Learner> list = new ArrayList<>(); // Tạo danh sách Learner để sắp xếp
        for (Map.Entry<String, Learner> entry : listOfLearner.entrySet()) {
            list.add(entry.getValue());
        }
        sortService.sortListLearner(list);  // Sắp xếp danh sách Learner
        view.displayListLearner(list);      // Hiển thị danh sách Learner ra giao diện
    }
}
