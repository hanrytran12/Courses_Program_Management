package Controller;

import Model.Course;
import Model.Topic;
import Service.CourseService;
import Service.FileService;
import Service.InputService;
import View.MenuView;
import java.util.HashMap;

/**
 * Class CourseController quản lý các hoạt động liên quan đến Course
 *
 * @author Huy
 */
public class CourseController {

    public HashMap<String, Course> listOfCourse; // Danh sách Course, lưu trữ dưới dạng HashMap
    private final HashMap<String, Topic> listOfTopic; // Danh sách Topic từ TopicController, dùng để liên kết với Course

    // Khởi tạo các đối tượng của các dịch vụ và view
    MenuView view = new MenuView();
    InputService inputService = new InputService();
    CourseService courseService = new CourseService();
    FileService fileService = new FileService();

    /**
     * Constructor khởi tạo CourseController với danh sách Topic từ bên ngoài. 
     * - Khởi tạo danh sách Course rỗng. 
     * - Tải dữ liệu Course từ "Course.txt".
     *
     * @param listOfTopic Danh sách Topic
     */
    public CourseController(HashMap<String, Topic> listOfTopic) {
        this.listOfCourse = new HashMap<>(); // Khởi tạo HashMap rỗng để chứa danh sách Course
        this.listOfTopic = listOfTopic; // Gán danh sách Topic được truyền từ TopicController
        fileService.loadListofCourse(this.listOfCourse); // Tải dữ liệu Course từ file vào HashMap
    }

    // Quản lý các hoạt động của Course thông qua các lựa chọn từ menu
    public void manageCourse() {
        boolean isContinue = true;
        do {
            view.displayCourseMenu(); // Hiển thị menu các thao tác với Course
            int select;
            try {
                select = inputService.inputInteger("Choose your select: "); // Nhập lựa chọn của người dùng
                switch (select) {
                    case 1: {
                        addCourse(); // Thêm mới Course
                        isContinue = false;
                        break;
                    }
                    case 2: {
                        updateCourse(); // Cật nhật Course
                        isContinue = false;
                        break;
                    }
                    case 3: {
                        deleteCourse(); // Xóa Course
                        isContinue = false;
                        break;
                    }
                    case 4: {
                        displayAllCourse(); // Hiển thị tất cả các Course
                        isContinue = false;
                        break;
                    }
                    default: {
                        System.out.println("WRONG SELECT! PLEASE TRY AGAIN!"); // Lựa chọn sai
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("INVALID SELECT! PLEASE TRY AGAIN!"); // Lỗi nhập số không đúng định dạng
            }
        } while (isContinue);
    }

    /**
     * Thêm một Course mới vào danh sách Course.
     */
    private void addCourse() {
        boolean isContinueAdd = true;
        do {
            courseService.addCourse(this.listOfCourse, this.listOfTopic); // Gọi hàm thêm Course từ CourseService
            isContinueAdd = inputService.inputString("Continue to add Course? (Y/N)").equalsIgnoreCase("Y"); // Hỏi người dùng có muốn tiếp tục thêm Course không
        } while (isContinueAdd);
    }

    /**
     * Cập nhật thông tin của một Course dựa trên ID.
     */
    private void updateCourse() {
        String id = inputService.inputString("Enter id: "); // Nhập id của Course cần cập nhật
        if (isCourseExist(id)) {
            courseService.updateCourse(this.listOfCourse, this.listOfTopic, id); // Gọi hàm cập nhật Course từ CourseService
        }
    }

    /**
     * Xóa một Course khỏi danh sách dựa trên ID.
     */
    private void deleteCourse() {
        String id = inputService.inputString("Enter id: "); // Nhập id của Course cần xóa
        if (isCourseExist(id)) {
            view.displayConfirmDelete(); // Hiển thị xác nhận xóa từ người dùng
            int selectDelete;
            try {
                selectDelete = inputService.inputInteger("Choose your select: "); // Nhập lựa chọn từ người dùng
                courseService.deleteCourse(this.listOfCourse, id, selectDelete); // Gọi hàm xóa Course từ CourseService
            } catch (NumberFormatException e) {
                System.out.println("FAILURE!"); // Nếu nhập sai định dạng số
            }
        }
    }

    /**
     * Hiển thị danh sách Course hiện có.
     */
    private void displayAllCourse() {
        courseService.displayAllCourse(listOfCourse);
    }

    /**
     * Kiểm tra một Course có tồn tại trong danh sách dựa trên ID hay không.
     *
     * @param id ID của Course
     * @return true nếu tồn tại, false nếu không tồn tại
     */
    private boolean isCourseExist(String id) {
        if (!this.listOfCourse.containsKey(id)) {
            System.out.println("The course does not exist."); // Thông báo lỗi
            return false;
        }
        return true; // Trả về true nếu Course tồn tại
    }
}
