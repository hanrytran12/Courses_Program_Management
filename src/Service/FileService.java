package Service;

import Model.Course;
import Model.Learner;
import Model.Topic;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class dùng để load/save dữ liệu của các đối tượng (Topic, Course, Learner) từ/đến file.
 * 
 * @author Huy
 */
public class FileService {

    /**
     * Phương thức loadListOfTopic
     * Mục đích: Load dữ liệu của các Topic từ file "Topic.txt" vào HashMap listOfTopic.
     * @param listOfTopic Danh sách HashMap chứa các Topic.
     */
    public void loadListOfTopic(HashMap<String, Topic> listOfTopic){
        try (BufferedReader reader = new BufferedReader(new FileReader("Topic.txt"))) {
            String line;
            // Đọc từng dòng trong file và phân tách dữ liệu theo định dạng của Topic
            while ((line = reader.readLine()) != null) {
                String[] data = line.trim().split(", ");
                listOfTopic.put(data[0], new Topic(data[0], data[1], data[2], data[3], data[4]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Phương thức storeListOfTopic
     * Mục đích: Lưu trữ danh sách Topic từ HashMap listOfTopic vào file "Topic.txt".
     * @param listOfTopic Danh sách HashMap chứa các Topic..
     */
    public void storeListOfTopic(HashMap<String, Topic> listOfTopic){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Topic.txt"))) {
            // Duyệt qua từng Topic trong HashMap và ghi vào file
            for (Map.Entry<String, Topic> entry : listOfTopic.entrySet()) {
                writer.write(entry.getValue().convertString()); // Chuyển đổi Topic sang dạng chuỗi để ghi vào file
                writer.newLine(); // Xuống dòng sau mỗi Topic
            }
            System.out.println("SUCCESS!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Phương thức loadListOfCourse
     * Mục đích: Load dữ liệu của các Course từ file "Course.txt" vào HashMap listOfCourse.
     * @param listOfCourse Danh sách HashMap chứa các Course.
     */
    public void loadListofCourse(HashMap<String, Course> listOfCourse) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Course.txt"))) {
            String line;
            // Đọc từng dòng trong file và phân tách dữ liệu theo định dạng của Course
            while ((line = reader.readLine()) != null) {
                String[] data = line.trim().split(", ");
                listOfCourse.put(data[0], new Course(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Phương thức storeListOfCourse
     * Mục đích: Lưu trữ danh sách Course từ HashMap listOfCourse vào file "Course.txt".
     * @param listOfCourse Danh sách HashMap chứa các Course.
     */
    public void storeListOfCourse(HashMap<String, Course> listOfCourse){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Course.txt"))) {
            // Duyệt qua từng Course trong HashMap và ghi vào file
            for (Map.Entry<String, Course> entry : listOfCourse.entrySet()) {
                writer.write(entry.getValue().convertString()); // Chuyển đổi Course sang dạng chuỗi để ghi vào file
                writer.newLine(); // Xuống dòng sau mỗi Course
            }
            System.out.println("SUCCESS!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Phương thức loadListOfLearner
     * Mục đích: Load dữ liệu của các Learner từ file "Learner.txt" vào HashMap listOfLearner.
     * @param listOfLearner Danh sách HashMap chứa các Learner.
     */
    public void loadListOfLearner(HashMap<String, Learner> listOfLearner) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Learner.txt"))) {
            String line;
            // Đọc từng dòng trong file và phân tách dữ liệu theo định dạng của Learner
            while ((line = reader.readLine()) != null) {
                String[] data = line.trim().split(", ");
                listOfLearner.put(data[0], new Learner(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Phương thức storeListOfLearner
     * Mục đích: Lưu trữ danh sách Learner từ HashMap listOfLearner vào file "Learner.txt".
     * @param listOfLearner Danh sách HashMap chứa các Learner.
     */
    public void storeListOfLearner(HashMap<String, Learner> listOfLearner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Learner.txt"))) {
            // Duyệt qua từng Learner trong HashMap và ghi vào file
            for (Map.Entry<String, Learner> entry : listOfLearner.entrySet()) {
                writer.write(entry.getValue().convertString()); // Chuyển đổi Learner sang dạng chuỗi để ghi vào file
                writer.newLine(); // Xuống dòng sau mỗi Learner
            }
            System.out.println("SUCCESS!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Phương thức updateListOfCourse
     * Mục đích: Cập nhật trạng thái đậu hoặc trượt của Learner trong mỗi Course.
     * @param listOfCourse Danh sách HashMap chứa các Course.
     * @param listOfLearner Danh sách HashMap chứa các Learner.
     */
    public void updateListOfCourse(HashMap<String, Course> listOfCourse, HashMap<String, Learner> listOfLearner) {
        // Duyệt qua từng Learner và cập nhật trạng thái vào Course tương ứng
        for (Map.Entry<String, Learner> entry : listOfLearner.entrySet()) {
            Learner learner = entry.getValue();
            for (Map.Entry<String, Course> entry1 : listOfCourse.entrySet()) {
                if (entry1.getValue().getId().equals(learner.getCourse())) {
                    Course courseLearner = entry1.getValue();
                    if (learner.isPassCourse()) {
                        courseLearner.increasePass(); // Tăng số lượng học viên đậu
                    } else {
                        courseLearner.increaseFail(); // Tăng số lượng học viên trượt
                    }
                }
            }
        }
    }
}
