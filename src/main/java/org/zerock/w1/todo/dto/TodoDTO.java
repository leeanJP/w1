package org.zerock.w1.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
    //@Data
    //@Getter @Setter @ToString @EqualsAndHashCode @RequireArgsConstructor

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;

}
