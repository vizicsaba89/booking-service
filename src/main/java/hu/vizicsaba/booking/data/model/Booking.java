package hu.vizicsaba.booking.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("bookings")
public class Booking {

    @Id
    @Column("id")
    private Integer id;

    @Column("title")
    private ZonedDateTime from;

    @Column("content")
    private ZonedDateTime to;

}
