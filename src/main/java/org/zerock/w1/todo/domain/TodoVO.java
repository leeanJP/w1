package org.zerock.w1.todo.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TodoVO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;


    //장점
    //1. 가독성 : 복잡한 객체를 생성할 때 코드의 가독성을 높인다.
    //2. 유연성 : 생성과정에서 필요한 필드만 설정할 수 있고, 나머지는 디폴트 값을 사용할 수 있다.
    //3. 확장성 : 새로운 필드가 추가되어도 기존 코드를 많이 수정하지 않고 확장할 수 있다.
    //4. 불변성 유지 : 불변 객체를 쉽게 생성할 수 있다.


}
