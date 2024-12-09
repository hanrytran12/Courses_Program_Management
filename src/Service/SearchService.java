package Service;

import Model.Course;
import Model.Topic;
import View.MenuView;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class xử lý các nghiệp vụ tìm kiếm thông tin cho Topic và Course.
 * 
 * @author Huy
 */
public class SearchService {

    // Khởi tạo đối tượng MenuView và InputService để hỗ trợ giao diện và nhập dữ liệu
    MenuView view = new MenuView();
    InputService inputService = new InputService();

    /**
     * Tìm kiếm các Topic dựa trên tên được cung cấp.
     * Phương thức sẽ trả về danh sách các Topic có tên chứa chuỗi tìm kiếm.
     * 
     * @param name Chuỗi tên cần tìm kiếm trong danh sách Topic
     * @param listOfTopic Danh sách Topic hiện có (HashMap chứa các đối tượng Topic)
     */
    public void searchListOfTopic(String name, HashMap<String, Topic> listOfTopic) {
        // Tìm các Topic có tên chứa chuỗi tìm kiếm (không phân biệt hoa thường)
        List<Topic> listTopicFound = listOfTopic.values().stream()
            .filter(topic -> topic.getName().toLowerCase().contains(name.toLowerCase()))
            .collect(Collectors.toList());

        // Hiển thị danh sách kết quả hoặc thông báo nếu không tìm thấy
        if (!listTopicFound.isEmpty()) {
            view.displayListTopics(listTopicFound);
        } else {
            System.out.println("NO TOPIC FOUND!");
        }
    }

    /**
     * Tìm kiếm các Course dựa trên TopicID.
     * Phương thức sẽ trả về danh sách các Course thuộc về Topic có ID tương ứng.
     * 
     * @param listOfCourse Danh sách Course hiện có (HashMap chứa các đối tượng Course)
     */
    public void searchCourseByTopicID(HashMap<String, Course> listOfCourse) {
        // Nhập TopicID để tìm kiếm Course
        String idTopic = inputService.inputString("Enter idTopic: ");
        
        // Tìm các Course có TopicID tương ứng
        List<Course> listCourseFound = listOfCourse.values().stream()
            .filter(course -> course.getTopic().equals(idTopic))
            .collect(Collectors.toList());

        // Hiển thị danh sách kết quả hoặc thông báo nếu không tìm thấy
        if (!listCourseFound.isEmpty()) {
            view.displayListCourse(listCourseFound);
        } else {
            System.out.println("NO COURSE FOUND!");
        }
    }

    /**
     * Tìm kiếm các Course dựa trên tên được cung cấp.
     * Phương thức sẽ trả về danh sách các Course có tên chứa chuỗi tìm kiếm.
     * 
     * @param listOfCourse Danh sách Course hiện có (HashMap chứa các đối tượng Course)
     */
    public void searchCourseByName(HashMap<String, Course> listOfCourse) {
        // Nhập tên để tìm kiếm Course
        String name = inputService.inputString("Enter name: ");
        if(name.isEmpty())
            System.out.println("NO COURSE FOUND!");
        else{
            // Tìm các Course có tên chứa chuỗi tìm kiếm (không phân biệt hoa thường)
            List<Course> listCourseFound = listOfCourse.values().stream()
                .filter(course -> course.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());

            // Hiển thị danh sách kết quả hoặc thông báo nếu không tìm thấy
            if (!listCourseFound.isEmpty()) {
                view.displayListCourse(listCourseFound);
            } else {
                System.out.println("NO COURSE FOUND!");
            }
        }
    }
}
