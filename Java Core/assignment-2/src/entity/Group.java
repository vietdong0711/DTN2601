package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private int id;
    private String name;
    private Account creator;
    private List<Account> accounts;
    private LocalDate createDate;

    public Group() {

    }

    public Group(List<Account> accounts, LocalDate createDate, Account creator, String name) {
        this.accounts = accounts;
        this.createDate = createDate;
        this.creator = creator;
        this.name = name;
    }

    public Group(List<String> username, LocalDate createDate, Account creator, String name) {
        this.createDate = createDate;
        this.creator = creator;
        this.name = name;
        // chuyen list username thanh list account
        List<Account> accounts1 = new ArrayList<>();
        for (String usename: username) {
            Account acc = new Account(usename);
            accounts1.add(acc);// theem account vua moi tao vafo list account
        }
        this.accounts = accounts1;
    }


    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
