package Main;

import Controller.CourseController;
import Controller.LearnerController;
import Controller.SearchController;
import Controller.TopicController;
import Service.FileService;
import Service.InputService;
import View.MenuView;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // Khởi tạo các đối tượng cần thiết
        MenuView view = new MenuView(); // Hiển thị các menu chính của chương trình
        FileService fileService = new FileService(); // Service dùng để load và lưu file dữ liệu

        // Khởi tạo TopicController để quản lý các Topic
        TopicController controllT = new TopicController();

        // Khởi tạo CourseController, truyền danh sách Topic từ TopicController vào
        CourseController controllC = new CourseController(controllT.listOfTopic);

        // Khởi tạo LearnerController, truyền danh sách Course từ CourseController vào
        LearnerController controllL = new LearnerController(controllC.listOfCourse);
        
        InputService inputService = new InputService(); // Service nhập liệu từ người dùng
        boolean isContinue = true;

        // Vòng lặp chính của chương trình
        do {
            view.displayMainMenu(); // Hiển thị menu chính
            int select = 0;
            try {
                // Nhập lựa chọn của người dùng
                select = inputService.inputInteger("Choose your select: ");
            } catch (NumberFormatException e) {
                // Xử lý ngoại lệ khi nhập sai kiểu dữ liệu
                System.out.println("QUIT!"); // Thông báo thoát chương trình
                isContinue = false; 
            }
            if (isContinue) {
                switch (select) {
                    case 1: {
                        // Gọi chức năng quản lý Topic
                        controllT.manageTopic();
                        isContinue = true; 
                        break;
                    }
                    case 2: {
                        // Gọi chức năng quản lý Course
                        controllC.manageCourse();
                        isContinue = true;
                        break;
                    }
                    case 3: {
                        // Gọi chức năng quản lý Learner
                        controllL.manageLearner();
                        isContinue = true; 
                        break;
                    }
                    case 4: {
                        // Gọi chức năng tìm kiếm Course và Topic
                        SearchController searchController = new SearchController(controllT.listOfTopic, controllC.listOfCourse);
                        searchController.manageSearch(); // Thực hiện tìm kiếm
                        isContinue = true; 
                        break;
                    }
                    case 5: {
                        // Lưu danh sách Topic, Course, và Learner vào file
                        fileService.storeListOfTopic(controllT.listOfTopic);
                        fileService.storeListOfCourse(controllC.listOfCourse);
                        fileService.storeListOfLearner(controllL.listOfLearner);
                        isContinue = true; 
                        break;
                    }
                    default: {
                        System.out.println("QUIT!");
                        isContinue = false;
                    }
                }
            }
        } while (isContinue); 
    }
}
