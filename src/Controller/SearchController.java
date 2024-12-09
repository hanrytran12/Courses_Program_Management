package Controller;

import Model.Course;
import Model.Topic;
import Service.InputService;
import Service.SearchService;
import View.MenuView;
import java.util.HashMap;

/**
 * Class dùng để controll search Topic - Course.
 *
 * @author Huy
 */
public class SearchController {

    // Lưu trữ danh sách các Topic để thực hiện tìm kiếm
    private final HashMap<String, Topic> listOfTopic;

    // Lưu trữ danh sách các Course để thực hiện tìm kiếm
    private final HashMap<String, Course> listOfCourse;

    // Khởi tạo các đối tượng cần thiết để giao tiếp với người dùng và xử lý logic
    MenuView view = new MenuView();
    InputService inputService = new InputService();
    SearchService searchService = new SearchService();

    /**
     * Constructor khởi tạo SearchController với danh sách Topic, Course từ bên ngoài.
     *
     * @param listOfTopic Danh sách Topic
     * @param listOfCourse Danh sách Course
     */
    public SearchController(HashMap<String, Topic> listOfTopic, HashMap<String, Course> listOfCourse) {
        this.listOfTopic = listOfTopic;
        this.listOfCourse = listOfCourse;
    }

    // Phương thức quản lý tìm kiếm chính, hiển thị menu và điều hướng dựa trên lựa chọn của người dùng
    public void manageSearch() {
        boolean isContinue = true;
        do {
            // Hiển thị menu tìm kiếm cho người dùng
            view.displaySearchMenu();
            try {
                // Lấy lựa chọn của người dùng
                int select = inputService.inputInteger("Choose your select: ");
                switch (select) {
                    case 1: {
                        // Tìm kiếm Topic
                        searchTopic();
                        isContinue = false;
                        break;
                    }
                    case 2: {
                        // Tìm kiếm Course
                        searchCourse();
                        isContinue = false;
                        break;
                    }
                    default: {
                        // Thông báo nếu lựa chọn không hợp lệ
                        System.out.println("WRONG SELECT!");
                    }
                }
            } catch (NumberFormatException e) {
                // Bắt lỗi nếu người dùng nhập không phải số
                System.out.println("INVALID SELECT！");
            }
        } while (isContinue); // Lặp lại cho đến khi có lựa chọn hợp lệ
    }

    // Tìm kiếm Topic dựa trên tên được nhập bởi người dùng
    private void searchTopic() {
        String name = inputService.inputString("Enter name: "); // Nhập tên topic cần tìm
        if (name.isEmpty()) {
            // Nếu không nhập tên, thông báo không tìm thấy
            System.out.println("NO TOPIC FOUND!");
        } else {
            // Gọi SearchService để thực hiện tìm kiếm
            searchService.searchListOfTopic(name, listOfTopic);
        }
    }

    // Tìm kiếm Course dựa trên hai lựa chọn: theo TopicID hoặc theo tên Course
    private void searchCourse() {
        boolean isContinue = true;
        do {
            view.displaySearchCourse(); // Hiển thị menu tìm kiếm course
            int select;
            try {
                // Lấy lựa chọn tìm kiếm từ người dùng
                select = inputService.inputInteger("Choose your select: ");
                switch (select) {
                    case 1: {
                        // Tìm kiếm Course theo Topic ID
                        searchService.searchCourseByTopicID(listOfCourse);
                        isContinue = false;
                        break;
                    }
                    case 2: {
                        // Tìm kiếm Course theo tên
                        searchService.searchCourseByName(listOfCourse);
                        isContinue = false;
                        break;
                    }
                    default: {
                        // Thông báo nếu lựa chọn không hợp lệ
                        System.out.println("WRONG SELECT!");
                    }
                }
            } catch (NumberFormatException e) {
                // Bắt lỗi nếu người dùng nhập không phải số
                System.out.println("INVALID SELECT!");
            }
        } while (isContinue); // Lặp lại cho đến khi có lựa chọn hợp lệ
    }
}
