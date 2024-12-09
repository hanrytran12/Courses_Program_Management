package View;

import Model.Course;
import Model.Learner;
import Model.Topic;
import java.util.List;

/**
 * Class dùng để in ra các giao diện của các task.
 *
 * @author Huy
 */
public class MenuView {

    /**
     * Hiển thị menu chính của chương trình quản lý khóa học.
     */
    public void displayMainMenu() {
        System.out.println("===================================================="
                + "\n=             COURSES PROGRAM MANAGEMENT           ="
                + "\n===================================================="
                + "\n=   1. MANAGE TOPIC                                ="
                + "\n=   2. MANAGE COURSE                               ="
                + "\n=   3. MANAGE LEARNER                              ="
                + "\n=   4. SEARCH INFORMATION                          ="
                + "\n=   5. SAVE TOPICS, COURSES AND LEARNERS TO FILE   ="
                + "\n=   OTHERS - QUIT                                  ="
                + "\n====================================================");
    }

    /**
     * Hiển thị menu quản lý các chủ đề (Topic).
     */
    public void displayTopicMenu() {
        System.out.println("============================================"
                + "\n=             MANAGE THE TOPIC             ="
                + "\n============================================"
                + "\n=   1. ADD TOPICS TO CATALOG               ="
                + "\n=   2. UPDATE TOPIC                        ="
                + "\n=   3. DELETE TOPIC                        ="
                + "\n=   4. DISPLAY ALL TOPICS                  ="
                + "\n============================================");
    }

    /**
     * Hiển thị menu quản lý các khóa học (Course).
     */
    public void displayCourseMenu() {
        System.out.println("============================================"
                + "\n=             MANAGE THE COURSE            ="
                + "\n============================================"
                + "\n=   1. ADD COURSE                          ="
                + "\n=   2. UPDATE COURSE                       ="
                + "\n=   3. DELETE COURSE                       ="
                + "\n=   4. DISPLAY COURSE INFORMATION          ="
                + "\n============================================");
    }

    /**
     * Hiển thị menu quản lý các học viên (Learner).
     */
    public void displayLearnerMenu() {
        System.out.println("============================================"
                + "\n=             MANAGE THE LEARNER           ="
                + "\n============================================"
                + "\n=   1. ADD LEARNER TO COURSE               ="
                + "\n=   2. ENTER SCORES FOR LEARNERS           ="
                + "\n=   3. DISPLAY LEARNER INFORMATION         ="
                + "\n============================================");
    }

    /**
     * Hiển thị menu tìm kiếm thông tin.
     */
    public void displaySearchMenu() {
        System.out.println("============================================"
                + "\n=             SEARCH INFORMATION           ="
                + "\n============================================"
                + "\n=   1. SEARCH TOPIC                        ="
                + "\n=   2. SEARCH COURSE                       ="
                + "\n============================================");
    }

    /**
     * Hiển thị menu tìm kiếm khóa học theo tiêu chí.
     */
    public void displaySearchCourse() {
        System.out.println("============================================"
                + "\n=               SEARCH BY                  ="
                + "\n============================================"
                + "\n=   1. BY TOPIC                            ="
                + "\n=   2. BY NAME                             ="
                + "\n============================================");
    }

    /**
     * Hiển thị thông báo xác nhận xóa một chủ đề hoặc mục nào đó.
     */
    public void displayConfirmDelete() {
        System.out.println("================================================"
                + "\n=  ARE YOU SURE WANT TO DELETE THIS RAM ITEM?  ="
                + "\n=        0: NO         |        1: YES         ="
                + "\n================================================");
    }

    /**
     * Hiển thị danh sách tất cả các chủ đề (Topic) đã được lưu.
     *
     * @param list danh sách các chủ đề
     */
    public void displayListTopics(List<Topic> list) {
        System.out.println("--------------------------------------------------------------------------\n"
                + "|                      TOPICS INFORMATION                                |\n"
                + "|------------------------------------------------------------------------|\n"
                + "|ID   |Name                |Type       |Title                 |Duration  |\n"
                + "|-----|--------------------|-----------|----------------------|----------|");
        for (Topic i : list) {
            System.out.println(i);
        }
        System.out.println("--------------------------------------------------------------------------\n");
    }

    /**
     * Hiển thị danh sách tất cả các khóa học (Course) đã được lưu.
     *
     * @param list danh sách các khóa học
     */
    public void displayListCourse(List<Course> list) {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"
                + "|                                                                COURSES INFORMATION                                                                               |\n"
                + "|------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n"
                + "|ID   |Name                  |Type    |Title                       |Begin Date |End Date   |Tuition Fee  |Topic        |Learners Passed |Learners Failed  | Income |\n"
                + "|-----|----------------------|--------|----------------------------|-----------|-----------|-------------|-------------|----------------|-----------------|--------|");
        for (Course i : list) {
            System.out.println(i);
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
    }

    /**
     * Hiển thị danh sách tất cả các học viên (Learner) đã được lưu.
     *
     * @param list danh sách các học viên
     */
    public void displayListLearner(List<Learner> list) {
        System.out.println("------------------------------------------------------------------------------------\n"
                + "|                             LEARNERS INFORMATION                                 |\n"
                + "|----------------------------------------------------------------------------------|\n"
                + "|ID   |Name                |Date of birth |Score|Course              |Status |GPA  |\n"
                + "|-----|--------------------|--------------|-----|--------------------|-------|-----|");
        for (Learner i : list) {
            System.out.println(i);
        }
        System.out.println("------------------------------------------------------------------------------------\n");
    }
}  
