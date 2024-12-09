package Controller;

import Model.Course;
import Model.Learner;
import Service.FileService;
import Service.InputService;
import Service.LearnerService;
import View.MenuView;
import java.util.HashMap;
import java.util.Map;

/**
 * Class LearnerController quản lý các thao tác với Learner.
 *
 * @author Huy
 */
public class LearnerController {

    public HashMap<String, Learner> listOfLearner; // Danh sách Learner, lưu trữ dưới dạng HashMap
    private final HashMap<String, Course> listOfCourse; // Danh sách Course, được tham chiếu từ bên ngoài

    // Khởi tạo các đối tượng của các dịch vụ và view
    MenuView view = new MenuView();
    InputService inputService = new InputService();
    LearnerService learnerService = new LearnerService();
    FileService fileService = new FileService();

    /**
     * Constructor khởi tạo LearnerController với danh sách Course từ bên ngoài.
     * - Khởi tạo danh sách Learner rỗng. 
     * - Tải dữ liệu Learner từ "Learner.txt". 
     * - Cập nhật danh sách Course dựa trên danh sách Learner đã load.
     *
     * @param listOfCourse Danh sách Course
     */
    public LearnerController(HashMap<String, Course> listOfCourse) {
        this.listOfLearner = new HashMap<>(); // Khởi tạo danh sách Learner rỗng
        this.listOfCourse = listOfCourse; // Tham chiếu đến danh sách Course được truyền vào
        fileService.loadListOfLearner(this.listOfLearner); // Tải dữ liệu Learner từ file
        fileService.updateListOfCourse(this.listOfCourse, this.listOfLearner); // Cập nhật thông tin Course dựa trên Learner
    }

    /**
     * Quản lý các lựa chọn về Learner như thêm, nhập điểm, hiển thị Learner.
     */
    public void manageLearner() {
        boolean isContinue = true;
        do {
            view.displayLearnerMenu(); // Hiển thị menu cho người dùng chọn
            int select;
            try {
                select = inputService.inputInteger("Choose your select: "); // Lấy lựa chọn từ người dùng
                switch (select) {
                    case 1: {
                        addLearner(); // Thêm mới Learner
                        isContinue = false;
                        break;
                    }
                    case 2: {
                        enterScoreForLearner(); // Nhập điểm cho Learner
                        isContinue = false;
                        break;
                    }
                    case 3: {
                        displayLearner(); // Hiển thị danh sách Learner
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
     * Thêm một Learner mới vào danh sách Learner.
     */
    private void addLearner() {
        boolean isContinueAdd = true;
        do {
            learnerService.addLearner(this.listOfLearner, this.listOfCourse); // Gọi hàm thêm Learner từ LearnerService
            isContinueAdd = inputService.inputString("Continue to add Learner? (Y/N)").equalsIgnoreCase("Y"); // Hỏi người dùng có muốn tiếp tục thêm không
        } while (isContinueAdd);
    }

    /**
     * Nhập điểm cho một Learner dựa trên ID.
     */
    private void enterScoreForLearner() {
        String id = inputService.inputString("Enter id: "); // Nhận ID của Learner từ người dùng
        if (this.listOfLearner.containsKey(id)) {
            boolean isPassBefore = this.listOfLearner.get(id).isPassCourse(); // Lưu lại trạng thái pass trước khi nhập điểm
            learnerService.enterScore(id, this.listOfLearner); // Gọi hàm nhập điểm từ LearnerService
            updateListCourse(id, isPassBefore); // Cập nhật danh sách Course dựa trên kết quả điểm của Learner
        } else {
            System.out.println("The learner does not exist."); // Thông báo nếu Learner không tồn tại
        }
    }

    /**
     * Hiển thị danh sách Learner hiện có.
     */
    private void displayLearner() {
        learnerService.displayLearner(this.listOfLearner); // Gọi hàm hiển thị Learner từ LearnerService
    }

    /**
     * Cập nhật thông tin pass/fail trong Course dựa trên điểm của Learner. 
     * Nếu Learner thay đổi trạng thái pass/fail, cập nhật số lượng pass/fail trong Course tương ứng.
     *
     * @param id ID của Learner
     * @param isPassBefore Trạng thái pass của Learner trước khi cập nhật
     */
    private void updateListCourse(String id, boolean isPassBefore) {
        Learner learner = this.listOfLearner.get(id); // Lấy Learner từ danh sách Learner
        for (Map.Entry<String, Course> entry : this.listOfCourse.entrySet()) // Duyệt qua danh sách Course
        {
            if (entry.getValue().getId().equals(learner.getCourse())) {
                Course courseFind = entry.getValue(); // Lấy Course tương ứng
                if (isPassBefore && !learner.isPassCourse()) { // Nếu trước đó Learner pass nhưng giờ không pass nữa
                    courseFind.increaseFail();
                    courseFind.decreasePass();
                } else if (!isPassBefore && learner.isPassCourse()) { // Nếu trước đó Learner không pass nhưng giờ pass
                    courseFind.increasePass();
                    courseFind.decreaseFail();
                }
            }
        }
    }
}
