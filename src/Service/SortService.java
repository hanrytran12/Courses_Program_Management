package Service;

import Model.Course;
import Model.Learner;
import Model.Topic;
import java.util.List;

/**
 * Class dùng để thực hiện các chức năng sắp xếp (sort) danh sách Topic, Course, Learner.
 * 
 * @author Huy
 */
public class SortService {

    /**
     * Sắp xếp danh sách các Topic theo thứ tự tăng dần của tên (name).
     * 
     * @param list Danh sách Topic cần sắp xếp.
     */
    public void sortListTopic(List<Topic> list) {
        // Sắp xếp danh sách Topic theo tên (tăng dần theo thứ tự alphabet)
        list.sort((o1, o2) -> {
            return o1.getName().compareTo(o2.getName());
        });
    } 

    /**
     * Sắp xếp danh sách các Course theo thứ tự tăng dần của ngày bắt đầu (beginDate).
     * 
     * @param list Danh sách Course cần sắp xếp.
     */
    void sortListCourse(List<Course> list) {
        // Sắp xếp danh sách Course theo ngày bắt đầu (tăng dần)
        list.sort((o1, o2) -> {
            return o1.getBeginDate().compareTo(o2.getBeginDate());
        });
    }

    /**
     * Sắp xếp danh sách các Learner theo thứ tự giảm dần của điểm GPA (điểm trung bình).
     * 
     * @param list Danh sách Learner cần sắp xếp.
     */
    void sortListLearner(List<Learner> list) {
        // Sắp xếp danh sách Learner theo GPA (giảm dần)
        list.sort((o1, o2) -> {
            return o2.getGPA().compareTo(o1.getGPA());
        });
    }
}
