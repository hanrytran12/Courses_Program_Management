package Service;

import java.util.Scanner;

/**
 * Class dùng để xử lý việc nhập dữ liệu từ người dùng.
 * 
 * @author Huy
 */
public class InputService {

    /**
     * Phương thức này yêu cầu người dùng nhập một số nguyên.
     * Mục đích: Nhận dữ liệu đầu vào là số nguyên từ bàn phím.
     * 
     * @param text Thông báo để hiển thị cho người dùng trước khi nhập.
     * @return Số nguyên được nhập từ người dùng.
     */
    public int inputInteger(String text) {
        Scanner ip = new Scanner(System.in);
        System.out.println(text);
        int number = Integer.parseInt(ip.next());
        return number;
    }

    /**
     * Phương thức này yêu cầu người dùng nhập một chuỗi ký tự.
     * Mục đích: Nhận dữ liệu đầu vào là một chuỗi từ bàn phím.
     * 
     * @param text Thông báo để hiển thị cho người dùng trước khi nhập.
     * @return Chuỗi ký tự được nhập từ người dùng.
     */
    public String inputString(String text) {
        Scanner ip = new Scanner(System.in);
        System.out.println(text);
        return ip.nextLine();
    }
}
