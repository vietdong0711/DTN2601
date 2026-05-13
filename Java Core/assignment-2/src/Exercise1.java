import entity.Account;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Exercise1 {

    // viết 1 project có chức năng như sau
    // in ra menu
    // 1. thêm mới account
    // 2. thêm mới group
    // 3. thêm 1 account vào 1 group
    // 4. thêm 1 account vào 1 group ngẫu nhiên
    // nhập sai -> in ra menu và mời bạn nhập lại

    public static void question1(List<Account> accounts, List<Group> groups, List<GroupAccount> groupAccounts) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        while (true) {
            System.out.println("== Mời bạn chọn chức năng ==");
            System.out.println("1. Thêm mới account");
            System.out.println("2. Thêm mới group");
            System.out.println("3. Thêm 1 account vào 1 group");
            System.out.println("4. Thêm 1 account vào 1 group ngẫu nhiên");
            System.out.println("5. Xem ds groupAccount");
            String choose = sc.nextLine();
            switch (choose) {
                case "1":
                    System.out.println("Chức năng Thêm mới account");

                    Account account = new Account();

                    Account account1 = new Account(1, "account1", "account1@gmail.com", "account1");

                    System.out.println("Mời bạn nhập accountId");
                    account.setAccountID(sc.nextInt());
                    System.out.println(account.getAccountID());


                    sc.nextLine();
                    System.out.println("Mời bạn nhập Username");
                    account.userName = sc.nextLine();
                    System.out.println(account.userName);


                    System.out.println("Mời bạn nhập Fullname");
                    account.fullname = sc.nextLine();
                    System.out.println("Mời bạn nhập Email");
                    account.email = sc.nextLine();
                    // thêm account vào accounts
                    accounts.add(account);
                    System.out.println("Thêm account thành công");
                    break;
                case "2":
                    System.out.println(" Chức năng Thêm mới group");
                    Group group = new Group();
                    System.out.println("Mời bạn nhập  group Id");
                    group.groupID = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Mời bạn nhập  group Name");
                    group.groupName = sc.nextLine();
                    // thêm group vào ds groups
                    groups.add(group);
                    System.out.println("Thêm group thành công");
                    break;
                case "3":
                    System.out.println("Chức năng Thêm 1 account vào 1 group");
                    System.out.println("Nhập username cần thêm vào group: ");
                    for (Account acc : accounts) {
                        System.out.println(acc.userName);
                    }
                    String username = sc.nextLine();
                    System.out.println("Nhập tên group cần thêm vào account: ");
                    for (Group gr : groups) {
                        System.out.println(gr.groupName);
                    }
                    String groupName = sc.nextLine();
                    // Tìm ra account và group mà người dùng vừa nhập
                    Account accountAdd = new Account();
                    for(Account acc : accounts) {
                        if (acc.userName.equals(username)) {
                            accountAdd = acc;
                            break;
                        }
                    }

                    Group groupAdd = new Group();
                    for(Group gr : groups) {
                        if (gr.groupName.equals(groupName)) {
                            groupAdd = gr;
                            break;
                        }
                    }

                    GroupAccount groupAccount = new GroupAccount();
                    groupAccount.account = accountAdd;
                    groupAccount.group = groupAdd;

                    // them groupaccount o tren vao danh sach
                    groupAccounts.add(groupAccount);
                    System.out.println("Them moi groupAccount thanh cong");
                    break;
                case "4":
                    System.out.println(" Chức năng Thêm 1 account vào 1 group ngẫu nhiên");
                    System.out.println("Chức năng Thêm 1 account vào 1 group");
                    System.out.println("Nhập username cần thêm vào group: ");
                    for (Account acc : accounts) {
                        System.out.println(acc.userName);
                    }
                    String username4 = sc.nextLine();
                    // Tìm ra account mà người dùng vừa nhập
                    Account accountAdd4 = new Account();
                    for(Account acc : accounts) {
                        if (acc.userName.equals(username4)) {
                            accountAdd4 = acc;
                            break;
                        }
                    }
                    // random group
                    // group đang có nhiêu phần tử
//                    groups.size();// 4
                    int indexRandom = random.nextInt(groups.size());
                    Group groupRandom = groups.get(indexRandom);

                    GroupAccount groupAccount4 = new GroupAccount();
                    groupAccount4.account = accountAdd4;
                    groupAccount4.group = groupRandom;

                    // them groupaccount o tren vao danh sach
                    groupAccounts.add(groupAccount4);
                    System.out.println("Them moi groupAccount ngẫu nhiên thanh cong");

                    break;
                case "5":
                    System.out.println("Xem ds group demo.Account");
                    for (GroupAccount ga : groupAccounts) {
                        System.out.println(ga);
                    }
                    break;

                default:
                    System.out.println("Mời bạn nhập lại!");
                    break;
            }

            System.out.println("bạn có muốn tiếp tục không(Y/N)");
            String checkRun = sc.nextLine();
            if (checkRun.equals("Y")) {
                return;
            }

        }


    }

    //
}
