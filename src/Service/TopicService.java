package Service;

import Model.Topic;
import View.MenuView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class dùng để xử lý các task chính của Topic.
 * 
 * @author Huy
 */
public class TopicService {

    // Khởi tạo các đối tượng của các dịch vụ và view
    ValidationTopicService validationTopicService = new ValidationTopicService(); 
    InputService inputService = new InputService();
    SortService sortService = new SortService(); 
    MenuView view = new MenuView(); 

    /**
     * Phương thức thêm Topic vào danh sách.
     * 
     * @param listOfTopic danh sách Topic hiện có
     */
    public void addTopic(HashMap<String, Topic> listOfTopic) {
        // Lấy dữ liệu từ người dùng
        String id = inputService.inputString("Enter id: ");
        String name = inputService.inputString("Enter name: ");
        String type = inputService.inputString("Enter type: ");
        String title = inputService.inputString("Enter title: ");
        String duration = inputService.inputString("Enter duration: ");
        
        // Xác thực dữ liệu trước khi thêm
        if (validationTopicService.isValidDataToAddTopic(id, name, type, title, duration, listOfTopic)) {
            // Thêm Topic mới vào danh sách nếu hợp lệ
            listOfTopic.put(id, new Topic(id, name, type, title, duration));
            System.out.println("SUCCESS!");
        } else {
            System.out.println("FAILURE!");
        }
    }

    /**
     * Phương thức cập nhật Topic.
     * 
     * @param listOfTopic danh sách Topic hiện có
     * @param id ID của Topic cần cập nhật
     */
    public void updateTopic(HashMap<String, Topic> listOfTopic, String id) {
        // Lấy dữ liệu cập nhật từ người dùng
        String name = inputService.inputString("Enter new name: ");
        String type = inputService.inputString("Enter new type: ");
        String title = inputService.inputString("Enter new title: ");
        String duration = inputService.inputString("Enter new duration: ");

        // Kiểm tra và giữ lại giá trị cũ nếu người dùng không nhập giá trị mới
        name = (name.isEmpty()) ? listOfTopic.get(id).getName() : name;
        type = (type.isEmpty()) ? listOfTopic.get(id).getType() : type;
        title = (title.isEmpty()) ? listOfTopic.get(id).getTitle() : title;
        duration = (duration.isEmpty()) ? listOfTopic.get(id).getDuration() : duration;

        // Xác thực dữ liệu trước khi cập nhật
        if (validationTopicService.isValidDataToUpdate(name, type, title, duration)) {
            // Cập nhật Topic nếu dữ liệu hợp lệ
            listOfTopic.get(id).setName(name);
            listOfTopic.get(id).setType(type);
            listOfTopic.get(id).setTitle(title);
            listOfTopic.get(id).setDuration(duration);
            System.out.println("SUCCESS!");
        } else {
            System.out.println("FAILURE!");
        }
    }

    /**
     * Phương thức xóa Topic khỏi danh sách.
     * 
     * @param listOfTopic danh sách Topic hiện có
     * @param id ID của Topic cần xóa
     * @param selectDelete lựa chọn xóa từ người dùng
     */
    public void deleteTopic(HashMap<String, Topic> listOfTopic, String id, int selectDelete) {
        switch (selectDelete) {
            case 0: {
                System.out.println("SUCCESS!"); // Không thực hiện xóa, quay lại menu
                break;
            }
            case 1: {
                listOfTopic.remove(id); // Xóa Topic khỏi danh sách
                System.out.println("SUCCESS!");
                break;
            }
            default: {
                System.out.println("FAILURE!"); // Lựa chọn xóa không hợp lệ
            }
        }
    }

    /**
     * Phương thức hiển thị tất cả các Topic.
     * 
     * @param listOfTopic danh sách Topic hiện có
     */
    public void displayAllTopics(HashMap<String, Topic> listOfTopic) {
        List<Topic> list = new ArrayList<>(); // Tạo danh sách Topic để sắp xếp
        for (Map.Entry<String, Topic> entry : listOfTopic.entrySet()) {
            list.add(entry.getValue()); 
        }
        sortService.sortListTopic(list); // Sắp xếp danh sách Topic
        view.displayListTopics(list); // Hiển thị danh sách sau khi sắp xếp
    }
}
