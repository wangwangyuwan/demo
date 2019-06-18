package git.wangwangyuwan.demo.dto;

public class GitHubUserDTO {
    private String name;
    private String bio;
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GitHubUserDTO{" +
                "name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", id=" + id +
                '}';
    }
}
