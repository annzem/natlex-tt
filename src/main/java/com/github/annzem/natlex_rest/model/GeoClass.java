package com.github.annzem.natlex_rest.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class GeoClass {
    public GeoClass () {
    }

    public GeoClass (String name, String code
//            , Section section
    ) {
        this.name = name;
        this.code = code;
//this.section = section;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private String code;

//    @ManyToOne
//    @JoinColumn(name = "section_id", nullable = false)
//    private Section section;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

//    public Section getSection() {
//        return section;
//    }
//
//    public void setSection(Section section) {
//        this.section = section;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoClass geoClass = (GeoClass) o;
        return Objects.equals(id, geoClass.id) &&
                Objects.equals(name, geoClass.name) &&
                Objects.equals(code, geoClass.code);
//                &&
//                Objects.equals(section, geoClass.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code
//                , section
        );
    }
}
