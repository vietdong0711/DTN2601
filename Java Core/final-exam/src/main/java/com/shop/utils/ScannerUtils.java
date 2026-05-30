package com.shop.utils;


import com.shop.common.StringCommon;

import java.util.Objects;
import java.util.Scanner;

// hỗ trợ nhập dữ liệu và check du lieu nhap tu ban phim
public class ScannerUtils {
    private static Scanner sc = new Scanner(System.in);

    public static int inputInt() {
        while (true) {
            try {
                // Nhập vào 1 chuỗi ký tự
                // Integer.parseInt ==> convert từ String sang Interger
                // TH1: Nếu nhập vào chuỗi là số nguyên ==> convert thành công
                // TH2: Nếu nhập vào chuỗi là ko số nguyên ==> có exception ==> Nhập lại
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Nhập lại...");
            }
        }
    }

    public static int inputID() {
        while (true) {
            int number = ScannerUtils.inputInt();
            if (number > 0) {
                return number;
            } else {
                System.err.println("ID phải lớn hơn 0! Nhập lại:");
            }
        }
    }

    public static String inputString() {
        while (true) {
            String string = sc.nextLine();
            if (Objects.nonNull(string) && !string.trim().isEmpty()) {
                return string;
            } else {
                System.err.println("Nhập lại:");
            }
        }
    }

    // kiem tra dinh dang email xem co hop le ko
    public static String inputEmail() {
        while (true) {
            String email = sc.nextLine();// equals(); so sanh gtri,   == so sánh địa chỉ ,  biểu thức chính quy, matches(): so sánh  theo quy tắc
            if (email == null || email.trim().isEmpty() || !email.matches(StringCommon.EMAIL_REGEX)) {// a@b
                System.out.print("Không đúng định dạng Nhập lại: ");
            } else {
                return email;
            }
        }
    }

    public static String inputFullName() {
        while (true) {
            String fullName = ScannerUtils.inputString();
            if (fullName == null
                    || !fullName.matches("^[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
                    + "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨú"
                    + "ÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ \\\\ _-]{3,25}$")) {

                System.out.println("Nhập lại: ");

            } else {
                return fullName;
            }
        }
    }

    public static String inputPassword() {
        while (true) {
            String password = ScannerUtils.inputString();
            if (password.length() < 6 || password.length() > 12) {
                System.out.print("Nhập lại: ");
                continue;
            }
            boolean hasAtLeast1Character = false;
            for (int i = 0; i < password.length(); i++) {
                if (Character.isUpperCase(password.charAt(i))) {
                    hasAtLeast1Character = true;
                    break;
                }
            }

            if (hasAtLeast1Character) {// password dung
                return password;
            } else {
                System.out.print("Mời bạn nhập lại password: ");
            }
        }
    }
}
