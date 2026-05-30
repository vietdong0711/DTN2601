import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Department> departments = new ArrayList<>();
        departments.add(new Department("Bảo vệ", 1));
        departments.add(new Department("Chờ việc", 2));
        departments.add(new Department("Giám đốc", 3));
        departments.add(new Department("Kỹ thuật", 1));
        departments.add(new Department("Nhân sự", 3));
        departments.add(new Department("Phó giám đốc", 0));
        departments.add(new Department("Sale", 1));
        departments.add(new Department("Tài chính", 2));

        // tim ra department co ten la "Nhân sự"
//        Department department = new Department();
//        for (Department dep : departments) {
//            if ("Nhân sự".equals(dep.getName())) {//"Nhân sự".equals(null)
//                department = dep;
//            }// tifm dc n department cos ten nhan su
//        }
//        System.out.println(department);

//        Department department1 = departments.stream().filter(dep -> "Nhân sự".equals(dep.getName())).findFirst().get();
//                                                                                                                // limit1
//        System.out.println(department1);

        // tifm ra cac phong ban trong ten co chu "n"
//        List<Department> departments1 = new ArrayList<>();
//        for (Department dep : departments) {
//            if (dep.getName().contains("n")) {//"Nhân sự".equals(null)
//                departments1.add(dep);
//            }// tifm dc n department cos ten chuaw chu "n"
//        }
//        System.out.println(departments1.toString());
//        List<Department> departments2 = departments.stream().filter(dep -> Objects.nonNull(dep.getName()) && dep.getName().contains("n")).toList();
//        System.out.println(departments2.toString());

        // chuyeern tu 1 lisst ve map
        // laay ra map tuwf ds department key la departmentName, value la object department tuwowng ung
//        Map<String, Department> departmentMap = new HashMap<>();
//        for (Department dep : departments) {
//            departmentMap.put(dep.getName(), dep);
//        }
//        System.out.println(departmentMap);

//        Map<String, Department> departmentMap1 = departments.stream().
//                collect(Collectors.toMap(dep -> dep.getName(), dep -> dep));
//        System.out.println(departmentMap1);

        // lay ra map  key so account , value List<Department> danh sasch cac phong ban co cung so luong
        // dem xem voi moi so luong account thi co bao nhieu phong ban
        Map<Integer, List<Department>> departmentMap2 = new HashMap<>();
        for (Department department : departments) {
            if (!departmentMap2.containsKey(department.getCount())) {// chua co map voi so luong nay thi tao moi 1 ptu trong map
                List<Department> departmentList = new ArrayList<>();
                departmentList.add(department);
                departmentMap2.put(department.getCount(), departmentList);
            } else {
                List<Department> departmentList = departmentMap2.get(department.getCount());// lay ra ls hien tai tuong ung so voicount
                departmentList.add(department);
                departmentMap2.put(department.getCount(), departmentList);
            }
        }

        System.out.println(departmentMap2);

        Map<Integer, List<Department>> departmentMap3 = departments.stream().collect(Collectors.groupingBy(dep -> dep.getCount()));

        System.out.println(departmentMap3);
        Tim
    }
}