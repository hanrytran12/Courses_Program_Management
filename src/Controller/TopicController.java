package Controller;

import Model.Topic;
import Service.FileService;
import Service.InputService;
import Service.TopicService;
import View.MenuView;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class TopicController quản lý các thao tác với danh sách Topic.
 *
 * @author Huy
 */
public class TopicController {

    public HashMap<String, Topic> listOfTopic; // Danh sách Topic, lưu trữ dưới dạng HashMap

    // Khởi tạo các đối tượng của các dịch vụ và view
    MenuView view = new MenuView();
    InputService inputService = new InputService();
    TopicService topicService = new TopicService();
    FileService fileService = new FileService();

    /**
     * Constructor mặc định của TopicController. 
     * - Khởi tạo danh sách Topic rỗng. 
     * - Tải danh sách Topic từ "Topic.txt".
     */
    public TopicController() throws IOException {
        listOfTopic = new HashMap<>(); // Khởi tạo HashMap để chứa Topic
        fileService.loadListOfTopic(this.listOfTopic); // Load dữ liệu từ file vào listOfTopic
    }

    /**
     * Quản lý các lựa chọn về Topic như thêm, sửa, xóa, hiển thị Topic.
     */
    public void manageTopic() {
        boolean isContinue = true;
        do {
            view.displayTopicMenu(); // Hiển thị menu cho người dùng chọn
            int select;
            try {
                select = inputService.inputInteger("Choose your select: "); // Lấy lựa chọn từ người dùng
                switch (select) {
                    case 1: {
                        addTopic(); // Thêm mới Topic
                        isContinue = false;
                        break;
                    }
                    case 2: {
                        updateTopic(); // Cập nhật thông tin Topic
                        isContinue = false;
                        break;
                    }
                    case 3: {
                        deleteTopic(); // Xóa Topic
                        isContinue = false;
                        break;
                    }
                    case 4: {
                        displayAllTopic(); // Hiển thị tất cả Topic
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
     * Thêm mới một Topic vào danh sách.
     */
    private void addTopic() {
        boolean isContinueAdd = true;
        do {
            topicService.addTopic(this.listOfTopic); // Gọi hàm thêm Topic từ TopicService
            isContinueAdd = inputService.inputString("Continue to add Topic? (Y/N)").equalsIgnoreCase("Y"); // Hỏi người dùng có muốn tiếp tục thêm không
        } while (isContinueAdd);
    }

    /**
     * Cập nhật thông tin của một Topic dựa trên ID.
     */
    private void updateTopic() {
        String id = inputService.inputString("Enter id: "); // Nhận ID từ người dùng
        if (isTopicExist(id)) {
            topicService.updateTopic(this.listOfTopic, id); // Cập nhật Topic
        }
    }

    /**
     * Xóa một Topic khỏi danh sách dựa trên ID.
     */
    private void deleteTopic() {
        String id = inputService.inputString("Enter id: "); // Nhận ID của Topic cần xóa
        if (isTopicExist(id)) {
            view.displayConfirmDelete(); // Hiển thị xác nhận xóa
            int selectDelete;
            try {
                selectDelete = inputService.inputInteger("Choose your select: "); // Nhận lựa chọn xác nhận xóa
                topicService.deleteTopic(this.listOfTopic, id, selectDelete); // Thực hiện xóa
            } catch (NumberFormatException e) {
                System.out.println("FAILURE!"); // Nếu nhập sai định dạng số
            }
        }
    }

    /**
     * Kiểm tra một Topic có tồn tại trong danh sách dựa trên ID hay không.
     *
     * @param id ID của Topic
     * @return true nếu tồn tại, false nếu không tồn tại
     */
    private boolean isTopicExist(String id) {
        if (this.listOfTopic.containsKey(id)) {
            return true; // Topic tồn tại
        } else {
            System.out.println("The topic does not exist."); // Topic không tồn tại
            return false;
        }
    }

    /**
     * Hiển thị tất cả các Topic có trong danh sách.
     */
    private void displayAllTopic() {
        topicService.displayAllTopics(this.listOfTopic); // Gọi hàm hiển thị từ TopicService
    }
}
