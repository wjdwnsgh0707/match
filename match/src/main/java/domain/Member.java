package domain;

public class Member {
    private Long id;
    private String email;      // 학교 이메일 (아이디 역할)
    private String password;   // 비밀번호
    private String name;       // 이름/닉네임
    private String department; // 학과 (예: 컴퓨터공학전공)

    public Member() {}

    public Member(Long id, String email, String password, String name, String department) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.department = department;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}