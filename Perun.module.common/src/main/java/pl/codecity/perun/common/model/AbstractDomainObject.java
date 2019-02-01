package pl.codecity.perun.common.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@SuppressWarnings("serial")
public abstract class AbstractDomainObject<ID extends Serializable> implements Serializable{

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(length = 100)
    private LocalDateTime createdBy;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(length = 100)
    private LocalDateTime updatedBy;

    // Abstract methods

    public abstract ID getId();
    public abstract String print();

    // Getters and setters

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(LocalDateTime createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(LocalDateTime updatedBy) {
        this.updatedBy = updatedBy;
    }

    // Object class methods

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        AbstractDomainObject<?> that = (AbstractDomainObject<?>) other;
        return new EqualsBuilder()
                .append(getId(), that.getId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [id = " + getId() + "]";
    }
}
