package io.github.marrafon91.DSLearn.entities;

import io.github.marrafon91.DSLearn.entities.pk.EnrollmentPK;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "tb_enrollment")
public class Enrollment {

    @EmbeddedId
    private EnrollmentPK id = new EnrollmentPK();

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant enrollmentMoment;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant refundMoment;

    private boolean available;
    private boolean onlyUpdate;

    public Enrollment() {
    }

    public Enrollment(User user, Offer offer, Instant enrollmentMoment, Instant refundMoment, boolean available, boolean onlyUpdate) {
        id.setUser(user);
        id.setOffer(offer);
        this.enrollmentMoment = enrollmentMoment;
        this.refundMoment = refundMoment;
        this.available = available;
        this.onlyUpdate = onlyUpdate;
    }

    public User getStudent() {
        return id.getUser();
    }

    public void setStudent(User user) {
        id.setUser(user);
    }

    public Offer getOffer() {
        return id.getOffer();
    }

    public void setOffer(Offer offer) {
        id.setOffer(offer);
    }

    public Instant getEnrollmentMoment() {
        return enrollmentMoment;
    }

    public void setEnrollmentMoment(Instant enrollmentMoment) {
        this.enrollmentMoment = enrollmentMoment;
    }

    public Instant getRefundMoment() {
        return refundMoment;
    }

    public void setRefundMoment(Instant refundMoment) {
        this.refundMoment = refundMoment;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isOnlyUpdate() {
        return onlyUpdate;
    }

    public void setOnlyUpdate(boolean onlyUpdate) {
        this.onlyUpdate = onlyUpdate;
    }

}
