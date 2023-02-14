package hu.vizicsaba.booking.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("bookings")
public class Booking {

    @Id
    @Column("id")
    private Long id;

    @Column("user_id")
    private String userId;

    @Column("from_date")
    private LocalDateTime from;

    @Column("to_date")
    private LocalDateTime to;

}
