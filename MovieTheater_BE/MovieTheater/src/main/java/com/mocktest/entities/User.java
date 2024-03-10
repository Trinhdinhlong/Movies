package com.mocktest.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
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
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "email", unique = true)
    @NotNull(message = "The email should not be blanked!")
    @Email(message = "Invalid email address")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "phone", unique = true)
    @Pattern(regexp="\\d{10}", message="Invalid phone number")
    private String phone;
    @CreationTimestamp
    @Column(name = "register_date")
    private LocalDateTime registerDate;
    @UpdateTimestamp
    @Column(name = "update_Date")
    private LocalDateTime updateDate;
    @Column(name = "Identity_card" ,unique = true)
    private String identityCard;
    @Column(name = "image_url")
    private String imageURL;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> tickets;

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
                ", registerDate=" + registerDate +
                ", updateDate=" + updateDate +
                ", identituCard='" + identityCard + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", role=" + role +
                '}';
    }
}
