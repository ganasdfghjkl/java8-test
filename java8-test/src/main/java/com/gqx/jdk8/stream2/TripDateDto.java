package com.gqx.jdk8.stream2;

import lombok.*;

import java.util.Objects;

/**
 * @author gqx
 * @date 2020/8/17 17:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TripDateDto {
    private Integer year;
    private Integer month;
    private Integer day;

    public boolean equals(TripDateDto obj) {
        return year.intValue() == obj.getYear().intValue() &&
                month.intValue() == obj.getMonth().intValue() &&
                day.intValue() == obj.getDay().intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripDateDto that = (TripDateDto) o;
        return year.equals(that.year) &&
                month.equals(that.month) &&
                day.equals(that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }

}
