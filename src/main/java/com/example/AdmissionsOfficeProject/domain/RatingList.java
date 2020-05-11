package com.example.AdmissionsOfficeProject.domain;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortNatural;
import org.hibernate.annotations.SortType;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rating_list")
public class RatingList{

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "total_mark")
    private double totalMark;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private Statement statement;
    private boolean accepted;

    public RatingList() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(double totalMark) {
        this.totalMark = totalMark;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

//    @Override
//    public int compareTo(RatingList o) {
//        if (this.totalMark > o.totalMark) {
//            return 1;
//        } else if (this.totalMark < o.totalMark) {
//            return -1;
//        }
//        return 0;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingList that = (RatingList) o;
        return getId() == that.getId() &&
                Double.compare(that.getTotalMark(), getTotalMark()) == 0 &&
                isAccepted() == that.isAccepted() &&
                Objects.equals(getStatement(), that.getStatement());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTotalMark(), getStatement(), isAccepted());
    }
}
