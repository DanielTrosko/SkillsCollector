package listeners;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sources")
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinTable(
            name = "skillSource",
            joinColumns = @JoinColumn(
                    name = "SOURCE_ID",
                    referencedColumnName = "SID"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "SKILL_ID",
                    referencedColumnName = "SKID"
            )
    )
    @OneToMany
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(unique = true, nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Source source = (Source) o;
        return Objects.equals(id, source.id) &&
                Objects.equals(description, source.description) &&
                Objects.equals(name, source.name);
    }

    @Override
    public String toString() {
        return "listeners.Source{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
