package Service;

import Model.Course;
import Model.Topic;
import View.MenuView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class dùng để xử lý các task chính của Course.
 * 
 * @author Huy
 */
public class CourseService {

    // Khởi tạo các đối tượng của các dịch vụ và view
    InputService inputService = new InputService(); 
    ValidationCourseService validationCourseService = new ValidationCourseService(); 
    SortService sortService = new SortService();
    MenuView view = new MenuView();

    /**
     * Phương thức thêm Course vào danh sách.
     * 
     * @param listOfCourse Danh sách các Course hiện tại
     * @param listOfTopic Danh sách các Topic hiện tại
     */
    public void addCourse(HashMap<String, Course> listOfCourse, HashMap<String, Topic> listOfTopic) {
        // Lấy dữ liệu từ người dùng
        String id = inputService.inputString("Enter id: ");
        String name = inputService.inputString("Enter name: ");
        String type = inputService.inputString("Enter type: ");
        String title = inputService.inputString("Enter title: ");
        String beginDate = inputService.inputString("Enter begin date: ");
        String endDate = inputService.inputString("Enter end date: ");
        String tuitionFee = inputService.inputString("Enter tuition fee: ");
        String topic = inputService.inputString("Enter topic: ");
        
        // Xác thực dữ liệu trước khi thêm
        if (validationCourseService.isValidDataToAddCourse(id, name, type, title, beginDate, endDate, tuitionFee, topic, listOfCourse, listOfTopic)) {
            // Thêm Course mới vào danh sách nếu hợp lệ
            listOfCourse.put(id, new Course(id, name, type, title, beginDate, endDate, tuitionFee, topic));
            System.out.println("SUCCESS!");
        } else {
            System.out.println("FAILURE!");
        }
    }

    /**
     * Phương thức cập nhật thông tin của một Course trong danh sách.
     * 
     * @param listOfCourse Danh sách các Course hiện tại
     * @param listOfTopic Danh sách các Topic hiện tại
     * @param id ID của Course cần cập nhật
     */
    public void updateCourse(HashMap<String, Course> listOfCourse, HashMap<String, Topic> listOfTopic, String id) {
        // Lấy dữ liệu cập nhật từ người dùng
        String name = inputService.inputString("Enter name: ");
        String type = inputService.inputString("Enter type: ");
        String title = inputService.inputString("Enter title: ");
        String beginDate = inputService.inputString("Enter begin date: ");
        String endDate = inputService.inputString("Enter end date: ");
        String tuitionFee = inputService.inputString("Enter tuition fee: ");
        String topic = inputService.inputString("Enter topic: ");

        // Kiểm tra và giữ lại giá trị cũ nếu người dùng không nhập giá trị mới
        name = (name.isEmpty()) ? listOfCourse.get(id).getName() : name;
        type = (type.isEmpty()) ? listOfCourse.get(id).getType() : type;
        title = (title.isEmpty()) ? listOfCourse.get(id).getTitle() : title;
        beginDate = (beginDate.isEmpty()) ? listOfCourse.get(id).getBeginDate() : beginDate;
        endDate = (endDate.isEmpty()) ? listOfCourse.get(id).getEndDate(): endDate;
        tuitionFee = (tuitionFee.isEmpty()) ? listOfCourse.get(id).getTuitionFee(): tuitionFee;
        topic = (topic.isEmpty()) ? listOfCourse.get(id).getTopic(): topic;

        // Xác thực dữ liệu trước khi cập nhật
        if (validationCourseService.isValidDataToUpdate(name, type, title, beginDate, endDate, tuitionFee, topic, listOfCourse, listOfTopic)) {
            // Cập nhật Course nếu dữ liệu hợp lệ
            listOfCourse.get(id).setName(name);
            listOfCourse.get(id).setType(type);
            listOfCourse.get(id).setTitle(title);
            listOfCourse.get(id).setBeginDate(beginDate);
            listOfCourse.get(id).setEndDate(endDate);
            listOfCourse.get(id).setTuitionFee(tuitionFee);
            listOfCourse.get(id).setTopic(topic);
            System.out.println("SUCCESS!");
        } else {
            System.out.println("FAILURE!");
        }
    }

    /**
     * Phương thức xóa Course khỏi danh sách.
     * 
     * @param listOfCourse Danh sách các Course hiện tại
     * @param id ID của Course cần xóa
     * @param selectDelete Lựa chọn của người dùng để xóa
     */
    public void deleteCourse(HashMap<String, Course> listOfCourse, String id, int selectDelete) {
        switch (selectDelete) {
            case 0: {
                System.out.println("SUCCESS!"); // Không thực hiện xóa, quay lại menu
                break;
            }
            case 1: {
                listOfCourse.remove(id); // Xóa Course khỏi danh sách
                System.out.println("SUCCESS!");
                break;
            }
            default: {
                System.out.println("FAILURE!"); // Lựa chọn xóa không hợp lệ
            }
        }
    }
    
    /**
     * Phương thức hiển thị danh sách tất cả các Course.
     * 
     * @param listOfCourse Danh sách các Course hiện tại
     */
    public void displayAllCourse(HashMap<String, Course> listOfCourse) {
        List<Course> list = new ArrayList<>(); // Tạo danh sách Course để sắp xếp
        for (Map.Entry<String, Course> entry : listOfCourse.entrySet()) {
            list.add(entry.getValue()); 
        }
        sortService.sortListCourse(list); // Sắp xếp danh sách Course
        view.displayListCourse(list); // Hiển thị danh sách sau khi sắp xếp
    }

}
