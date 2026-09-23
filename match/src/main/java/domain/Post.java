package  domain; // 본인 패키지명에 맞게 유지해주세요!

public class Post {
    private Long id;
    private String category;  // 공모전, 스터디, 프로젝트
    private String title;
    private String writer;
    private String position;
    private String content;
    private String imageUrl; // 🖼️ 추가된 이미지 파일명/URL 필드

    // 기본 생성자
    public Post() {}

    // 전체 필드 생성자 (imageUrl 추가)
    public Post(Long id, String category, String title, String writer, String position, String content, String imageUrl) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.writer = writer;
        this.position = position;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getWriter() { return writer; }
    public void setWriter(String writer) { this.writer = writer; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}