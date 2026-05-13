import entity.Account;

import java.time.LocalDate;

public class Group {
    int groupID;
    String groupName;
    Account creator;
    LocalDate createDate;

    @Override
    public String toString() {
        return "Group{" +
                "groupID=" + groupID +
                ", groupName='" + groupName + '\'' +
                ", creator=" + creator +
                ", createDate=" + createDate +
                '}';
    }
}
