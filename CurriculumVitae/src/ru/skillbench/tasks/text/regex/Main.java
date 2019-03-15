package ru.skillbench.tasks.text.regex;

/**
 * Created by Sir Nightmare on 08/14/16.
 * Just to check
 */
public class Main {
    public static void main(String[] args) {
        CurriculumVitae cv = new CurriculumVitaeImpl();
        String str = "John Doe Petrovich\n" +
                "Resume title\n" +
                "Some quote\n" +
                "street and number\n" +
                "postcode city\n" +
                "country\n" +
                "M +1 (234) 567 890\n" +
                "T +2 (345) 678 901\n" +
                "F +3 (456) 789 012\n" +
                "E john@doe.org\n" +
                "www.johndoe.com\n" +
                "additional information\n" +
                "Education\n" +
                "Degree, Institution, City, Grade. year–year\n" +
                "Description\n" +
                "Degree, Institution, City, Grade. year–year\n" +
                "Description\n" +
                "Master thesis\n" +
                "title: Title\n" +
                "supervisors: Supervisors\n" +
                "description: Short thesis abstract\n" +
                "Experience\n" +
                "Vocational\n" +
                "Job title, Employer, City. year–year\n" +
                "General description no longer than 1–2 lines.\n" +
                "Detailed achievements:\n" +
                "{ Achievement 1;\n" +
                "{ Achievement 2, with sub-achievements:\n" +
                "- Sub-achievement (a);\n" +
                "- Sub-achievement (b), with sub-sub-achievements (don’t do this!);\n" +
                "· Sub-sub-achievement i;\n" +
                "· Sub-sub-achievement ii;\n" +
                "· Sub-sub-achievement iii;\n" +
                "- Sub-achievement (c);\n" +
                "{ Achievement 3.\n" +
                "Job title, Employer, City. year–year\n" +
                "Description line 1\n" +
                "Description line 2\n" +
                "Miscellaneous\n" +
                "Job title, Employer, City. year–year\n" +
                "Description\n" +
                "1/3\n" +
                "Languages\n" +
                "Language 1: Skill level Comment\n" +
                "Language 2: Skill level Comment\n" +
                "Language 3: Skill level Comment\n" +
                "Computer skills\n" +
                "category 1: XXX, YYY, ZZZ category 4: XXX, YYY, ZZZ\n" +
                "category 2: XXX, YYY, ZZZ category 5: XXX, YYY, ZZZ\n" +
                "category 3: XXX, YYY, ZZZ category 6: XXX, YYY, ZZZ\n" +
                "Interests\n" +
                "hobby 1: Description\n" +
                "hobby 2: Description\n" +
                "hobby 3: Description\n" +
                "Extra 1\n" +
                "{ Item 1\n" +
                "{ Item 2\n" +
                "{ Item 3. This item is particularly long and therefore normally spans over\n" +
                "several lines. Did you notice the indentation when the line wraps?\n" +
                "Extra 2\n" +
                "{ Item 1 { Item 4\n" +
                "{ Item 2 { Item 5[?]\n" +
                "{ Item 3 { Item 6. Like item 3 in the single\n" +
                "column list before, this item is particularly\n" +
                "long to wrap over several\n" +
                "lines.\n" +
                "References\n" +
                "Category 1 Category 2 All the rest & some more\n" +
                "{ Person 1\n" +
                "{ Person 2\n" +
                "{ Person 3\n" +
                "Amongst others:\n" +
                "{ Person 1, and\n" +
                "{ Person 2\n" +
                "(more upon request)\n" +
                "That person, and those also (all\n" +
                "available upon request).\n" +
                "2/3\n" +
                "Company Recruitment team\n" +
                "Company, Inc.\n" +
                "123 somestreet\n" +
                "some city\n" +
                "January 01, 1984\n" +
                "Dear Sir or Madam,\n" +
                "John Doe\n" +
                "street and number\n" +
                "postcode city\n" +
                "country\n" +
                "M +1 (234) 567 890\n" +
                "T +2 (345) 678 901\n" +
                "F +3 (456) 789 012\n" +
                "E john@doe.org\n" +
                "www.johndoe.com\n" +
                "additional information\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis ullamcorper\n" +
                "neque sit amet lectus facilisis sed luctus nisl iaculis. Vivamus at neque\n" +
                "arcu, sed tempor quam. Curabitur pharetra tincidunt tincidunt. Morbi\n" +
                "volutpat feugiat mauris, quis tempor neque vehicula volutpat. Duis\n" +
                "tristique justo vel massa fermentum accumsan. Mauris ante elit, feugiat\n" +
                "vestibulum tempor eget, eleifend ac ipsum. Donec scelerisque lobortis\n" +
                "ipsum eu vestibulum. Pellentesque vel massa at felis accumsan rhoncus.\n" +
                "Suspendisse commodo, massa eu congue tincidunt, elit mauris pellentesque\n" +
                "orci, cursus tempor odio nisl euismod augue. Aliquam adipiscing\n" +
                "nibh ut odio sodales et pulvinar tortor laoreet. Mauris a accumsan ligula.\n" +
                "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per\n" +
                "inceptos himenaeos. Suspendisse vulputate sem vehicula ipsum varius\n" +
                "nec tempus dui dapibus. Phasellus et est urna, ut auctor erat. Sed\n" +
                "tincidunt odio id odio aliquam mattis. Donec sapien nulla, feugiat eget\n" +
                "adipiscing sit amet, lacinia ut dolor. Phasellus tincidunt, leo a fringilla\n" +
                "consectetur, felis diam aliquam urna, vitae aliquet lectus orci nec velit.\n" +
                "Vivamus dapibus varius blandit.\n" +
                "Duis sit amet magna ante, at sodales diam. Aenean consectetur porta\n" +
                "risus et sagittis. Ut interdum, enim varius pellentesque tincidunt, magna\n" +
                "libero sodales tortor, ut fermentum nunc metus a ante. Vivamus odio\n" +
                "leo, tincidunt eu luctus ut, sollicitudin sit amet metus. Nunc sed orci\n" +
                "lectus. Ut sodales magna sed velit volutpat sit amet pulvinar diam\n" +
                "venenatis.\n" +
                "Albert Einstein discovered that e = mc2\n" +
                "in 1905.\n" +
                "e = lim\n" +
                "n>? \u0012\n" +
                "1 + 1\n" +
                "n\n" +
                "\u0013n\n" +
                "Yours faithfully,\n" +
                "John Doe\n" +
                "Attached: curriculum vit?\n" +
                "3/3";
        cv.setText(str);
        System.out.println(cv.getFullName());
        System.out.println(cv.getFirstName());
        System.out.println(cv.getMiddleName());
        System.out.println(cv.getLastName());

    }
}