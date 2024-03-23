package com.mocktest.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Data
@Table(name = "users", schema = "dbo")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "username", unique = true)
    @NotNull(message = "The username should not be blanked!")
    private String username;
    @Column(name = "password")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least 8 characters, one uppercase letter" +
                    ", one digit and one special character")
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "date_of_birth")
    @PastOrPresent(message = "Date of birth must be in the past or present")
    private LocalDate dateOfBirth;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender must not be null")
    private Gender gender;
    @Column(name = "email", unique = true)
    @NotNull(message = "The email should not be blanked!")
    @Email(message = "Invalid email address")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "phone", unique = true)
    @NotNull(message = "The phone should not be blanked!")
    @Pattern(regexp = "\\d{10}", message = "Invalid phone number. Phone number must contain exactly 10 digits.")
    private String phone;
    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @UpdateTimestamp
    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
    @Column(name = "Identity_card" ,unique = true)
    @NotNull(message = "The identity card should not be blanked!")
    @Pattern(regexp="[0-9]{9,12}", message="Invalid identity card number")
    private String identityCard;
    @Column(name = "image_url")
    private String imageURL;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> tickets;
    @Column(name = "isActive")
    private String Active = "true";
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", createdDate=" + createdDate +
                ", updatedTime=" + updatedTime +
                ", identityCard='" + identityCard + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", role=" + role +
                '}';
    }
}
